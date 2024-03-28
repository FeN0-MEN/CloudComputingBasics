from xmlrpc.server import SimpleXMLRPCServer

def hello_world():
    return "Hello, World!"

server = SimpleXMLRPCServer(("localhost", 8000))
print("Запуск на порте 8000")
server.register_function(hello_world, "hello_world")
server.serve_forever()
