import HTTPServer.Codes;

import routes.*;

import HTTPServer.Route;
import HTTPServer.Response;
import HTTPServer.ResponseHelper;

public class ResponseBuilder {
    public static Response responseHandler(String method, String path, String body, Response response) {

        Route route = RouteMatcher.getRoute(path);

        if (ResponseHelper.checkRouteNotFound(response, route) || checkRouteRedirect(path, route, response)) return response;

        String responseCode = ResponseHelper.getResponseCode(method, route);
        response.setParams(responseCode);

        ResponseHelper.setResponseHeaders(response, route);
        route.performRequest(method, response, body, path);
        ResponseHelper.checkRouteParamsFound(response, route);
        return response;
    }

    private static boolean checkRouteRedirect(String path, Route route, Response response) {
        if (path.equals("/redirect")) {
            response.setParams(Codes._301.getCode());
            response.setBody("");
            for (String header: route.getHeaders()) {
                response.addHeader(header);
            }
            return true;
        }
        return false;
    }
}
