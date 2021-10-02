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
    ip = "localhost:3306"
    user = "root"
    password = "bandtec"
    db = "osiris"
    try:
        connection = DbManager(
            host = ip, password = password, username = user, db = db, type = DbType.MYSQL
        )
        connection.read("SELECT 1")
    except :
        print(traceback.format_exc())
        pytest.fail(f"Connection to database failed {ip, user, password, db}")
        

def test_connection_remote_database_aws():
    # MySql
    ip = "3.233.173.254"
    user = "root"
    password = "bandtec"
    db = "osiris"
    try:
        connection = DbManager(username=user, password=password, host=ip, db=db, type= DbType.MYSQL)
        df = connection.read("SELECT 1")
        print(df.head())
    except :
        print(traceback.format_exc())
        pytest.fail(f"Connection to database failed {ip, user, password, db}")

@pytest.mark_skip(reason = "Azure database is no longer supported")
def test_connection_remote_database():
    # Azure
    # ! Deprecated
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