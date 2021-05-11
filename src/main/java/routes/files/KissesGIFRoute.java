package routes.files;

import constants.Headers;
import routes.Route;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KissesGIFRoute implements Route {
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
        filePath += "/src/main/resources/kisses.gif";

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
        headers.add(Headers.CONTENT_TYPE_GIF.getHeader());
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
}
