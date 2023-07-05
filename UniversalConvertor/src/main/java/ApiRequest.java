import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONObject;

public class ApiRequest  {
    public static void main(String[] args) {
        String DIVISAS[] = {"COP", "USD", "EUR", "KRW", "GBP", "JPY"};
        ApiClass apiFetch = new ApiClass();
        new MakeWindow(DIVISAS, DIVISAS);
        double rate = apiFetch.Requestapi("3ddbcda2c715f3d01c3f41a4","COP", "USD");
        System.out.println(4000 * rate);

    }
}
