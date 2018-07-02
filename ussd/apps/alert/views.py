from ussd.utils.screens import load_screen
from ussd.config import settings
import os

base_path = os.path.join(os.path.dirname(os.path.realpath(__file__)), 'screens')


def alert_created():
    screen_path = os.path.join(base_path, 'success.json')
    screen = load_screen(screen_path)
    response = screen['text']
    return response

def load_other_views(name):
    print('name', name)
    screen_path = os.path.join(base_path, name + '.json')
    screen = load_screen(screen_path)
    response = screen['text']
    print('loading response ...', response)
    return response

    