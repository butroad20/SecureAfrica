from ussd.utils.screens import load_screen
from ussd.config import settings
import os

base_path = os.path.join(os.path.dirname(os.path.realpath(__file__)), 'screens')


def landing_page():
    screen_path = os.path.join(base_path, 'home.json')
    screen = load_screen(screen_path)
    alert_details = '\n'.join(['{0}. {1}'.format(str(index + 1), alert) for index, alert in enumerate(settings.ALERTS)])

    response = screen['text'].replace('{{ alert_list }}', alert_details) 
    return response

 