import java.util.ArrayList;

public class RequestReader {
    public static String requestHandler(ArrayList<String> request) {
        return request.get(0);
    }

    public static String findRequestMethod(String parameters) {
        return parameters.split(" ")[0];
    }

    public static String findRequestAddress(String parameters) {
        return parameters.split(" ")[1];
    }
}
