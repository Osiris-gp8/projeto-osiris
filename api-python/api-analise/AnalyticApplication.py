from DbManager import DbManager
from ApiClient import ApiClient
import numpy as np
import pandas as pd
from  datetime import date

def main():
    db = DbManager("root", "bandtec123", "localhost", "processamento_db")
    api = ApiClient("http://localhost")

    data_frame = db.read("SELECT * FROM eventos").astype({
        'categoria':'category',
        'sexo' : 'category',
        'dataNascimento':'datetime64[ns]'
        })
    data_frame['categoria'] = data_frame['categoria'].cat.codes
    data_frame['sexo'] = data_frame['sexo'].cat.codes
    data_frame['idade'] = date.today().year - data_frame['dataNascimento'].dt.year - (
    data_frame['dataNascimento'].apply( lambda x : (date.today().month, date.today().day) < (x.month, x.day)) )
    quartis_idade = data_frame['idade'].quantile([0.25, 0.5, 0.75])
    data_frame['faixa_etaria'] = np.where(
        data_frame['idade'].between(0, quartis_idade[0.25]), 'Primeira Faixa',
        np.where(data_frame['idade'].between(quartis_idade[0.25], quartis_idade[0.5]), 
        'Segunda Faixa',
        'Terceira Faixa')
    )

    max_categoria_faixa = data_frame[data_frame['statusEvento'] == 1].groupby(['faixa_etaria', 'categoria']).agg(
        maxPreco = ('preco' , 'max'),
        Quantidade = ('idEvento' , 'count')
        ).reset_index()

    print(max_categoria_faixa.head(50))
    categorias_consumidor = data_frame.groupby(['idConsumidor','categoria']).agg(
        freq = ('idEvento', 'count')
    )

    
    

if __name__ == "__main__":
    main()