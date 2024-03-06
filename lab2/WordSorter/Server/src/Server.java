import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

  private static final int PORT = 19000;

  public static void main(String[] args) {
    // Создание серверного сокета
    try (ServerSocket serverSocket = new ServerSocket(PORT)) {
      while (true) {
        // Получение нового соединения с клиента
        try (Socket socket = serverSocket.accept();
            // BufferedReader для чтения данных, отправленных клиентом
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // PrintWriter для отправки данных клиенту
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

          // Получение данных от клиента
          String textBlock = in.readLine();
          String[] sortedWords = WordSorter.sortWords(textBlock);
          for (String word : sortedWords) {
            out.println(word);
          }
        } catch (IOException e) {
          System.out.println("Ошибка при обработке запроса: " + e.getMessage());
        }
      }
    } catch (IOException e) {
      System.out.println("Ошибка при запуске сервера: " + e.getMessage());
    }
  }
}