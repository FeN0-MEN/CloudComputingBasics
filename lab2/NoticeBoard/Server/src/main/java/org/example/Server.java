package org.example;

import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.net.*;
import java.util.*;
import com.google.gson.*;

public class Server {

  private static final String FILE_NAME = "messages.json";
  private static List<String> messages = new ArrayList<>();
  private static final int PORT = 19001;

  public static void main(String[] args) {
    loadMessages();

    try (ServerSocket serverSocket = new ServerSocket(PORT)) {
      System.out.println("Сервер запущен...");

      while (true) {
        Socket clientSocket = serverSocket.accept();
        System.out.println("Подключен клиент: " + clientSocket);

        handleClient(clientSocket);
      }
    } catch (IOException e) {
      System.out.println("Ошибка при запуске сервера: " + e.getMessage());
    }
  }

  private static void handleClient(Socket clientSocket) {
    try (BufferedReader in = new BufferedReader(
        new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
      String input;
      while ((input = in.readLine()) != null) {
        if (input.equals("LIST")) {
          out.println(String.join(";", messages));
        } else if (!input.isEmpty()) {
          messages.add(input);
          saveMessages();
          out.println("Message added: \"" + input + "\"");
        } else {
          break;
        }
      }
      System.out.println("Клиент отключен: " + clientSocket);
      clientSocket.close();
    } catch (IOException e) {
      System.out.println("Ошибка при обработке запроса: " + e.getMessage());
    }
  }

  private static void loadMessages() {
    try (Reader reader = new FileReader(FILE_NAME)) {
      Gson gson = new Gson();
      messages = gson.fromJson(reader, new TypeToken<List<String>>() {
      }.getType());
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