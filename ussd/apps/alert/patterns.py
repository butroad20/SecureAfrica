from .views import *

{
    '1': 'load_info',
    '2': 'quit',
    '3': 'place_call'
    '*'
}

def load_view(name):
    if name == 'quit':
        pass

    

def load_info_view(in_pattern):
    pattern = '([1-7])(\*)()([1-3])'
    