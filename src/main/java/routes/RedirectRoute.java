package routes;

import HTTPServer.Codes;
import HTTPServer.Response;
import HTTPServer.Headers;
import HTTPServer.Route;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RedirectRoute implements Route {
    private static final String body = "";
    private ArrayList<String> headers = new ArrayList<>();
    private static final List<String> allow = Arrays.asList("GET", "HEAD");

    private static final String redirect = "http://127.0.0.1:5000/simple_get";

    @Override
    public String getBody() {
        return body;
    }

    @Override
    public ArrayList<String> getHeaders() {
        headers.add(Headers.LOCATION.getHeader() + redirect);
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
        response.setParams(Codes._301.getCode());
    }

    @Override
    public boolean getRouteIsFound() {
        return true;
    }
}
