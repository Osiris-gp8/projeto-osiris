from commons.api_client import ApiClient
from datetime import date
import pandas as pd
from pandas.core.frame import DataFrame
from pipelines.base_pipeline import Pipeline
from commons.db_manager import DbManager
import numpy as np

class EventosPipeline(Pipeline):
    def __init__(self, db: DbManager, api: ApiClient, output_database: DbManager):
        super().__init__()
        self.db = db
        self.api = api
        self.output_database = output_database

    
    def get_data(self) -> DataFrame:
        self.logger.info("Getting data from database")
        df = self.db.read("SELECT * FROM eventos").astype({
            'categoria':'category',
            'sexo' : 'category',
            'dataNascimento':'datetime64[ns]'
        })
        return df
    
    def process_data(self, df: DataFrame) -> DataFrame:
        self.logger.info("Processing data")
        df['categoria'] = df['categoria'].cat.codes
        df['sexo'] = df['sexo'].cat.codes
        df['idade'] = date.today().year - df['dataNascimento'].dt.year - (
        df['dataNascimento']\
        .apply( lambda x : (date.today().month, date.today().day) < (x.month, x.day)) )
        
        quartis_idade = df['idade'].quantile([0.25, 0.5, 0.75])
        df['faixa_etaria'] = np.where(
            df['idade'].between(0, quartis_idade[0.25]), 'Primeira Faixa',
            np.where(
                df['idade'].between(quartis_idade[0.25], quartis_idade[0.5]), 
                'Segunda Faixa',
                'Terceira Faixa'
            )
        )
        
        eventos = self._format_eventos(df)
        return eventos
    
    def _format_eventos(self, df):
        newDf = pd.DataFrame()
        newDf["id_evento"] = None
        newDf['id_consumidor_ecommerce'] = df['idConsumidor']
        newDf['nome_produto']= df['nome']
        newDf['preco']= df['preco']
        newDf['nome_categoria']= df['categoria']
        newDf['data_compra']= df['dataCompra']
        newDf['dominio_status_id_dominio_status'] =  df['statusEvento']
        newDf['ecommerce_id_ecommerce'] = 1
        newDf['cupom_id_cupom'] =  None

        return newDf
    
    def save_data(self, df: DataFrame) -> None:
        self.logger.info("Saving data")
        self.output_database.insert(df, "evento")
        self.logger.info("Saved successfully")
        
