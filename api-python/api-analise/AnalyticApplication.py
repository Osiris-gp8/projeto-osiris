from DbManager import DbManager
import pandas as pd

def main():
    db = DbManager("root", "bandtec", "localhost", "processamento_db")
    data_frame = db.read("SELECT * FROM eventos")
    print(data_frame)

if __name__ == "__main__":
    main()