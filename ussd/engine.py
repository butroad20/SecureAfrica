from ussd.apps.default.views import landing_page
from ussd.apps.alert.views import alert_created, load_other_views
from ussd.config import settings


"""
Todos:
1. Add fetched data from responses, like .. state, etc
"""

class Controller:
    def route(self, notapp):
        print('notapp at the top', notapp)
        if notapp == 'default':
            return landing_page()
        elif notapp in settings.ALERTS:
            return alert_created()
        
    def path(self, name):
            print('loading other view', name)
            return load_other_views(name)
