import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class Server {

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(8989)) {
            System.out.println("Сервер запущен.");
            LinkedList<Socket> clients = new LinkedList<>();
            String city = null;

            while (true) { // в цикле(!) принимаем подключения
                try (
                        Socket socket = serverSocket.accept();
                        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                ) {
                    System.out.println("Новое соединение принято");
                    clients.add(socket);

                    if (clients.size() == 1) {
                        out.println("???");
                        city = in.readLine();
                        out.println("OK");
                    } else {
                        char cityLast = city.charAt(city.length() - 1);
                        out.println(city);

                        if (in.readLine().charAt(0) == cityLast) {
                            out.println("OK");
                            city = in.readLine();
                        } else {
                            out.println("NOT OK");
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }
}
