from xmlrpc.server import SimpleXMLRPCServer
import json

def handler(data):
    def response(category):
        return json.dumps({ 'category': category})

    request = json.loads(data)
    age = request.get('age')

    print(age)

    if age >= 5 and age <= 7:
        return response('infantil A')
    elif age >= 8 and age <= 10:
        return response('infantil B')
    elif age >= 11 and age <= 13:
        return response('juvenil A')
    elif age >= 14 and age <= 17:
        return response('juvenil B')
    elif age >= 18:
        return response('adulto')

server = SimpleXMLRPCServer(("localhost", 8000))
print("Listening on port 8000...")
server.register_function(handler, "consume")
server.serve_forever()