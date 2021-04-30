import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler extends Thread {
    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    public void run() {
        try {
            out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String line;
            ArrayList<String> input = new ArrayList<>();

            while ((line = in.readLine()) != null && line.length() != 0) {
                input.add(line);
            }

            String parameters = RequestReader.requestHandler(input);
            String parametersMethod = RequestReader.findRequestMethod(parameters);
            String parametersPath = RequestReader.findRequestAddress(parameters);

            Response response = ResponseBuilder.responseHandler(parametersMethod,  parametersPath);
            out.println(response.print());

            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
