import java.io.*;
import java.net.Socket;

public class ClientHandler extends Thread {
    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    public void run() {
        System.out.println("Client Connected.");
        try {
            out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out.println("Welcome");
            String input;
            while ((input = in.readLine()) != null) {
                if(input != ".") {
                    out.println("Hey.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
