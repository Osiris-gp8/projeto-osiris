
import random
import json

class GenerateNames():
    def __init__(self):

        self.marks = ['nike', "adidas", ]

    def __process_data(self, data) -> str:
        # mark = random.choice(self.marks)
        # list_names = self.names[mark].split('|')
        # name = random.choice(list_names)
        # return f'{mark.capitalize()} {name}'
        for mark in data:
            for value in data[mark]:
                print(f'{mark} {value}') 


    def __read_file(self) -> str:
        with open('./src/resources/words_mark.json') as f:
            file_data = json.load(f)
            return file_data
            

    def generate_name(self) -> str:
        return self.__process_data(self.__read_file())
        # return self.__read_file()
