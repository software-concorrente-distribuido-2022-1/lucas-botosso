import Pyro4
import json

@Pyro4.expose
class Server:
        
    def handle(self, data):
        def response(liquid_salary, name, level):
            return json.dumps({ 'liquid_salary': liquid_salary, 'name': name, 'level': level})

        request = json.loads(data)
        name = request.get('name')
        level = request.get('level')
        dependents = request.get('dependents')
        salary = request.get('salary')

        fee = 0
        if level == 'A':
            fee = 0.08 if dependents > 0 else 0.03
        if level == 'B':
            fee = 0.10 if dependents > 0 else 0.05
        if level == 'C':
            fee = 0.15 if dependents > 0 else 0.08
        if level == 'D':
            fee = 0.17 if dependents > 0 else 0.10
        
        return response(salary - (salary * fee), name, level)

Pyro4.Daemon.serveSimple({
    Server: 'Server',
}, host="0.0.0.0", port=9090, ns=False, verbose=True) 