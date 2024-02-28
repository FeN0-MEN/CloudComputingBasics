import socket
import json

def receive_employee_data(client_socket):
    data, _ = client_socket.recvfrom(1024)
    employees = json.loads(data.decode())
    return employees

def main():
    while True:
        # Создаем UDP сокет
        client_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

        # Указываем адрес и порт сервера
        server_address = ('localhost', 12345)

        try:
            # Отправляем запрос серверу
            message = "Запрос информации о работниках"
            print("Отправка запроса:", message)
            client_socket.sendto(message.encode(), server_address)

            # Получаем информацию о работниках от сервера
            employees = receive_employee_data(client_socket)
            print("Информация о работниках:")
            for employee in employees:
                print(f"ID: {employee['id']}, Имя: {employee['name']}, Должность: {employee['position']}")

        finally:
            # Закрываем соединение
            client_socket.close()

        # Проверяем, хочет ли пользователь повторить запрос
        choice = input("Хотите повторить запрос? (Да/Нет): ").lower()
        if choice != 'да':
            break

if __name__ == "__main__":
    main()
