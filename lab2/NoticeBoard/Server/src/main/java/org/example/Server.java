package org.example;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.net.*;
import java.util.*;
import com.google.gson.*;

public class Server {
  private static final String FILE_NAME = "messages.json";
  private static List<String> messages = new ArrayList<>();

  public static void main(String[] args) {
    loadMessages();

    try (ServerSocket serverSocket = new ServerSocket(8000)) {
      while (true) {
        try (Socket socket = serverSocket.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
          String input = in.readLine();
          if (input == null || input.isEmpty()) {
            break;
          } else if (input.equals("LIST")) {
            out.println(String.join(";", messages));
          } else {
            messages.add(input);
            saveMessages();
            out.println("Message added: \"" + input + "\"");
          }
        } catch (IOException e) {
          System.out.println("Ошибка при обработке запроса: " + e.getMessage());
        }
      }
    } catch (IOException e) {
      System.out.println("Ошибка при запуске сервера: " + e.getMessage());
    }
  }

  private static void loadMessages() {
    try (Reader reader = new FileReader(FILE_NAME)) {
      Gson gson = new Gson();
      messages = gson.fromJson(reader, new TypeToken<List<String>>(){}.getType());
    } catch (IOException e) {
      System.out.println("Ошибка при загрузке сообщений: " + e.getMessage());
    }
  }

  private static void saveMessages() {
    try (Writer writer = new FileWriter(FILE_NAME)) {
      Gson gson = new Gson();
      gson.toJson(messages, writer);
    } catch (IOException e) {
      System.out.println("Ошибка при сохранении сообщений: " + e.getMessage());
    }
  }
}