from .views import default, fetch_id, change_pin, add_card, delete_card

patterns = {
    '1': fetch_id,
    '2': change_pin,
    '3': add_card,
    '4': delete_card
}