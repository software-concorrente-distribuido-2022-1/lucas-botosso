import xmlrpc.client
import json

proxy = xmlrpc.client.ServerProxy("http://localhost:8000/")

message = proxy.consume(json.dumps({ 'job_years': 30, 'age': 65 }))
print(message)

message = proxy.consume(json.dumps({ 'job_years': 20, 'age': 40 }))
print(message)