from commons.api_client import ApiClient
from commons.db_manager import DbManager, DbType

import traceback
import pytest

def test_connection_api():
    ip = "http://localhost:8080"
    try:
        connection = ApiClient(ip)
    except :
        pytest.fail(f"Connection to API failed: {ip}")
        
def test_connection_local_database():
    ip = "http://localhost:8080"
    user = "root"
    password = "bandtec"
    db = "processamento_db"
    try:
        connection = DbManager(ip, password, user, db, DbType.MYSQL)
    except :
        print(traceback.format_exc())
        pytest.fail(f"Connection to database failed {ip, user, password, db}")
        
def test_connection_remote_database():
    # Azure
    
    ip = "tcp:server-osiris.database.windows.net"
    user = "osiris-adm"
    password = "#Gfgrupo8"
    db = "db_osiris"
    try:
        connection = DbManager(ip, password, user, db, DbType.MSSQL)
        df = connection.read("SELECT 1")
        print(df.head())
    except :
        print(traceback.format_exc())
        pytest.fail(f"Connection to database failed {ip, user, password, db}")