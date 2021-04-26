import java.io.IOException;
import java.net.ServerSocket;

public class HTTPServer {
    private ServerSocket serverSocket;

    public HTTPServer(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void start() {
        while (true) {
            try {
                new ClientHandler(this.serverSocket.accept()).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
