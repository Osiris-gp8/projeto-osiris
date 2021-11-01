
import random
import json

class GenerateNames():
    def __init__(self):

        self.marks = ['nike', "adidas", ]

    def __process_data(self, data:dict, mark : str) -> tuple:
        value = random.choice(data[mark]);
        return value['id_categoria'] , f'{mark} {value["nome"]}'


    def __read_file(self) -> str:
        with open('./src/resources/words_mark.json') as f:
            file_data = json.load(f)
            return file_data
            

    def generate_name(self, mark:str) -> str:
        return self.__process_data(self.__read_file(), mark)
