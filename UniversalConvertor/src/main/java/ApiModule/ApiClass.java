package ApiModule;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

/**
 * Esta clase esta encargada de hacer las peticiones a la api de divisas, de recibir y filtrar el Json Resultante.
 */
public class ApiClass {


    /**
     * Este método es el encargado de hacer la petición a la api, para ello usa la key pública y dos parámetros
     * String que serán las divisas necesarias para la conversión en la clase CoinsGUI, hace uso del método ResponseCodeAndGetRate() para
     * obtener el Json filtrado.
     *
     * @param key        Llave pública de acceso.
     * @param firstCoin  Primera divisa.
     * @param secondCoin Segunda divisa.
     * @return Tasa de conversión.
     *
     * @see #ResponseCodeAndGetRate(int, HttpURLConnection)
     */
    public double Requestapi(String key, String firstCoin, String secondCoin) {
        String Request = String.format("https://v6.exchangerate-api.com/v6/%s/pair/%s/%s", key, firstCoin, secondCoin);

        try {
            // Creando URL y abriendo HttpUrlConnection.

            URL URL_API = new URL(Request);
            HttpURLConnection connection = (HttpURLConnection) URL_API.openConnection();
            connection.setRequestMethod("GET");

            // Se obtiene el código de respuesta de la api.
            int RESPONSE_CODE = connection.getResponseCode();

            // Uso del metodo ResponseCodeAndGetRate(), para obtener el valor requerido desde la api.
            double rate = ResponseCodeAndGetRate(RESPONSE_CODE, connection);
            return rate;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }

    }


    /**
     * Este método es el encargado de obtener el Json generado por la api y filtrarlo para extraer el valor requerido.
     * @param code Código de respuesta generado por la api.
     * @param connection Objeto HttpURLConnection.
     * @return Tasa de conversión
     * @throws IOException Exception.
     */
    public double ResponseCodeAndGetRate(int code, HttpURLConnection connection) throws IOException  {
       if(code == HttpURLConnection.HTTP_OK){
           BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
           String line;
           StringBuilder response = new StringBuilder();
           while ((line = reader.readLine()) != null) {
               response.append(line);
           }
           reader.close();


           JSONObject JSON_RESPONSE = new JSONObject(response.toString());
            double CONVERSION_RATE = JSON_RESPONSE.getDouble("conversion_rate");
            return CONVERSION_RATE;

       }else {
           System.out.println("A ocurrido un error");
           return -1;
       }
    }

}
