from ussd.utils.screens import load_screen

def default(args):
    if not args:
        return load_screen(app_name='payment', screen_name='payment_vendor')
    if len(args) == 1:
        return load_screen(app_name='payment', screen_name='payment_amount')
    if len(args) == 2:
        return load_screen(app_name='payment', screen_name='payment_pin')

    print(args)
    return {'text': 'Payment processed', 'quit': True}