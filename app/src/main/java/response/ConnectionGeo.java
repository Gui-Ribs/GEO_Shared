package response;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectionGeo {

    private static final String API_SHARED = "https://geoshared-api.onrender.com/shared/";

    public static String connectGeo(String end_Point) {
        String return_api = null;
        String url_amount = API_SHARED + end_Point;
        System.out.println(url_amount);
        try {
            URL url = new URL(url_amount);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                reader.close();
                return_api = stringBuilder.toString();
            }
            else
                System.out.println("Error, code of return: " + responseCode);

            connection.disconnect();
        }
        catch (Exception error) {
            error.printStackTrace();
        }
        return return_api;
    }



}
