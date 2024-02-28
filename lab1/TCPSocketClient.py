import socket
import json

def receive_employee_data(client_socket):
    data = client_socket.recv(1024)
    employees = json.loads(data.decode())
    return employees

def main():
    while True:
        # TCP сокет (семейство адресов IPv4 и Тип сокета - TCP)
        client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

        # Адрес и порт сервера для подключения
        server_address = ('localhost', 12345)

        # Подключение к серверу
        client_socket.connect(server_address)

        try:
            # Получаем информацию о работниках от сервера
            employees = receive_employee_data(client_socket)
            print("Информация о работниках:")
            for employee in employees:
                print(f"ID: {employee['id']}, Имя: {employee['name']}, Должность: {employee['position']}")

        finally:
            # Закрываем соединение
            client_socket.close()

        # Повтор запроса
        choice = input("Хотите повторить запрос? (Да/Нет): ").lower()
        if choice != 'да':
            break

if __name__ == "__main__":
    main()
