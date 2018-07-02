import requests
from ussd.config import settings

def create_alert_in_db(phone_number, alert_type):
    data = {'phone': phone_number, 'type': alert_type}
    print('data to be sent', data)
    r = requests.post(settings.ALERT_API_ADD_ENDPOINT, data)
    print(r.content)

def update_alert_in_db(phone_number, data):
    data = {
        'phonenumber': phone_number,
        'state': data[0],
        'lga': data[1],
        'address': data[2],
        'message': data[3]
    }
    url = settings.ALERT_API_UPDATE_ENDPOINT + '/' + phone_number
    print('update url', url)
    r = requests.post(settings.ALERT_API_UPDATE_ENDPOINT + '/' + phone_number, data)
    print(r.content)

def extract_data(text):
    if text.count('*') == 1:
        # only alert type and phone number can be detected
        None
    elif text.count('*') > 1:
        data = text.split('*')[2:]
        return data