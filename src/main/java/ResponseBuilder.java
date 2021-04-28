import constants.Codes;
import constants.Paths;

public class ResponseBuilder {
    public static String responseHandler(String method, String path) {
        String responseOKWithBody = "HTTP/1.1 200 OK\r\nContent-Type: text/plain\r\n\r\nHello world";

        String responseGetHead = "HTTP/1.1 405 Method Not Allowed\r\nContent-Type: text/plain\r\nAllow: HEAD, OPTIONS\r\n";

        String responseOKOptions = "HTTP/1.1 200 OK\r\nContent-Type: text/plain\r\nAllow: GET, HEAD, OPTIONS\r\n";
        String responseOKOptions2 = "HTTP/1.1 200 OK\r\nContent-Type: text/plain\r\nAllow: GET, HEAD, OPTIONS, PUT, POST\r\n";

        String CRLF = "\r\n";
        String HTTP_VERSION = "HTTP/1.1 ";
        String response;

        String requestRoute = null;
        for (Paths route : Paths.values()) {
            if (route.getPath().equals(path)) {
                requestRoute = route.getPath();
            }
        }
        if (requestRoute == null) {
            response = "HTTP/1.1 " + Codes._404.getCode() + CRLF;
        }

        else if(method.equals("GET") && path.equals("/simple_get_with_body")) {
            response = responseOKWithBody;

        } else if (method.equals("GET") && path.equals("/head_request")) {
            response = responseGetHead;

        } else if (requestRoute.equals(Paths.REDIRECT.getPath())) {
            response = HTTP_VERSION + Codes._301.getCode() + CRLF + "Content-Type: text/plain" + CRLF + "Location: http://127.0.0.1:5000/simple_get" + CRLF;

        } else if (method.equals("OPTIONS") && path.equals("/method_options")) {
            response = responseOKOptions;
        } else if (method.equals("OPTIONS") && path.equals("/method_options2")) {
            response = responseOKOptions2;

        } else {
            response = HTTP_VERSION + Codes._200.getCode() + CRLF + "Content-Type: text/plain" + CRLF;
        }

        return response;
    }
}
