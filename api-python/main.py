from typing import List
from pipelines.base_pipeline import Pipeline
from commons.db_manager import DbManager, DbType
from commons.utils import *
from pipelines.acessos_pipeline import AcessosPipeline
from pipelines.eventos_pipeline import EventosPipeline
from pipelines.cupons_pipeline import CuponsPipeline
from pipelines.metas_pipeline import MetasPipeline
from pipelines.fill_cupons_pipeline import FillCupons

def main():
    db = DbManager("admin", "bandtec", "127.0.0.1", "processamento_db", DbType.MYSQL)
    remote_db = DbManager("root", "bandtec", "3.94.235.84:3306", "osiris", DbType.MYSQL)
    pipelines: List[Pipeline] = [
        AcessosPipeline(db, remote_db), 
        EventosPipeline(db, remote_db),
        CuponsPipeline(remote_db),
        FillCupons(remote_db),
        MetasPipeline(db, remote_db)
    ]
    for p in pipelines:
        p.run()


if __name__ == "__main__":
    main()