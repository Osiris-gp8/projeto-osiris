from pandas.core.frame import DataFrame
from commons.db_manager import DbManager
from pipelines.base_pipeline import Pipeline

class FillCupons(Pipeline):
    
    def __init__(self, database: DbManager, frac = 0.7):
        super().__init__()
        self.database = database
        self.frac = frac # representa a fração dos cupons gerados que será usada
    
    def get_data(self) -> DataFrame:
        df = self.database.read("SELECT * FROM evento")
        return df
    
    def process_data(self, df: DataFrame) -> DataFrame:
        cupons_gerados = self.database.read("SELECT id_cupom FROM cupom")
        cupons_usados = cupons_gerados.sample(frac = self.frac)
        eventos_com_cupom = df.sample(n = cupons_usados.shape[0])
        df.loc[eventos_com_cupom.index, "cupom_id_cupom"] = cupons_usados["id_cupom"].values
        return df
    
    def save_data(self, df: DataFrame) -> DataFrame:
        self.database.insert(df, "evento", if_exists="replace")