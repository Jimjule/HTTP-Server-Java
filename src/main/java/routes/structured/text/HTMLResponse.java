package routes.structured.text;

import constants.Headers;
import routes.Route;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HTMLResponse implements Route {
    private static final String body = "<html><body><p>HTML Response</p></body></html>";
    private ArrayList<String> headers = new ArrayList<>();
    private static final List<String> allow = Arrays.asList("GET", "HEAD");

    public String getBody() {
        return body;
    }

    @Override
    public ArrayList<String> getHeaders() {
        headers.add(Headers.CONTENT_TYPE_HTML.getHeader());
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
    public void getContentType() {
    }

    @Override
    public List<String> getAllow() {
        return allow;
    }
}
