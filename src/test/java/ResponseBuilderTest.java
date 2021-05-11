import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResponseBuilderTest {
    @Test
    public void returnsOKForExistingGETPath() {
        String method = "GET";
        String path = "/simple_get";
        String expectedResponse = "HTTP/1.1 200 OK\r\nAllow: GET, HEAD\r\n\r\n";
        Response response = new Response();
        ResponseBuilder.responseHandler(method, path, null, response);
        assertEquals(expectedResponse, response.print());
    }

    @Test
    public void returnsOKWithBody() {
        String method = "GET";
        String path = "/simple_get_with_body";
        String expectedResponse = "HTTP/1.1 200 OK\r\nContent-Type: text/plain;charset=utf-8\r\nAllow: GET, HEAD\r\n\r\nHello world";
        Response response = new Response();
        ResponseBuilder.responseHandler(method, path, null, response);
        assertEquals(expectedResponse, response.print());
    }

    @Test
    public void returns404ForNoSuchGETPath() {
        String method = "GET";
        String path = "/over_the_rainbow";
        String expectedResponse = "HTTP/1.1 404 Not Found\r\n\r\n";
        Response response = new Response();
        ResponseBuilder.responseHandler(method, path, null, response);
        assertEquals(expectedResponse, response.print());
    }

    @Test
    public void returns301ForRedirect() {
        String method = "GET";
        String path = "/redirect";
        String expectedResponse = "HTTP/1.1 301 Redirect\r\nLocation: http://127.0.0.1:5000/simple_get\r\nAllow: GET, HEAD\r\n\r\n";
        Response response = new Response();
        ResponseBuilder.responseHandler(method, path, null, response);
        assertEquals(expectedResponse, response.print());
    }

    @Test
    public void returnsMethodOptions() {
        String method = "OPTIONS";
        String path = "/method_options";
        String expectedResponse = "HTTP/1.1 200 OK\r\nAllow: GET, HEAD, OPTIONS\r\n\r\n";
        Response response = new Response();
        ResponseBuilder.responseHandler(method, path, null, response);
        assertEquals(expectedResponse, response.print());
    }

    @Test
    public void returnsMoreMethodOptions() {
        String method = "OPTIONS";
        String path = "/method_options2";
        String expectedResponse = "HTTP/1.1 200 OK\r\nAllow: GET, HEAD, OPTIONS, PUT, POST\r\n\r\n";
        Response response = new Response();
        ResponseBuilder.responseHandler(method, path, null, response);
        assertEquals(expectedResponse, response.print());
    }

    @Test
    public void returns405MethodNotAllowed() {
        String method = "GET";
        String path = "/head_request";
        String expectedResponse = "HTTP/1.1 405 Method Not Allowed\r\nAllow: HEAD, OPTIONS\r\n\r\n";
        Response response = new Response();
        ResponseBuilder.responseHandler(method, path, null, response);
        assertEquals(expectedResponse, response.print());
    }
}
