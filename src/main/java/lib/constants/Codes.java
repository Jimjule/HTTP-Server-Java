package lib.constants;

public enum Codes {
    _200("200 OK"),
    _301("301 Redirect"),
    _404("404 Not Found"),
    _405("405 Method Not Allowed");

    private final String code;

    Codes(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
