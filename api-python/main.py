from typing import List
from pipelines.base_pipeline import Pipeline
from commons.db_manager import DbManager, DbType
from commons.utils import *
from pipelines.acessos_pipeline import AcessosPipeline
from pipelines.eventos_pipeline import EventosPipeline
from pipelines.cupons_pipeline import CuponsPipeline

def main():
    db = DbManager("root", "bandtec", "localhost", "processamento_db", DbType.MYSQL)
    remote_db = DbManager("admin", "bandtec", "18.211.88.253", "osiris", DbType.MYSQL)
    pipelines: List[Pipeline] = [
        # AcessosPipeline(db, remote_db), 
        # EventosPipeline(db, remote_db),
        # CuponsPipeline(remote_db)
    ]
    for p in pipelines:
        p.run()


if __name__ == "__main__":
    main()