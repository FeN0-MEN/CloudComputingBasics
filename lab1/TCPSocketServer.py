import socket
import json

def send_employee_data(connection):
    employees = [
        {"id": 1, "name": "John", "position": "Developer"},
        {"id": 2, "name": "Alice", "position": "Manager"},
        {"id": 3, "name": "Bob", "position": "Designer"}
    ]
    data = json.dumps(employees).encode()
    connection.sendall(data)

def main():
    # TCP сокет (семейство адресов IPv4 и Тип сокета - TCP)
    server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

    # Адрес и порт сервера
    server_address = ('localhost', 12345)
    # Привязка сокета к адресу и порту
    server_socket.bind(server_address)
    server_socket.listen(1)
    print("Сервер запущен")

    while True:
        # Принимаем входящее соединение
        connection, client_address = server_socket.accept()
        try:
            print("Подключение от", client_address)
            send_employee_data(connection)
            print("Сообщение отправлено")
        finally:
            connection.close()

if __name__ == "__main__":
    main()
