from datetime import datetime
from pipelines.base_pipeline import Pipeline
from commons.db_manager import DbManager
from commons.utils import *
from commons.location import LocationBR

class AcessosPipeline(Pipeline):
    def __init__(self, db: DbManager, output_database: DbManager, group: bool = False):
        super().__init__()
        self.db = db
        self.output_database = output_database
        self.group = group
        self.location_df = LocationBR().get_location_data_frame()[["uf", "cidade"]]

    def get_data(self) -> pd.DataFrame:
        self.logger.info("Getting data from database")
        data_frame = self.db.read("SELECT * FROM acessos").astype({
            'fimAcesso':'datetime64[ns]', 
            'inicioAcesso':'datetime64[ns]', 
        })
        return data_frame
        
    
    def process_data(self, df: pd.DataFrame) -> pd.DataFrame:
        self.logger.info("Processing data")
        data_frame = self.__format_to_request(df)
        data_frame = data_frame.join(self.location_df, on = "localidade")
        data_frame.drop(columns = "localidade", inplace=True)
        if self.group:
            data_frame = self.__group_data(df)
        return data_frame

    def __group_data(self,data_frame:pd.DataFrame) -> pd.DataFrame:
        data_frame['duracao_sessao'] = self.__get_duration(data_frame) 
        data_group = data_frame.groupby(['fkEcommerce', 'idConsumidor'])\
            .agg(quantidade_acessos=('idAcessos','count'),total_duracao_sessao=('duracao_sessao', 'sum'))\
            .reset_index()
        data_group['created_at'] = datetime.now()
        return data_group
    
    def __get_duration(self, data_frame: pd.DataFrame):
        return (data_frame["fimAcesso"] - data_frame['inicioAcesso']).dt.seconds
    
    def save_data(self, df: pd.DataFrame) -> None:
        self.logger.info("Saving data")
        self.output_database.insert(df, "acesso", if_exists="append")
        self.logger.info("Saved successfully")
    
    def __format_to_request(self, df: pd.DataFrame) -> pd.DataFrame:
        df.rename(columns = {
            'idConsumidor':'id_consumidor_ecommerce',
            'fkEcommerce':'ecommerce_id_ecommerce',
            'idAcesso':'id_acessos',
            'inicioAcesso':'inicio_acesso',
            'fimAcesso':'fim_acesso'
        },
        inplace=True)
        return df