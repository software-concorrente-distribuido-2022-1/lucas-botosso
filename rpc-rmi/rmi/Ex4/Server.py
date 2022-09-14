import Pyro4
import json

@Pyro4.expose
class Server:
        
    def handle(self, data):
        def response_weight(weight):
            return json.dumps({ 'weight': weight})

        def woman(height):
            return response_weight((72.7 * height) - 58)

        def man(height):
            return response_weight((62.1 * height) - 44.7)


        request = json.loads(data)
        height = request.get('height')
        gender = request.get('gender')

        if gender == 'M':
            return man(height)
        elif gender == 'F':
            return woman(height)

Pyro4.Daemon.serveSimple({
    Server: 'Server',
}, host="0.0.0.0", port=9090, ns=False, verbose=True