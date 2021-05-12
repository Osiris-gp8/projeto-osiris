from os import rename
from DbManager import DbManager
from ApiClient import ApiClient
import pandas as pd

def main():
    db = DbManager("root", "bandtec", "localhost", "processamento_db")
    api = ApiClient("http://localhost")

    data_frame = db.read("SELECT * FROM eventos").astype({'categoria':'category'})
    data_frame['categoria'] = data_frame['categoria'].cat.codes
    data_frame = data_frame.groupby(['idConsumidor','categoria']).agg(
        freq = ('idEvento', 'count')
    )
    print(data_frame)
    data_frame.rename(columns = {
        'categoria' : 'nomeCluster',
        'freq' : 'valor'
    })
    api.sendData("/clusters", data_frame)
    

if __name__ == "__main__":
    main()