from pandas.core.frame import DataFrame
from pipelines.base_pipeline import Pipeline
from commons.db_manager import DbManager

class MetasPipeline(Pipeline):
    def __init__(self, db: DbManager, output_db: DbManager):
        super().__init__()
        self.db = db
        self.output_db = output_db
    
    def get_data(self) -> DataFrame:
        self.logger.info("Getting data from database")
        df = self.db.read("SELECT * FROM metas").astype({
            "dataInicio": "datetime64[ns]",
            "dataFim": "datetime64[ns]",
            "tipo": "category"
        })
        
        return df
    
    def process_data(self, df: DataFrame) -> DataFrame:
        df.rename(
            columns={
                "dataInicio": "data_inicio",
                "dataFim": "data_fim",
                "fkEcommerce": "ecommerce_id_ecommerce"
            }, 
            inplace=True
        )
        df.drop(columns = "idMeta", inplace=True)
        return df
    
    def save_data(self, df: DataFrame) -> None:
        self.output_db.insert(df, "meta")
        self.logger.info("Saved successfully")