import pandas as pd
from sqlalchemy import create_engine
import logging
import os
import pathlib

logging.basicConfig()
logger = logging.getLogger(__name__)
logger.setLevel(logging.INFO)

"""
    A dict containing the queries for the extraction
    and its date fields to be parsed
"""
extractions = {
    "evento": "select * from evento",
    "acesso": "select * from acesso"
}


config = {
    "username": "root",
    "password": "bandtec",
    "host": "18.211.88.253",
    "db": "osiris"
}

connection_str = 'mysql+mysqlconnector://{username}:{password}@{host}/{db}'.format(**config)
con = create_engine(connection_str)

def extract(query, path):
    logger.info(f"Executing {query}")
    result = pd.read_sql(query, con = con)
    logger.info(f"Saving {path}")
    result.to_pickle(path)
    logger.info("Saved successfully")

outdir = "./analysis/ml_models/datasets"
if not os.path.exists(outdir):
    pathlib.Path(outdir).mkdir(parents=True, exist_ok=True)

for t, q in extractions.items():
    extract(q, f"{outdir}/{t}.pkl")