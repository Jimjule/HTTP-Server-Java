import HTTPServer.constants.Codes;

import routes.*;
import routes.files.DoggoPNGRoute;
import routes.files.HealthCheckHTMLRoute;
import routes.files.KissesGIFRoute;
import routes.files.KittehJPGRoute;

import HTTPServer.route.Route;
import HTTPServer.Response;

public class ResponseBuilder {
    public static Response responseHandler(String method, String path, String body, Response response) {

        Route route = RouteMatcher.getRoute(path);

        if (checkRouteNotFound(path, route, response)) return response;

        String responseCode = getResponseCode(method, route);
        response.setParams(responseCode);

        for (String header : route.getHeaders()) {
            response.addHeader(header);
        }
        if (path.equals("/echo_body")) {
            response.setBody(body);
        } else if (path.equals("/health-check.html")) {
            HealthCheckHTMLRoute healthCheckHTMLRoute = new HealthCheckHTMLRoute();
            response.setFile(healthCheckHTMLRoute.getFile());
        } else if (path.equals("/doggo.png")) {
            DoggoPNGRoute doggoPNGRoute = new DoggoPNGRoute();
            response.setFile(doggoPNGRoute.getFile());
        } else if (path.equals("/kitteh.jpg")) {
            KittehJPGRoute kittehJPGRoute = new KittehJPGRoute();
            response.setFile(kittehJPGRoute.getFile());
        } else if (path.equals("/kisses.gif")) {
            KissesGIFRoute kissesGIFRoute = new KissesGIFRoute();
            response.setFile(kissesGIFRoute.getFile());
        } else {
            response.setBody(route.getBody());
        }

        return response;
    }

    private static boolean checkRouteNotFound(String path, Route route, Response response) {
        if (route == null) {
            response.setParams(Codes._404.getCode());
            response.setBody("");
            return true;
        }
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
