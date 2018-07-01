from ussd.utils.screens import load_screen

def default(args):
    return load_screen(app_name='account', screen_name='account_menu')

def fetch_id(args):
    return load_screen(app_name='account', screen_name='fetch_id')

def change_pin(args):
    if not args:
        return load_screen(app_name='account', screen_name='account_old_pin')
    if len(args) == 1:
        return load_screen(app_name='account', screen_name='account_new_pin')
    if len(args) == 2:
        return load_screen(app_name='account', screen_name='confirm_new_pin')
    # change the pin
    return {"text": "Pin successfully changed", "quit": True}

def add_card(args):
    if not args:
        return load_screen(app_name='account', screen_name='add_card_number')
    if len(args) == 1:
        return load_screen(app_name='account', screen_name='card_expiry_month')
    if len(args) == 2:
        return load_screen(app_name='account', screen_name='card_expiry_year')
    if len(args) == 3:
        return load_screen(app_name='account', screen_name='card_pin')
    
    # verify card details
    # if not succesful, send errror message instead
    return {"text": "Card Successfully added", "quit": True}

def delete_card(args):
    if not args:
        return load_screen(app_name='account', screen_name='select_card')
    return {"text": "Card details succesfully removed", "quit": True}