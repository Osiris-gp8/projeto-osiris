from typing import List
from pipelines.base_pipeline import Pipeline
from commons.db_manager import DbManager
from commons.api_client import ApiClient
from commons.utils import *
from pipelines.acessos_pipeline import AcessosPipeline
from pipelines.eventos_pipeline import EventosPipeline
from pipelines.cupons_pipeline import CuponsPipeline

def main():
    db = DbManager("root", "bandtec123", "localhost", "processamento_db")
    api = ApiClient("http://localhost:8080")
    
    pipelines: List[Pipeline] = [
        AcessosPipeline(db, api), 
        EventosPipeline(db, api),
        # CuponsPipeline()
    ]
    for p in pipelines:
        p.run()


if __name__ == "__main__":
    main()