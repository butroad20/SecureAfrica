import os
import json

# base_path = os.path.dirname(os.path.dirname(os.path.join('..', os.path.realpath(__file__))))
# print(base_path)

def load_screen(path):
    with open(path, 'r') as f:
        response = json.load(f)
    return response