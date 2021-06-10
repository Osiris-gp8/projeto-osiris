from numpy import datetime64
import requests
import json

from requests.api import request 
class ApiClient:
    def __init__(self, domain):
        self.domain = domain
        self.token = ''
    def login(self, uri):
        user = "user8"
        pwd = "user8"
        data_json = {
            "login":user,
            "senha":pwd
        }

        response = requests.post(url = (self.domain + uri), json = data_json).content
        self.token = json.loads(response)['token']
        


    def send_data(self, uri, data):
        data_json = json.loads(data.to_json(orient='records', date_format="iso"))
        print("Aqui " + self.domain)
        self.login("/auth")
        
        cripto = {'authorization': f'Bearer {self.token}'}
        requisicao =  requests.post(url = (self.domain + uri),  headers={'Authorization': 'Bearer {}'.format(self.token)},json = data_json)
        print(requisicao)