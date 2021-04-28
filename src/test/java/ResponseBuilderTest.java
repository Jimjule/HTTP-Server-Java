import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResponseBuilderTest {
    @Test
    public void returnsOKForExistingGETPath() {
        String method = "GET";
        String path = "/simple_get";
        String response = "HTTP/1.1 200 OK\r\nContent-Type: text/plain\r\n";
        assertEquals(response, ResponseBuilder.responseHandler(method, path));
    }

    @Test
    public void returns404ForNoSuchGETPath() {
        String method = "GET";
        String path = "/over_the_rainbow";
        String response = "HTTP/1.1 404 Not Found\r\n";
        assertEquals(response, ResponseBuilder.responseHandler(method, path));
    }

    @Test
    public void returns301ForRedirect() {
        String method = "GET";
        String path = "/redirect";
        String response = "HTTP/1.1 301 Redirect\r\nContent-Type: text/plain\r\nLocation: http://127.0.0.1:5000/simple_get\r\n";
        assertEquals(response, ResponseBuilder.responseHandler(method, path));
    }
}
