import xmlrpc.client
import json

proxy = xmlrpc.client.ServerProxy("http://localhost:8000/")

message = proxy.consume(json.dumps({ 'age': 5 }))
print(message)

message = proxy.consume(json.dumps({ 'age': 11 }))
print(message)