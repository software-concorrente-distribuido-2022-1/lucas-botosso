from xmlrpc.server import SimpleXMLRPCServer
import json

def handler(data):
    def response(can_retire):
        return json.dumps({ 'can_retire': can_retire })

    request = json.loads(data)
    job_years = request.get('job_years')
    age = request.get('age')

    if (age >= 65) and (job_years >= 30) and ((age >= 60) and (job_years >= 25)):
        return response(True)
    else:
        return response(False)

server = SimpleXMLRPCServer(("localhost", 8000))
print("Listening on port 8000...")
server.register_function(handler, "consume")
server.serve_forever()