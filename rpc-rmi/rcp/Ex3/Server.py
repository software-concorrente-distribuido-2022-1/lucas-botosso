from xmlrpc.server import SimpleXMLRPCServer
import json
import functools

def handler(data):
    request = json.loads(data)
    n1 = request.get('n1')
    n2 = request.get('n2')
    n3 = request.get('n3')

    def approved():
        return json.dumps({'approved': True})
    
    def disapproved():
        return json.dumps({'approved': False})

    def average(notes):
        return functools.reduce(lambda a, b: a+b, notes)/len(notes)

    first_avg = average([n1, n2])
    if first_avg >= 7.0:
        return approved()
    elif first_avg > 3.0 and first_avg < 7.0:
        new_average = average([n1, n2, n3])
        if new_average >= 5.0:
            return approved()
        return disapproved()
    else:
        return disapproved()

server = SimpleXMLRPCServer(("localhost", 8000))
print("Listening on port 8000...")
server.register_function(handler, "consume")
server.serve_forever()