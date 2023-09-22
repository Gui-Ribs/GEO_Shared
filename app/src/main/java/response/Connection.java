package response;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Connection {

    private static final String BASE_URL = "https://wft-geo-db.p.rapidapi.com/v1/";
    private static final String API_KEY = "3264553452msh975cf288060e43ep1e2e77jsnc4e264f83487";
    private static final String API_HOST = "wft-geo-db.p.rapidapi.com";

    public static String connectHttp(String end_Point) {
        String return_api = null;
        String url_amount = BASE_URL + end_Point;
        System.out.println(url_amount);
        try {
            URL url = new URL(url_amount);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("X-RapidAPI-Key", API_KEY);
            connection.setRequestProperty("X-RapidAPI-Host", API_HOST);
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