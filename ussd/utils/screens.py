import os
import json

from ussd.config import settings

def load_screen(app_name, screen_name):
    screen_path = os.path.join('ussd', settings.screens_path, app_name + '.json')
    with open(screen_path) as f:
        app_screens = json.load(f)
    return app_screens.get(screen_name)
