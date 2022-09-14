import Pyro4
import json

import functools

@Pyro4.expose
class Server:
        
    def handle(self, data):
        request = json.loads(data)
        age = request.get('age')
        name = request.get('name')
        gender = request.get('gender')

        if (gender == 'M' and age >= 18) or (gender == 'F' and age >= 21):
            return json.dumps({ 'name': name, 'maiority': True})
        return json.dumps({ 'name': name, 'maiority': False })

Pyro4.Daemon.serveSimple({
    Server: 'Server',
}, host="0.0.0.0", port=9090, ns=False, verbose=True) 