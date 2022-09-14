from xmlrpc.server import SimpleXMLRPCServer
import json

OPR_FEE = 0.18
DVLP_FEE = 0.20

def handler(data):
    request = json.loads(data)

    def new_salary(request, fee):
        return request.get('salary') + (request.get('salary') * fee)

    if request.get('role') == 'OPR':
        return json.dumps({ 'new_salary': new_salary(request, OPR_FEE), 'name': request.get('name') })
    elif request.get('role') == 'DVLP':
        return json.dumps({ 'new_salary': new_salary(request, DVLP_FEE), 'name': request.get('name') })

server = SimpleXMLRPCServer(("localhost", 8000))
print("Listening on port 8000...")
server.register_function(handler, "consume")
server.serve_forever()