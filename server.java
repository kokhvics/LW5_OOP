import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class server {
    public static void main(String[] args) {
        int port = 8080; // Порт, на котором сервер будет слушать подключения

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server is running on port: " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept(); // Принимаем входящее подключение
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                String request = in.readLine(); // Читаем запрос от клиента

                // Здесь можно обработать запрос, например, отправить данные клиенту или выполнить определенное действие

                out.println("Server received the request: " + request); // Отправляем ответ клиенту

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}