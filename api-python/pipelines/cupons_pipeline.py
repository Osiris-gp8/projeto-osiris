from commons.api_client import ApiClient
from datetime import date, timedelta
import numpy as np
import pandas as pd
from pandas.core.frame import DataFrame
from pipelines.base_pipeline import Pipeline

class CuponsPipeline(Pipeline):
    def __init__(self, eventos_df: DataFrame, api: ApiClient):
        super().__init__()
        self.eventos_df = eventos_df
        self.api = api
    
    def get_data(self) -> DataFrame:
        self.logger.info("Getting data from eventos pipeline")
        return self.eventos_df
    
    def process_data(self, df: DataFrame) -> DataFrame:
        self.logger.info("Processing data")
        max_categoria_faixa = df[df['statusEvento'] == 1]\
        .groupby(['faixa_etaria', 'categoria'], as_index=False)\
        .agg(
            maxPreco = ('preco' , 'max'),
            quantidade = ('idEvento' , 'count')
        ).reset_index()
        
        cupons = pd.DataFrame()
        cupons['valor'] = max_categoria_faixa['maxPreco'] / 100 # 1% do valor
        cupons['usado'] = False
        cupons['cupomEcommerce'] = False
        cupons['nomeCupom'] = np.random.randint(1, 10, size = len(max_categoria_faixa))
        cupons['nomeCupom'] = 'OSIRIS'+cupons['nomeCupom'].astype(str)
        cupons['dataEmitido'] = date.today()
        cupons['dataValido'] = cupons['dataEmitido'] + timedelta(days = 30)
        cupons['idConsumidor'] = df.merge(max_categoria_faixa)\
            .query('maxPreco == preco').reset_index()['idConsumidor']
        cupons['ecommerce'] = ""
        
        sem_cupom = df[df['fkCupom'] == 0]
        sem_cupom = sem_cupom[~sem_cupom['idConsumidor'].isin(cupons['idConsumidor'])].reset_index()
        sem_cupom_freq = sem_cupom.groupby('idConsumidor').agg(
            freq = ('idEvento', 'count'),
            media_preco = ('preco', 'mean')
            ).reset_index()
        freq_quartis = sem_cupom_freq['freq'].quantile([0.25, 0.50, 0.75])

        receber_cupom = sem_cupom_freq[ sem_cupom_freq['freq'] > freq_quartis[0.50] ].reset_index()
        
        cupons = pd.DataFrame()
        cupons['idConsumidorEcommerce'] = receber_cupom['idConsumidor']
        cupons['usado'] = False
        cupons['cupomEcommerce'] = False
        cupons['nomeCupom'] = np.random.randint(1, 10, size = len(cupons))
        cupons['nomeCupom'] = 'OSIRIS'+cupons['nomeCupom'].astype(str)
        cupons['dataEmitido'] = date.today()
        cupons['dataValidado'] = cupons['dataEmitido'] + timedelta(days = 30)
        cupons['valor'] = receber_cupom['media_preco'] / 100
        
        return cupons
    
    def save_data(self, df: DataFrame):
        self.logger.info("Saving data")
        self.api.send_data("/cupons/list" , df)