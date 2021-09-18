from numpy import datetime64
import requests
from db_manager import DbManager
from api_client import ApiClient
import json
from commons.utils import *

from requests.api import request 
class AcessosApi:
    def __init__(self, domain):
        self.db = DbManager("root", "bandtec123", "localhost", "processamento_db")
        self.api = ApiClient("localhost:8080")  
        

    
    def login(self, uri='/auth'):
        user = "user8"
        pwd = "user8"
        data_json = {
            "login":user,
            "senha":pwd
        }

        response = requests.post(url = (self.domain + uri), json = data_json).content
        self.token = json.loads(response)['token']
        

    def send_data(self):
        data_json = self.get_data(False)
        data_json = json.loads(data_json.to_json(orient='records', date_format="iso"))
        # self.login("/auth")
        tipo = "Cupom"
        if(uri == "/acessos/list"):
            tipo = "Evento"
        print(f"Inserindo dados de {tipo} no BD...")
        requisicao =  requests.post(url = (self.domain + uri),  headers={'Authorization': 'Bearer {}'.format(self.token)},json = data_json)
        if(requisicao.ok):
            print("Inserção feita com sucesso")
    
    def get_data(self, group:bool):
        data_frame = self.db.read("SELECT * FROM acessos").astype({
        'fimAcesso':'datetime64[ns]', 
        'inicioAcesso':'datetime64[ns]', 
        })
        data_frame['duracao_sessao'] =  (data_frame.fimAcesso - data_frame['inicioAcesso']).dt.seconds
        if group:
            data_frame = self.__group_data(data_frame)
        return data_frame
    
    def __group_data(self,data_frame:pd.DataFrame) -> pd.DataFrame:
        return data_frame.groupby(['fkEcommerce', 'idConsumidor'])\
            .agg(quantidade_acessos=('idAcessos','count'),total_Duracao_sessao=('duracao_sessao', 'sum'))\
            .reset_index()



    def _get_cupons(self):
        requisicao = requests.get(self.domain + "/cupons",  headers={'Authorization': 'Bearer {}'.format(self.token)})
        return  json.loads(requisicao.content)

    

    # def send_evento(self, uri, data):
