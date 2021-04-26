import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.ServerSocket;

import static org.junit.jupiter.api.Assertions.*;

class ClientHandlerTest {
    private int port;
    private ServerSocket serverSocket;
    private HTTPServer httpServer;

    @BeforeEach
    public void setup() throws IOException {
        port = 5000;
        serverSocket = new ServerSocket(port);
    }

    @Test
    public void serverReceivesInputUntilFullStop() {
        httpServer = new HTTPServer(serverSocket);
    }
}