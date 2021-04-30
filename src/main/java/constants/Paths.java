package constants;

public enum Paths {
    HEAD_REQUEST("/head_request"),
    METHOD_OPTIONS("/method_options"),
    METHOD_OPTIONS2("/method_options2"),
    REDIRECT("/redirect"),
    SIMPLE_GET("/simple_get"),
    SIMPLE_GET_WITH_BODY("/simple_get_with_body"),
    HEALTH_CHECK("/health_check.html");

    private final String path;

    Paths(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
