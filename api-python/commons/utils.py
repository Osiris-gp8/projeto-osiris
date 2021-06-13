import pandas as pd
import numpy as np

def random_dates(start, end, n=10):
    start_u = start.value//10**9
    end_u = end.value//10**9
    return pd.to_datetime(np.random.randint(start_u, end_u, n), unit='s')

def fill_dataFrame( data, column, value):
        data[column] = list([value]) * data.shape[0]
        return data
