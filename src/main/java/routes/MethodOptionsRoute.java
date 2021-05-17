package routes;

import HTTPServer.Response;
import HTTPServer.Headers;
import HTTPServer.Route;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MethodOptionsRoute implements Route {
    private static final String body = null;
    private ArrayList<String> headers = new ArrayList<>();
    private static final List<String> allow = Arrays.asList("GET", "HEAD", "OPTIONS");

    @Override
    public String getBody() {
        return body;
    }

    @Override
    public ArrayList<String> getHeaders() {
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
        response.setBody(this.getBody());
    }

    @Override
    public boolean getRouteIsFound() {
        return true;
    }
}
