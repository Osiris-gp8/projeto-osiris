from datetime import datetime
from db_manager import DbManager
from api_client import ApiClient
import json
from commons.utils import *

from requests.api import request 
class AcessosPipeline:
    def __init__(self):
        self.db = DbManager("root", "bandtec123", "localhost", "processamento_db")
        self.api = ApiClient("http://localhost:8080")          

    def send_data(self):
        data_json = self.__format_to_request()
        data_json = json.loads(data_json.to_json(orient='records', date_format="iso"))
        self.api.send_data('/acessos/list',data_json)
    
    def __format_to_request(self) -> pd.DataFrame:
        data_frame = self.get_data(False)
        data_frame.rename(columns = {'idConsumidor':'idConsumidorEcommerce', 'fkEcommerce':'ecommerce'}, inplace=True)
        data_frame['ecommerce'] = data_frame['ecommerce'].apply(lambda x : {"idEcommerce":x})
        return data_frame

    def get_data(self, group:bool):
        data_frame = self.db.read("SELECT * FROM acessos").astype({
        'fimAcesso':'datetime64[ns]', 
        'inicioAcesso':'datetime64[ns]', 
        })
        if group:
            data_frame = self.__group_data(data_frame)
        return data_frame
    
    def __group_data(self,data_frame:pd.DataFrame) -> pd.DataFrame:
        data_frame['duracao_sessao'] = self.__get_duration(data_frame) 
        data_group = data_frame.groupby(['fkEcommerce', 'idConsumidor'])\
            .agg(quantidade_acessos=('idAcessos','count'),total_Duracao_sessao=('duracao_sessao', 'sum'))\
            .reset_index()
        data_group['created_at'] = datetime.now()
        return data_group
    def __get_duration(self, data_frame):
        return (data_frame.fimAcesso - data_frame['inicioAcesso']).dt.seconds