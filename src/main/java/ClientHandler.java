import java.io.*;
import java.net.Socket;

public class ClientHandler extends Thread {
    private Socket clientSocket;
    private InputStream in;
    private PrintWriter out;
    private String request;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    public void run() {
        try {
            in = clientSocket.getInputStream();
            out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);
            request = getRequest();

            String parameters = RequestReader.requestHandler(request);
            String parametersMethod = RequestReader.findRequestMethod(parameters);
            String parametersPath = RequestReader.findRequestAddress(parameters);

            String body = RequestReader.getBody(request);

            Response response = new Response();

            ResponseBuilder.responseHandler(parametersMethod, parametersPath, body, response);

            if (parametersPath.equals("/health-check.html")) {
                System.out.println(response.getParams() + response.getHeaders() + "\r\n" + new String(response.getFile()));
                out.printf(response.getParams() + response.getHeaders() + "\r\n" + new String(response.getFile()));
            } else if (parametersPath.equals("/doggo.png")) {
                System.out.println(response.getParams() + response.getHeaders() + "\r\n" + new String(response.getFile()));
                out.printf(response.getParams() + response.getHeaders() + "\r\n" + new String(response.getFile()));
            } else if (parametersPath.equals("/kitteh.jpg")) {
                System.out.println(response.getParams() + response.getHeaders() + "\r\n" + response.getFile());
                out.printf(response.getParams() + response.getHeaders() + "\r\n" + response.getFile());
            } else {
                out.printf(response.print());
            }

            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getRequest() throws IOException {
        int readIn;
        StringBuilder input = new StringBuilder();
        while ((readIn = in.read()) != -1 && in.available() != 0) {
            input.append((char) readIn);
        }
        input.append((char) readIn);

        return input.toString();
    }
}

