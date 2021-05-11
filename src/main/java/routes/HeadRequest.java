package routes;

import constants.Headers;
import lib.Route;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HeadRequest implements Route {
    private static final String body = null;
    private ArrayList<String> headers = new ArrayList<>();
    private static final List<String> allow = Arrays.asList("HEAD", "OPTIONS");

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
}
