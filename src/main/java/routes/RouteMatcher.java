package routes;

public class RouteMatcher {
//    HEAD_REQUEST("/head_request"),

    public static Route getRoute(String path) {
        Route route = null;
        try {
            switch (path) {
                case "/head_request":
                    route = new HeadRequest();
                    break;
                case "/health_check.html":
                    route = new HealthCheckRoute();
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
                default:
                    break;
            }

        } catch (NullPointerException ignore) {
            return null;
        }
        return route;
    }


}
