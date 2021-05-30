from DbManager import DbManager
from ApiClient import ApiClient
import numpy as np
import pandas as pd
from  datetime import date, timedelta

def main():
    db = DbManager("root", "bandtec", "localhost", "processamento_db")
    api = ApiClient("http://localhost")

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
    cupons['idConsumidor'] = receber_cupom['idConsumidor']
    cupons['usado'] = False
    cupons['cupomEcommerce'] = False
    cupons['nomeCupom'] = np.random.randint(1, 10, size = len(cupons))
    cupons['nomeCupom'] = 'OSIRIS'+cupons['nomeCupom'].astype(str)
    cupons['dataEmitido'] = date.today()
    cupons['dataValido'] = cupons['dataEmitido'] + timedelta(days = 30)
    cupons['valor'] = receber_cupom['media_preco'] / 100

    print(cupons)
    
    # api.send_data("/cupons" , cupons)
    #db.clean_table("eventos")
    
    categorias_consumidor = data_frame.groupby(['idConsumidor','categoria']).agg(
        freq = ('idEvento', 'count')
    )

if __name__ == "__main__":
    main()