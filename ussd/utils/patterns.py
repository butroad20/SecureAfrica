from importlib import import_module
import os

def detect_view(app_name, next_token):
    app_pattern_path = '.'.join(['ussd', 'apps', app_name, 'patterns'])

    app_patterns = import_module(app_pattern_path)
    return app_patterns.patterns.get(next_token)

def load_default_view(app_name):
    app_pattern_path = '.'.join(['ussd', 'apps', app_name, 'views'])
    app_views = import_module(app_pattern_path)
    return app_views.default