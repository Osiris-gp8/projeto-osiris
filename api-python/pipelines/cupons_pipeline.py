from datetime import date, timedelta
import numpy as np
import pandas as pd
from commons.db_manager import DbManager
from pandas.core.frame import DataFrame
from pipelines.base_pipeline import Pipeline

class CuponsPipeline(Pipeline):
    def __init__(self, output_database:DbManager):
        super().__init__()
        self.output_database = output_database
    
    def get_data(self) -> DataFrame:
        self.logger.info("Getting data from eventos pipeline")
        return self.output_database.read("select * from evento")
    
    def process_data(self, df: DataFrame) -> DataFrame:
        self.logger.info("Processing data")
        max_categoria_faixa = df[df['dominio_status_id_dominio_status'] == 1]\
        .groupby(['faixa_etaria', 'nome_categoria','ecommerce_id_ecommerce'], as_index=False)\
        .agg(
            maxPreco = ('preco' , 'max'),
            quantidade = ('id_evento' , 'count')
        ).reset_index()
        
        cupons = pd.DataFrame()
        cupons['valor'] = max_categoria_faixa['maxPreco'] / 100 # 1% do valor
        cupons['usado'] = False
        cupons['cupom_ecommerce'] = False
        cupons['ecommerce_id_ecommerce'] = max_categoria_faixa['ecommerce_id_ecommerce']
        cupons['nome_cupom'] = np.random.randint(1, 10, size = len(max_categoria_faixa))
        cupons['nome_cupom'] = 'OSIRIS'+cupons['nome_cupom'].astype(str)
        cupons['data_emitido'] = date.today()
        cupons['data_valido'] = cupons['data_emitido'] + timedelta(days = 30)
        cupons['id_consumidor_ecommerce'] = df.merge(max_categoria_faixa)\
            .query('maxPreco == preco').reset_index()['id_consumidor_ecommerce']
        
        sem_cupom = df[df['cupom_id_cupom'].isnull()]
        sem_cupom = sem_cupom[~sem_cupom['id_consumidor_ecommerce']\
            .isin(cupons['id_consumidor_ecommerce'])].reset_index()
        sem_cupom_freq = sem_cupom.groupby(['ecommerce_id_ecommerce','id_consumidor_ecommerce']).agg(
            freq = ('id_evento', 'count'),
            media_preco = ('preco', 'mean')
            ).reset_index()
        freq_quartis = sem_cupom_freq['freq'].quantile([0.25, 0.50, 0.75])

        receber_cupom = sem_cupom_freq[ sem_cupom_freq['freq'] > freq_quartis[0.50] ].reset_index()
        
        cupons = pd.DataFrame()
        cupons['id_consumidor_ecommerce'] = receber_cupom['id_consumidor_ecommerce']
        cupons['usado'] = False
        cupons['cupom_ecommerce'] = False
        cupons['ecommerce_id_ecommerce'] = receber_cupom['ecommerce_id_ecommerce']
        cupons['nome_cupom'] = np.random.randint(1, 10, size = len(cupons))
        cupons['nome_cupom'] = 'OSIRIS'+cupons['nome_cupom'].astype(str)
        cupons['data_emitido'] = date.today()
        cupons['data_validado'] = cupons['data_emitido'] + timedelta(days = 30)
        cupons['valor'] = receber_cupom['media_preco'] / 100
        
        return cupons
    
    def save_data(self, df: DataFrame):
        self.logger.info("Saving data")
        self.output_database.insert(df, "cupom")
        self.logger.info("Saved successfully")