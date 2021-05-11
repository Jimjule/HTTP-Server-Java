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
            this.body += body;
        }
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getStringResponse() {
        return this.params + this.headers + CRLF + this.body;
    }

    public byte[] printHeaders() {
        return (this.params + this.headers + CRLF).getBytes();
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

    public byte[] getBody() {
        return body.getBytes();
    }
}
