import java.io.IOException;
import HTTPServer.Router;
import HTTPServer.HTTPServer;
import routes.RouteMatcher;

public class Main {
    public static void main(String[] args) throws IOException {
        int port = 5000;
        Router router = new RouteMatcher();
        HTTPServer httpServer = new HTTPServer(port, router);
        httpServer.start();
    }
}
