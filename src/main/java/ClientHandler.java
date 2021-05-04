import java.io.*;
import java.net.Socket;

public class ClientHandler extends Thread {
    private Socket clientSocket;
    private InputStream in;
    private PrintWriter out;
    StringBuilder stringBuilder = new StringBuilder();

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    public void run() {
        try {
            out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);
            in = clientSocket.getInputStream();

            String request = getRequest();
            String parameters = RequestReader.requestHandler(request);
            String parametersMethod = RequestReader.findRequestMethod(parameters);
            String parametersPath = RequestReader.findRequestAddress(parameters);
            String body = RequestReader.getBody(request);

            Response response = ResponseBuilder.responseHandler(parametersMethod, parametersPath, body);
            out.println(response.print());

            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getRequest() throws IOException {
        while (in.available() != 0) {
            stringBuilder.append((char) in.read());
        }

        return stringBuilder.toString();
    }
}
