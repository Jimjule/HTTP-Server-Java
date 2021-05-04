package constants;

public enum Headers {
    ALLOW("Allow: "),
    CONTENT_TYPE("Content-Type: "),
    CONTENT_TYPE_TEXT(CONTENT_TYPE.getHeader() + "text/plain"),
    CONTENT_TYPE_HTML(CONTENT_TYPE.getHeader() + "text/html"),
    LOCATION("Location: ");

    private final String header;

    Headers(String header) {
        this.header = header;
    }

    public String getHeader() {
        return header;
    }
}
