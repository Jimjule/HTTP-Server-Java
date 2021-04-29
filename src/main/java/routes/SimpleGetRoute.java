package routes;

import constants.Headers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SimpleGetRoute implements Route {
    private static final String body = null;
    private static final ArrayList<String> headers = new ArrayList<>();
    private static final List<String> allow = Arrays.asList("GET", "HEAD");

    public String getBody() {
        return body;
    }

    @Override
    public ArrayList<String> getHeaders() {
        headers.add(getAllow());
        return headers;
    }

    @Override
    public String getAllow() {
        String allowHeader = Headers.ALLOW.getHeader();
        allowHeader += String.join(", ", allow);
        return allowHeader;
    }

    public void getContentType() {
    }
}
