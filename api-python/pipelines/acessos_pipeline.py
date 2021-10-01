from datetime import datetime
from pipelines.base_pipeline import Pipeline

from commons.db_manager import DbManager
from commons.api_client import ApiClient
import json
from commons.utils import *

class AcessosPipeline(Pipeline):
    def __init__(self, db: DbManager, api: ApiClient, group: bool = False):
        super().__init__()
        self.db = db
        self.api = api
        self.group = group

    def get_data(self) -> pd.DataFrame:
        self.logger.info("Getting data from database")
        data_frame = self.db.read("SELECT * FROM acessos").astype({
        'fimAcesso':'datetime64[ns]', 
        'inicioAcesso':'datetime64[ns]', 
        })
        return data_frame
        
    
    def process_data(self, df: pd.DataFrame) -> pd.DataFrame:
        self.logger.info("Processing data")
        if self.group:
            data_frame = self.__group_data(df)
        return data_frame
    
    def __group_data(self,data_frame:pd.DataFrame) -> pd.DataFrame:
        data_frame['duracao_sessao'] = self.__get_duration(data_frame) 
        data_group = data_frame.groupby(['fkEcommerce', 'idConsumidor'])\
            .agg(quantidade_acessos=('idAcessos','count'),total_Duracao_sessao=('duracao_sessao', 'sum'))\
            .reset_index()
        data_group['created_at'] = datetime.now()
        return data_group
    
    def __get_duration(self, data_frame: pd.DataFrame):
        return (data_frame.fimAcesso - data_frame['inicioAcesso']).dt.seconds
    
    def save_data(self, df: pd.DataFrame) -> None:
        self.logger.info("Saving data")
        data_json = self.__format_to_request(df)
        data_json = json.loads(df.to_json(orient='records', date_format="iso"))
        self.api.send_data('/acessos/list',data_json)
    
    def __format_to_request(self, df: pd.DataFrame) -> pd.DataFrame:
        df.rename(columns = {'idConsumidor':'idConsumidorEcommerce', 'fkEcommerce':'ecommerce'}, inplace=True)
        df['ecommerce'] = df['ecommerce'].apply(lambda x : {"idEcommerce":x})
        return df