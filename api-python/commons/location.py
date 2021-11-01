import requests
import json
import os
import pandas as pd


class LocationBR():
    def __init__(self):
        self.file_name = './temp/localidades.json'
        self.data = self.__read_file()

    def get_location_data_frame(self) -> pd.DataFrame:
        return self.data

    def __read_file(self):
        if(not os.path.exists(f'{self.file_name}')):
            return self.__write_file()
        return pd.read_json(f'{self.file_name}', encoding='UTF-8')

    def __write_file(self):
        data = json.loads(
                requests.get("https://servicodados.ibge.gov.br/api/v1/localidades/municipios").text
            )
        data = self.__process_data(data)
        frame = pd.DataFrame(data=data)
        if not os.path.exists(self.file_name):
            os.mkdir('./temp')
        frame.to_json(self.file_name)
        return frame

    def __process_data(self, data_frame):
        cidade_list = []
        for cont, cidade in enumerate(data_frame):
            cidade_list.append(
                {
                    'id': cont,
                    'uf': cidade['microrregiao']['mesorregiao']['UF']['sigla'],
                    'cidade': cidade['nome']
                }
            )
        return cidade_list
