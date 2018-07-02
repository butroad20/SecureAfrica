from ussd.config import settings

def detect_alert_included(text):
    if not text:
        return (False, 'default')
    alert_type = detect_alert_type(text)
    return True, alert_type
    # alert_code = ussd_string.split('*')

def detect_alert_type(alert_code):
    for index, alert in enumerate(settings.ALERTS):
        if str(index + 1) == alert_code:
            return alert

def detect_path(text):
    print('detecting path')
    if '*' in text:
        token = text.split('*')
        print('count', text.count('*'))
        if text.count('*') == 1:
            print('got here!!')
            print('token', token)
            # print level one deep
            if token[-1] == '1':
                next_screen = 'alert_state'
            elif token[-1] == '2':
                next_screen = 'alert_quit_end'
            elif token[-1] == '3':
                next_screen = 'alert_call_end'
        elif text.count('*') == 2:
            next_screen = 'lga'
        elif text.count('*') == 3:
            next_screen = 'area'
        elif text.count('*') == 4:
            next_screen = 'otherinfo'
        elif text.count('*') == 5:
            next_screen = 'complete_end'
        else:
            next_screen = None
        return next_screen
        
    
if __name__ == '__main__':
    tests = ['*384*0110#', '*384*0110*1#']
    for case in tests:
        print(detect_alert_included())
    
