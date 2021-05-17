package routes.files;

import HTTPServer.Response;
import HTTPServer.Headers;
import HTTPServer.Route;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DoggoPNGRoute implements Route {
    private static String body = null;
    private ArrayList<String> headers = new ArrayList<>();
    private static final List<String> allow = Arrays.asList("GET", "HEAD");

    @Override
    public String getBody() {
        return body;
    }

    public void setBody(String newBody) {
        body = newBody;
    }

    public byte[] getFile() {
        String filePath = new File("").getAbsolutePath();
        filePath += "/src/main/resources/doggo.png";

        byte[] fileBytes = new byte[0];
        try {
            fileBytes = Files.readAllBytes(Paths.get(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileBytes;
    }

    @Override
    public ArrayList<String> getHeaders() {
        headers.add(Headers.CONTENT_TYPE_PNG.getHeader());
        headers.add(formatAllow());
        return headers;
    }

    @Override
    public String formatAllow() {
        String allowHeader = Headers.ALLOW.getHeader();
        allowHeader += String.join(", ", allow);
        return allowHeader;
    }

    @Override
    public List<String> getAllow() {
        return allow;
    }

    @Override
    public void performRequest(String method, Response response, String body, String path) {
        if (method.equals("GET")) {
            response.setFile(this.getFile());
        }
    }

    @Override
    public boolean getRouteIsFound() {
        return true;
    }
}
