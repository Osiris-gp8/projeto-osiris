from sqlalchemy import create_engine
import pandas as pd
class DbManager:

    def __init__(self, username, password, host, db):
        self.connection_str = f'mysql+mysqlconnector://{username}:{password}@{host}/{db}'

    def read(self, query):
        connection = create_engine(self.connection_str)
        print(f'Conectado ao banco')
        result = pd.read_sql(query, con = connection)
        data_frame = pd.DataFrame(result)
        data_frame.columns = result.keys()
        return data_frame