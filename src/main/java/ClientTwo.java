import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientTwo {

    protected static int port = 8989;
    protected static String host = "localhost";

    public static void main(String[] args) throws IOException {

        try (Socket socket = new Socket(host, port);
             PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {
            System.out.println(in.readLine());
            Scanner scanner = new Scanner(System.in);
            String city = scanner.nextLine();
            out.println(city);
            System.out.println(in.readLine());
            out.println(city);
        }
    }
}
