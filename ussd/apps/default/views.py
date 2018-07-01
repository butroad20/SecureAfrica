from ussd.utils.screens import load_screen

def default(args):
    return load_screen(app_name='default', screen_name='menu_options')
