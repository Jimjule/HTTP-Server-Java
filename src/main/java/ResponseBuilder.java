import constants.Codes;
import constants.Paths;
import routes.*;

public class ResponseBuilder {
    public static Response responseHandler(String method, String path) {
        String requestRoute = getRequestRoute(path);

        Route route = RouteMatcher.getRoute(requestRoute);
        Response response = new Response();

        if (route == null) {
            response.setParams(Codes._404.getCode());
            return response;
        }
        if (path.equals("/redirect")) {
            response.setParams(Codes._301.getCode());
            for (String header: route.getHeaders()) {
                response.setHeaders(header);
            }
            return response;
        }

        String responseCode;
        if (!route.getAllow().contains(method)) {
            responseCode = Codes._405.getCode();
        } else {
            responseCode = Codes._200.getCode();
        }
        response.setParams(responseCode);
        for (String header: route.getHeaders()) {
            response.setHeaders(header);
        }
        response.setBody(route.getBody());

        return response;
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
