from ussd.utils.screens import load_screen

def default(args):
    return load_screen(app_name='wallet', screen_name='menu_options')

def fund_wallet(args):
    if not args:
        return load_screen(app_name='wallet', screen_name='select_card')
    if len(args) == 1:
        return load_screen(app_name='payment', screen_name='payment_amount')
    if len(args) == 2:
        return load_screen(app_name='payment', screen_name='payment_pin')

    print(args)
    return {'text': 'Wallet funded', 'quit': True}

def check_blance(args):
    return load_screen(app_name='wallet', screen_name='wallet_balance')