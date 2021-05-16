from DbManager import DbManager
from ApiClient import ApiClient
import numpy as np
import pandas as pd

def main():
    db = DbManager("root", "bandtec", "localhost", "processamento_db")
    api = ApiClient("http://localhost")

    data_frame = db.read("SELECT * FROM eventos").astype({
        'categoria':'category',
        'sexo' : 'category'
        })
    data_frame['categoria'] = data_frame['categoria'].cat.codes
    data_frame['sexo'] = data_frame['sexo'].cat.codes

    quartis_idade = data_frame['idade'].quantile([0.25, 0.5, 0.75])
    data_frame['faixa_etaria'] = np.where(
        data_frame['idade'].between(0, quartis_idade[0.25]), 'Jovem',
        np.where(data_frame['idade'].between(quartis_idade[0.25], quartis_idade[0.5]), 
        'Adulto',
        'Idoso')
    )

    max_categoria_faixa = data_frame.groupby(['faixa_etaria', 'categoria']).agg({
        'preco' : 'max',
        'idEvento' : 'count'
        }).reset_index()

    print(max_categoria_faixa.head())
    categorias_consumidor = data_frame.groupby(['idConsumidor','categoria']).agg(
        freq = ('idEvento', 'count')
    )

    
    

if __name__ == "__main__":
    main()