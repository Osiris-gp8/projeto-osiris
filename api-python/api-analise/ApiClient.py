import requests
class ApiClient:
    def __init__(self, domain):
        self.domain = domain

    def send_data(self, uri, data):
        data_json = data.to_json(orient = 'table')
        requests.post(url = (self.domain + uri), json = data_json)