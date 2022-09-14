import xmlrpc.client
import json

proxy = xmlrpc.client.ServerProxy("http://localhost:8000/")

message = proxy.consume(json.dumps({ 'n1': 3, 'n2': 8.5, 'n3': 10.0}))
print(message)