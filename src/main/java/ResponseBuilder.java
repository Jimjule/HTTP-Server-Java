import constants.Codes;
import constants.Paths;
import routes.*;

public class ResponseBuilder {
    public static Response responseHandler(String method, String path) {
        String ALLOW_HEAD_OPTIONS = "Allow: HEAD, OPTIONS";

        String requestRoute = getRequestRoute(path);
        String responseCode = getResponseCode(requestRoute);

        Route route = RouteMatcher.getRoute(requestRoute);

        Response response = new Response();

        if (route == null) {
            response.setParams(Codes._404.getCode());
            return response;
        }

        if(method.equals("GET") && path.equals("/simple_get_with_body")) {
            response.setParams(responseCode);
            for (String header: route.getHeaders()) {
                response.setHeaders(header);
            }
            response.setBody(route.getBody());

        } else if (method.equals("GET") && path.equals("/head_request")) {
            response.setParams(responseCode);
            response.setParams(Codes._405.getCode());
            response.setHeaders(ALLOW_HEAD_OPTIONS);

        } else if (responseCode.equals(Codes._301.getCode())) {
            response.setParams(responseCode);
            for (String header: route.getHeaders()) {
                response.setHeaders(header);
            }

        } else if (method.equals("OPTIONS") && path.equals("/method_options")) {
            response.setParams(responseCode);
            for (String header: route.getHeaders()) {
                response.setHeaders(header);
            }

        } else if (method.equals("OPTIONS") && path.equals("/method_options2")) {
            response.setParams(responseCode);
            for (String header: route.getHeaders()) {
                response.setHeaders(header);
            }

        } else if (method.equals("GET") && path.equals("/health_check.html")) {
            response.setParams(responseCode);
            for (String header: route.getHeaders()) {
                response.setHeaders(header);
            }
            response.setBody(new HealthCheckRoute().getBody());
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
