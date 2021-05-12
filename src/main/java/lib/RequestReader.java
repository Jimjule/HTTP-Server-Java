package lib;

public class RequestReader {
    public static String requestHandler(String request) {
        return request.split("\r\n")[0];
    }

    public static String findRequestMethod(String parameters) {
        return parameters.split(" ")[0];
    }

    public static String findRequestAddress(String parameters) {
        return parameters.split(" ")[1];
    }

    public static String getBody(String request) {
        String body = null;
        String[] inputLines;
        if (request.contains("Content-Length")) {
            inputLines = request.split("\n");
            body = inputLines[inputLines.length - 1];
        }
        return body;
    }
}
