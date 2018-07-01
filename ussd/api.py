from flask import Flask, request, make_response
from flask_restful import Resource, Api
import json

from ussd.engine import Session

app = Flask(__name__)
api = Api(app)


class Api(Resource):
    def get(self):
        with open('ussd/screens/default.json') as f:
            res = json.load(f)
        return res

    def post(self):
        session_id = request.form.get('sessionId')
        service_code = request.form.get('serviceCode')
        phone_number = request.form.get('phoneNumber')
        text = request.form.get('text')
        print(session_id, service_code, phone_number, text)
        print(request.form)
        convo = Session(text)
        quit = convo.response.get('quit')
        prefix = 'END ' if quit else 'CON '
        reply = prefix + convo.response['text']
        return make_response(reply, 200, {'Content-Type' :'text/plain'})

api.add_resource(Api, '/')

if __name__ == '__main__':
    app.run(debug=True)