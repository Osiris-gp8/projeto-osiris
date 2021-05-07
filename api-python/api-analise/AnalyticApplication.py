from DbManager import DbManager
import pandas as pd

def main():
    db = DbManager("root", "bandtec", "localhost", "processamento_db")
    data_frame = db.read("SELECT * FROM eventos")
    
    data_frame['categoria'] = data_frame['categoria'].astype('category')
    
    data_frame['categoria'] = data_frame['categoria'].cat.codes

    consumidores = data_frame.groupby(['idConsumidor', 'categoria'])
    # .agg({
    #     'categoriaFreq':'count'
    # }).reset_index()
    
    print(consumidores.head())
    

if __name__ == "__main__":
    main()