package routes;

import lib.constants.Headers;
import lib.Route;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EchoBody implements Route {
    private static String body = null;
    private ArrayList<String> headers = new ArrayList<>();
    private static final List<String> allow = Arrays.asList("POST", "HEAD");

    @Override
    public String getBody() {
        return body;
    }

    public void setBody(String newBody) {
        body = newBody;
    }

    @Override
    public ArrayList<String> getHeaders() {
        headers.add(Headers.CONTENT_TYPE_TEXT.getHeader());
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
