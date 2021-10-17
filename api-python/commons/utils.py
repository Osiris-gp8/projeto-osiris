import pandas as pd
import numpy as np
import requests
import json
import os

def random_dates(start, end, n=10):
    start_u = start.value//10**9
    end_u = end.value//10**9
    return pd.to_datetime(np.random.randint(start_u, end_u, n), unit='s')

def fill_dataFrame( data, column, value):
        data[column] = list([value]) * data.shape[0]
        return data

def normalize_data(value, data, column):
    return data.loc[data['idConsumidor'] == value].iloc[0][column]

def get_location(data_frame:pd.DataFrame) -> pd.Series:
    if(not os.path.exists('./data.json')):
        data = json.loads(requests\
            .get("https://servicodados.ibge.gov.br/api/v1/localidades/municipios").text)
        with open('data.json', 'w', encoding='utf-8') as f:
            f.write(cidades_list, f, ensure_ascii=False, indent=4)
    with open('data.json', 'r') as openfile:
        data = json.load(openfile)    