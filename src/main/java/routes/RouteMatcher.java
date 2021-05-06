package routes;

public class RouteMatcher {
    public static Route getRoute(String path) {
        Route route = null;
        try {
            switch (path) {
                case "/echo_body":
                    route = new EchoBody();
                    break;
                case "/head_request":
                    route = new HeadRequest();
                    break;
                case "/health_check.html":
                    route = new HealthCheckRoute();
                    break;
                case "/html_response":
                    route = new HTMLResponse();
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
                default:
                    break;
            }
        } catch (NullPointerException ignore) {
            return null;
        }
        return route;
    }
}
