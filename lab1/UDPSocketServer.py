import socket
import json

def send_employee_data(client_address, server_socket):
    employees = [
        {"id": 1, "name": "John", "position": "Developer"},
        {"id": 2, "name": "Alice", "position": "Manager"},
        {"id": 3, "name": "Bob", "position": "Designer"}
    ]
    data = json.dumps(employees).encode()
    server_socket.sendto(data, client_address)

def main():
    # Создаем UDP сокет
    server_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

    # Указываем адрес и порт для прослушивания
    server_address = ('localhost', 12345)
    server_socket.bind(server_address)

    print("Сервер запущен")

    while True:
        # Принимаем данные от клиента
        data, client_address = server_socket.recvfrom(1024)
        print("Получено от", client_address, ":", data.decode())

        # Отправляем информацию о работниках обратно клиенту
        send_employee_data(client_address, server_socket)

if __name__ == "__main__":
    main()
