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
    data_frame['dataNascimento'].apply( lambda x : (date.today().month, date.today().day) < (x.month, x.day)) )
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
    cupons['nomeCupom'] = 'osiris'+cupons['nomeCupom'].astype(str)
    # cupons['nomeCupom'] =  'osiris %d' %  cupons['nomeCupom']
    cupons['dataEmitido'] = date.today()
    cupons['dataValido'] = cupons['dataEmitido'] + timedelta(days = 30)
    cupons['idConsumidor'] = data_frame.merge(max_categoria_faixa).query('maxPreco == preco').reset_index()['idConsumidor']
    cupons
    print(cupons)
    # cupons['idConsumidorEcommerce'] = data_frame[condicional]
    # api.sendData("/cupons" , cupons)


    # sem_cupom = data_frame[data_frame['fkCupom'] == 0]
    # print(sem_cupom)

    categorias_consumidor = data_frame.groupby(['idConsumidor','categoria']).agg(
        freq = ('idEvento', 'count')
    )

    
    

if __name__ == "__main__":
    main()