from xmlrpc.server import SimpleXMLRPCServer
import random

class Card:

    def __init__(self, value, naipe):
        self.value = value
        self.naipe = naipe

    def get_description(self):
        print(self.get_naipe())
        print(self.naipe)
        return f"{self.get_value()} de {self.get_naipe()}"

    def get_value(self):
        if self.value == 1:
            return 'Ás'
        if self.value == 11:
            return 'Valete'
        if self.value == 12:
            return 'Rainha'
        if self.value == 13:
            return 'Rei'
        return self.value
    
    def get_naipe(self):
        if self.naipe == 1:
            return 'ouros'
        elif self.naipe == 2:
            return 'paus'
        elif self.naipe == 3:
            return 'copas'
        elif self.naipe == 4:
            return 'espadas'

def handler(data):
    print('Entrou aqui')

    card = Card(random.randint(1, 14), random.randint(1, 5))
    desc = card.get_description()
    print(desc)
    return desc

server = SimpleXMLRPCServer(("localhost", 8000))
print("Listening on port 8000...")
server.register_function(handler, "consume")
server.serve_forever()