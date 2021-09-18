from numpy import datetime64
import requests
import json
from commons.utils import *

from requests.api import request 
class ApiClient:
    def __init__(self, domain):
        self.domain = domain
        self.token = ''
        self.login()
    def login(self, uri='/auth'):
        user = "user8"
        pwd = "user8"
        data_json = {
            "login":user,
            "senha":pwd
        }

        response = requests.post(url = (self.domain + uri), json = data_json).content
        self.token = json.loads(response)['token']
        

    def send_data(self, uri, data):
        data_json = data
        if(type(data) != list):
            data_json = self.format_data(uri, data)
            data_json = json.loads(data_json.to_json(orient='records', date_format="iso"))
        print("Aqui " + self.domain)
        # self.login("/auth")
        tipo = "Cupom"
        if(uri == "/eventos/list"):
            tipo = "Evento"
        print(f"Inserindo dados de {tipo} no BD...")
        requisicao =  requests.post(url = (self.domain + uri),  headers={'Authorization': 'Bearer {}'.format(self.token)},json = data_json)
        if(requisicao.ok):
            print("Inserção feita com sucesso")
        

    def format_data(self, uri, df):
        if(uri == "/eventos/list"):
            df['cupom'] = pd.DataFrame(self._get_cupons())['idCupom'].apply(lambda x: {'idCupom': x})
            df.loc[df['dominioStatus'] == 1, 'dominioStatus'] = 2 
            df.loc[df['dominioStatus'] == 2, 'dominioStatus'] = 6 
            df['dominioStatus'] = df['dominioStatus'].apply(lambda x: {'idDominioStatus': x})
        df = fill_dataFrame(df, "ecommerce",{"idEcommerce":1} )
        return df

    def _get_cupons(self):
        requisicao = requests.get(self.domain + "/cupons",  headers={'Authorization': 'Bearer {}'.format(self.token)})
        return  json.loads(requisicao.content)

    

    # def send_evento(self, uri, data):
