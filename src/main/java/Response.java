import constants.Codes;
import constants.Paths;

public class Response {

    private String params;
    private String headers = "";
    private String body = "";

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
        this.body += CRLF + body;
    }

    public String print() {
        return this.params + this.headers + this.body;
    }
}
