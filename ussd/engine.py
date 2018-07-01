from ussd.config import settings
from ussd.utils.patterns import detect_view, load_default_view

class Session:
    def __init__(self, text):
        self.text = text
        self.response = self._set_up()

    def _set_up(self):
        text_token = self.text.split('*')
        
        app_id = text_token[0]
        app = settings.ussd_patterns.get(app_id)

        if len(text_token) == 1:
            next_token = ''
        if len(text_token) > 1:
            next_token = text_token[1]

        view = detect_view(app_name=app, next_token=next_token)
        args = text_token[2:] if len(text_token)>2 else None

        if not view:
            view = load_default_view(app_name=app)
            args = text_token[1:]
        
        print('app:', app, 'args', args)
        return view(args)
        
if __name__ == '__main__':
    c = Controller('2*23453*100*1234')
    print(c.app, c.view, c.args)

    c = Controller('2')
    print(c.app, c.view, c.args)

    c = Controller('3')
    print(c.app, c.view, c.args)