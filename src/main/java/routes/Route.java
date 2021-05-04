package routes;
import java.util.ArrayList;
import java.util.List;

public interface Route {
    String getBody();

    ArrayList<String> getHeaders();

    String formatAllow();

    void getContentType();

    List<String> getAllow();
}
