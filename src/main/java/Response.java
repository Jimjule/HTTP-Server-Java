public class Response {

    private String params;
    private String headers = "";
    private String body = "";
    private byte[] file;

    private String CRLF = "\r\n";
    private String HTTP_VERSION = "HTTP/1.1 ";

    public Response() {
    }

    public void setParams(String code) {
        this.params = HTTP_VERSION + code + CRLF;
    }

    public void setHeaders(String headers) {
        this.headers += headers + CRLF;
    }

    public void setBody(String body) {
        if (body != null) {
            this.body += CRLF + body;
        } else {
            this.body += CRLF;
        }
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String print() {
        return this.params + this.headers + this.body;
    }

    public String getParams() {
        return params;
    }

    public String getHeaders() {
        return headers;
    }

    public byte[] getFile() {
        return file;
    }
}
