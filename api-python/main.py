from numpy.lib.shape_base import split
from db_manager import DbManager
from api_client import ApiClient
from commons.utils import *
import numpy as np
import pandas as pd
from  datetime import date, timedelta
import json
def main():
    db = DbManager("root", "bandtec123", "localhost", "processamento_db")
    api = ApiClient("http://localhost:8080")

    data_frame = db.read("SELECT * FROM eventos").astype({
        'categoria':'category',
        'sexo' : 'category',
        'dataNascimento':'datetime64[ns]'
        })
    data_frame['categoria'] = data_frame['categoria'].cat.codes
    data_frame['sexo'] = data_frame['sexo'].cat.codes
    data_frame['idade'] = date.today().year - data_frame['dataNascimento'].dt.year - (
    data_frame['dataNascimento']\
        .apply( lambda x : (date.today().month, date.today().day) < (x.month, x.day)) )

    quartis_idade = data_frame['idade'].quantile([0.25, 0.5, 0.75])
    data_frame['faixa_etaria'] = np.where(
        data_frame['idade'].between(0, quartis_idade[0.25]), 'Primeira Faixa',
        np.where(data_frame['idade'].between(quartis_idade[0.25], quartis_idade[0.5]), 
        'Segunda Faixa',
        'Terceira Faixa')
    )

    print(data_frame)
    max_categoria_faixa = data_frame[data_frame['statusEvento'] == 1]\
        .groupby(['faixa_etaria', 'categoria'], as_index=False).agg(
            maxPreco = ('preco' , 'max'),
            quantidade = ('idEvento' , 'count')
            ).reset_index()
    
    cupons = pd.DataFrame()
    cupons['valor'] = max_categoria_faixa['maxPreco'] / 100 # 1% do valor
    cupons['usado'] = False
    cupons['cupomEcommerce'] = False
    cupons['nomeCupom'] = np.random.randint(1, 10, size = len(max_categoria_faixa))
    cupons['nomeCupom'] = 'OSIRIS'+cupons['nomeCupom'].astype(str)
    cupons['dataEmitido'] = date.today()
    cupons['dataValido'] = cupons['dataEmitido'] + timedelta(days = 30)
    cupons['idConsumidor'] = data_frame.merge(max_categoria_faixa)\
        .query('maxPreco == preco').reset_index()['idConsumidor']
    cupons['ecommerce'] = ""

    # api.send_data("/cupons" , cupons)


    sem_cupom = data_frame[data_frame['fkCupom'] == 0]
    sem_cupom = sem_cupom[~sem_cupom['idConsumidor'].isin(cupons['idConsumidor'])].reset_index()
    sem_cupom_freq = sem_cupom.groupby('idConsumidor').agg(
        freq = ('idEvento', 'count'),
        media_preco = ('preco', 'mean')
        ).reset_index()
    freq_quartis = sem_cupom_freq['freq'].quantile([0.25, 0.50, 0.75])

    receber_cupom = sem_cupom_freq[ sem_cupom_freq['freq'] > freq_quartis[0.50] ].reset_index()

    cupons = pd.DataFrame()
    cupons['idConsumidorEcommerce'] = receber_cupom['idConsumidor']
    cupons['usado'] = False
    cupons['cupomEcommerce'] = False
    cupons['nomeCupom'] = np.random.randint(1, 10, size = len(cupons))
    cupons['nomeCupom'] = 'OSIRIS'+cupons['nomeCupom'].astype(str)
    cupons['dataEmitido'] = date.today()
    cupons['dataValidado'] = cupons['dataEmitido'] + timedelta(days = 30)
    cupons['valor'] = receber_cupom['media_preco'] / 100

    print( cupons.to_json(orient='records'))
    
    api.send_data("/cupons/list" , cupons)
    
    

    eventos = format_eventos(data_frame)
    api.send_data("/eventos/list", eventos)
    print(eventos)
    # print(random_dates("01-01-2021", "31-01-2021"))
    # db.clean_table("eventos")
    
    categorias_consumidor = data_frame.groupby(['idConsumidor','categoria']).agg(
        freq = ('idEvento', 'count')
    )


def format_eventos(dataFrame):
    newDf = pd.DataFrame()
    newDf['idConsumidorEcommerce'] = dataFrame['idConsumidor']
    newDf['nomeProduto']= dataFrame['nome']
    newDf['preco']= dataFrame['preco']
    newDf['nomeCategoria']= dataFrame['categoria']
    newDf['dataCompra']= dataFrame['dataCompra']
    newDf['dominioStatus'] =  dataFrame['statusEvento']
    newDf['ecommerce'] = ''
    newDf['cupom'] = ''

    return newDf

if __name__ == "__main__":
    main()