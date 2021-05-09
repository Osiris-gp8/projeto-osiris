from DbManager import DbManager
import pandas as pd

def main():
    db = DbManager("root", "bandtec123", "localhost", "processamento_db")
    data_frame = db.read("SELECT * FROM eventos").astype({'categoria':'category'})
    data_frame['categoria'] = data_frame['categoria'].cat.codes
    data_frame_categoria = data_frame.groupby(['idConsumidor','categoria'])['idEvento'].count().reset_index()
    data_frame_categoria.sort_values('idEvento')
    # .agg({
    #     'categoriaFreq':'count'
    # }).reset_index()
    
    print(consumidores.head())
    

if __name__ == "__main__":
    main()