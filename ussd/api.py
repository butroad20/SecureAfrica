from flask import Flask, request, make_response
from flask_restful import Resource, Api

import os
import sys
import json

base_path = os.path.dirname(os.path.dirname(os.path.join('..', os.path.realpath(__file__))))
sys.path.append(base_path)

# print(sys.path)

from ussd.utils.patterns import detect_alert_included, detect_path
from ussd.utils.db import extract_data, create_alert_in_db, update_alert_in_db
from ussd.engine import Controller


# from ussd.engine import Session

app = Flask(__name__)
api = Api(app)


class Api(Resource):
    def get(self):
        return {"message": "Hello World!"}

    def post(self):
        session_id = request.form.get('sessionId')
        service_code = request.form.get('serviceCode')
        phone_number = request.form.get('phoneNumber')
        text = request.form.get('text')
        #print('text', text)
        print(session_id, service_code, phone_number, text)
        
        alert_included, alert_type = detect_alert_included(text)
        path = detect_path(text)
        print('path', path)

        print(alert_included, alert_type)
        print('text', text)

        c = Controller()
        r = c.route(alert_type)
        print('r', r)

        if r and alert_type != 'default':
            print('creating alert in db')
            create_alert_in_db(phone_number, alert_type)

        if not r:
            print('r is null, getting path', path)
            r = c.path(path)

        quit = False

        if path and path.endswith('end'):
            quit = True
            data = extract_data(text)
            if data:
                print('updating alert in db')
                update_alert_in_db(phone_number=phone_number, data=data)                

        
        prefix = 'END ' if quit else 'CON '
        reply = prefix + r
        return make_response(reply, 200, {'Content-Type' :'text/plain'})


api.add_resource(Api, '/')

if __name__ == '__main__':
    app.run(debug=True, port=8000)
