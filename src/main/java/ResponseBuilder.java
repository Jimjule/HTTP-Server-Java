public class ResponseBuilder {
    public static String responseHandler(String method, String path) {
        String responseOKWithBody = "HTTP/1.1 200 OK\r\nContent-Type: text/plain\r\n\r\nHello world";
        String responseOK = "HTTP/1.1 200 OK\r\nContent-Type: text/plain\r\n";

        String responseHeadOnly = "HTTP/1.1 405 Method Not Allowed\r\nContent-Type: text/plain\r\nAllow: HEAD, OPTIONS\r\n";

        String responseOKOptions = "HTTP/1.1 200 OK\r\nContent-Type: text/plain\r\nAllow: GET, HEAD, OPTIONS\r\n";
        String responseOKOptions2 = "HTTP/1.1 200 OK\r\nContent-Type: text/plain\r\nAllow: GET, HEAD, OPTIONS, PUT, POST\r\n";

        String responseHead = "HTTP/1.1 200 OK\r\nContent-Type: text/plain\r\n";

        String responseRedirect = "HTTP/1.1 301 Redirect\r\nContent-Type: text/plain\r\nLocation: http://127.0.0.1:5000/simple_get\r\n";

        String responseNotFound = "HTTP/1.1 404 Not Found\r\n";

        String response = null;
        if (method.equals("GET") && path.equals("/simple_get")) {
            response = responseOK;
        } else if(method.equals("GET") && path.equals("/simple_get_with_body")) {
            response = responseOKWithBody;
        } else if (method.equals("GET") && path.equals("/redirect")) {
            response = responseRedirect;
        } else if (method.equals("GET") && path.equals("/head_request")) {
            response = responseHeadOnly;
        } else if (method.equals("HEAD") && path.equals("/simple_get") || method.equals("HEAD") && path.equals("/head_request")) {
            response = responseHead;
        } else if (method.equals("OPTIONS") && path.equals("/method_options")) {
            response = responseOKOptions;
        } else if (method.equals("OPTIONS") && path.equals("/method_options2")) {
            response = responseOKOptions2;
        } else {
            response = responseNotFound;
        }

        return response;
    }
}
