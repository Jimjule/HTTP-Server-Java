import constants.Codes;
import constants.Paths;

public class ResponseBuilder {
    public static Response responseHandler(String method, String path) {
        String CONTENT_TYPE_TEXT_PLAIN = "Content-Type: text/plain";
        String CONTENT_TYPE_TEXT_HTML = "Content-Type: text/html";
        String ALLOW_GET_HEAD_OPTIONS_PUT_POST = "Allow: GET, HEAD, OPTIONS, PUT, POST";
        String ALLOW_GET_HEAD_OPTIONS = "Allow: GET, HEAD, OPTIONS";
        String ALLOW_HEAD_OPTIONS = "Allow: HEAD, OPTIONS";

        String requestRoute = getRequestRoute(path);
        String responseCode = getResponseCode(requestRoute);

        Response response = new Response();

        if(method.equals("GET") && path.equals("/simple_get_with_body")) {
            response.setParams(responseCode);
            response.setHeaders(CONTENT_TYPE_TEXT_PLAIN);
            response.setBody("Hello world");

        } else if (method.equals("GET") && path.equals("/head_request")) {
            response.setParams("405 Method Not Allowed");
            response.setHeaders(ALLOW_HEAD_OPTIONS);

        } else if (responseCode.equals(Codes._301.getCode())) {
            response.setParams(responseCode);
            response.setHeaders("Location: http://127.0.0.1:5000/simple_get");

        } else if (method.equals("OPTIONS") && path.equals("/method_options")) {
            response.setParams(responseCode);
            response.setHeaders(ALLOW_GET_HEAD_OPTIONS);

        } else if (method.equals("OPTIONS") && path.equals("/method_options2")) {
            response.setParams(responseCode);
            response.setHeaders(ALLOW_GET_HEAD_OPTIONS_PUT_POST);

        } else if (method.equals("GET") && path.equals("/health_check.html")) {
            response.setParams(responseCode);
            response.setHeaders(CONTENT_TYPE_TEXT_HTML);
            response.setBody("<html><body><h1>Status: Passing</h1></body></html>");
        } else {
            response.setParams(responseCode);
        }

        return response;
    }

    private static String getResponseCode(String requestRoute) {
        String responseCode;

        if (requestRoute == null) {
            responseCode = Codes._404.getCode();
        } else if (requestRoute.equals(Paths.REDIRECT.getPath())) {
            responseCode = Codes._301.getCode();
        } else {
            responseCode = Codes._200.getCode();
        }

        return responseCode;
    }

    private static String getRequestRoute(String path) {
        String requestRoute = null;
        for (Paths route : Paths.values()) {
            if (route.getPath().equals(path)) {
                requestRoute = route.getPath();
            }
        }
        return requestRoute;
    }
}
