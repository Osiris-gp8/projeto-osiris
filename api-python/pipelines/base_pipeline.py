from abc import ABC, abstractmethod
from pandas.core.frame import DataFrame
import logging

class Pipeline(ABC):
    
    def __init__(self):
        logging.basicConfig()
        self.logger = logging.getLogger(__name__)
        self.logger.setLevel(logging.INFO)
        
    @abstractmethod
    def get_data(self) -> DataFrame:
        pass
    
    @abstractmethod
    def process_data(self, df: DataFrame) -> DataFrame:
        pass
    
    @abstractmethod
    def save_data(self, df: DataFrame) -> None:
        pass
    
    def run(self):
        df = self.get_data()
        df = self.process_data(df)
        self.save_data(df)