import constants.Codes;
import routes.*;

public class ResponseBuilder {
    public static Response responseHandler(String method, String path, String body) {

        Route route = RouteMatcher.getRoute(path);
        Response response = new Response();

        if (checkRouteNotFound(path, route, response)) return response;

        String responseCode = getResponseCode(method, route);
        response.setParams(responseCode);
        for (String header: route.getHeaders()) {
            response.setHeaders(header);
        }
        if (path.equals("/echo_body")) {
            response.setBody(body);
        } else {
            response.setBody(route.getBody());
        }

        return response;
    }

    private static boolean checkRouteNotFound(String path, Route route, Response response) {
        if (route == null) {
            response.setParams(Codes._404.getCode());
            return true;
        }
        if (path.equals("/redirect")) {
            response.setParams(Codes._301.getCode());
            for (String header: route.getHeaders()) {
                response.setHeaders(header);
            }
            return true;
        }
        return false;
    }

    private static String getResponseCode(String method, Route route) {
        String responseCode;
        if (!route.getAllow().contains(method)) {
            responseCode = Codes._405.getCode();
        } else {
            responseCode = Codes._200.getCode();
        }
        return responseCode;
    }
}
