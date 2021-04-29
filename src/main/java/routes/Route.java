package routes;
import java.util.ArrayList;

public interface Route {
    String getBody();

    ArrayList<String> getHeaders();

    String getAllow();

    void getContentType();
}
