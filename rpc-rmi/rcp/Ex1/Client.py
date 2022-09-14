import xmlrpc.client
import json

proxy = xmlrpc.client.ServerProxy("http://localhost:8000/")

message = proxy.consume(json.dumps({ 'salary': 3000.00, 'role': 'OPR', 'name': 'Lucas'}))
print(message)