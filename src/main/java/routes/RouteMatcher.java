package routes;

import HTTPServer.Route;
import HTTPServer.Router;
import routes.files.DoggoPNGRoute;
import routes.files.HealthCheckHTMLRoute;
import routes.files.KissesGIFRoute;
import routes.files.KittehJPGRoute;
import routes.structured.text.HTMLResponse;
import routes.structured.text.JSONResponse;
import routes.structured.text.TextResponse;
import routes.structured.text.XMLResponse;

public class RouteMatcher implements Router {
    public Route getRoute(String path) {
        Route route = null;
        try {
            switch (path) {
                case "/doggo.png":
                    route = new DoggoPNGRoute();
                    break;
                case "/echo_body":
                    route = new EchoBody();
                    break;
                case "/head_request":
                    route = new HeadRequest();
                    break;
                case "/health-check.html":
                    route = new HealthCheckHTMLRoute();
                    break;
                case "/html_response":
                    route = new HTMLResponse();
                    break;
                case "/json_response":
                    route = new JSONResponse();
                    break;
                case "/kisses.gif":
                    route = new KissesGIFRoute();
                    break;
                case "/kitteh.jpg":
                    route = new KittehJPGRoute();
                    break;
                case "/method_options":
                    route = new MethodOptionsRoute();
                    break;
                case "/method_options2":
                    route = new MethodOptions2Route();
                    break;
                case "/redirect":
                    route = new RedirectRoute();
                    break;
                case "/simple_get":
                    route = new SimpleGetRoute();
                    break;
                case "/simple_get_with_body":
                    route = new SimpleGetWithBodyRoute();
                    break;
                case "/text_response":
                    route = new TextResponse();
                    break;
                case "/xml_response":
                    route = new XMLResponse();
                    break;
                default:
                    break;
            }
        } catch (NullPointerException ignore) {
            return null;
        }
        return route;
    }
}
