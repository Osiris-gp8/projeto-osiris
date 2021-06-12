from sqlalchemy import create_engine
import pandas as pd
class DbManager:

    def __init__(self, username, password, host, db):
        self.connection_str = f'mysql+mysqlconnector://{username}:{password}@{host}/{db}'

    def read(self, query):
        engine = create_engine(self.connection_str)
        print(f'Conectado ao banco')
        result = pd.read_sql(query, con = engine, parse_dates=["dataCompra"])
        data_frame = pd.DataFrame(result)
        data_frame.columns = result.keys()
        return data_frame

    def clean_table(self, table):
        engine = create_engine(self.connection_str)
        print(f'Limpando banco de processamento')
        with engine.connect() as con:
            con.execute("DELETE FROM {}".format(table))