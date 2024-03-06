import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
  private static final String HOST = "localhost";
  private static final int PORT = 19000;

  public static void main(String[] args) {
    // Создание сокета клиента
    try (Socket socket = new Socket(HOST, PORT);
        // PrintWriter для отправки сообщения серверу
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        // BufferedReader для получения сообщения от сервера
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        // BufferReader для чтения данных введенных пользователем
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {

      String textBlock = stdIn.readLine();
      // Отправка сообщения серверу
      out.println(textBlock);
      String receivedWord;
      // Получение сообщения от сервера и вывод в консоль
      while ((receivedWord = in.readLine()) != null) {
        System.out.println(receivedWord);
      }

    } catch (IOException e) {
      System.out.println("Ошибка при подключении к серверу: " + e.getMessage());
    }
  }
}