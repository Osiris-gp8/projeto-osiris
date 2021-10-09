import requests
import json
import os
import pandas as pd


class LocationBR():
    def __init__(self):
        self.file_name = './data.json'
        self.data = self.__read_file()

    def get_location_data_frame(self) -> pd.DataFrame:
        return self.data

    def __read_file(self):
        if(not os.path.exists(f'./{self.file_name}')):
            return self.__write_file()
        # file_data = open(f'./{self.file_name}')
        return pd.read_json(f'./{self.file_name}', encoding='UTF-8')

    def __write_file(self):
        data = json.loads(requests
                               .get("https://servicodados.ibge.gov.br/api/v1/localidades/municipios").text)
        data = self.__process_data(data)
        with open('data.json', 'w', encoding='utf-8') as f:
            json.dump(data, f, ensure_ascii=False, indent=4)
        return pd.DataFrame(data=data)

    def __process_data(self, data_frame):
        cidade_list = []
        cont = 0
        for cidade in data_frame:
            cidade_list.append(
                {
                    'id': cont,
                    'UF': cidade['microrregiao']['mesorregiao']['UF']['sigla'],
                    'nome': cidade['nome']
                }
            )
            cont = cont + 1
        return cidade_list
