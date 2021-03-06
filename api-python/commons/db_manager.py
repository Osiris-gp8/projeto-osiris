from enum import Enum
from sqlalchemy import create_engine
import pandas as pd
import logging

class DbType(Enum):
    MYSQL = 'mysql+mysqlconnector://{username}:{password}@{host}/{db}'
    MSSQL = ('Driver={{ODBC Driver 13 for SQL Server}};'
            'Server={host};'
            'Database={db};'
            'UID={username};'
            'PWD={password};'
            'Trusted_Connection=yes;')

class DbManager:
    def __init__(self, username, password, host, db, type: DbType):
        logging.basicConfig(level=logging.INFO)
        self.type = type
        self.connection_str = self.type.value.format(
            username = username, password = password,
            db = db, host= host
        )

    def _create_connection(self):
        if self.type is DbType.MYSQL:
            return create_engine(self.connection_str)
        elif self.type is DbType.MSSQL:
            # ! Deprecated
            raise NotImplementedError()
        
    def read(self, query):
        con = self._create_connection()
        logging.info('Connected successfully')
        result = pd.read_sql(query, con = con, parse_dates=["dataCompra"])
        data_frame = pd.DataFrame(result)
        data_frame.columns = result.keys()
        return data_frame

    def clean_table(self, table):
        # ! Deprecated
        engine = create_engine(self.connection_str)
        print('Limpando banco de processamento')
        with engine.connect() as con:
            con.execute("DELETE FROM {}".format(table))
            

    def insert(self, df: pd.DataFrame, table: str, if_exists = "append"):
        logging.info("Inserting data")
        con = self._create_connection()
        df.to_sql(table, con, if_exists = if_exists, index = False)
