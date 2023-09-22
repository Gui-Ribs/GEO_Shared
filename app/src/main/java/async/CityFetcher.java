package async;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.HandlerThread;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import model.City;
import response.Connection;

public class CityFetcher {

    private Handler handler;
    private CityFetchListener listener;

    public CityFetcher(Handler handler, CityFetchListener listener) {
        this.handler = handler;
        this.listener = listener;
    }

    public void fetchCities() {
        HandlerThread handlerThread = new HandlerThread("CityFetcherThread");
        handlerThread.start();

        Handler handler = new Handler(handlerThread.getLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                Connection ConnectionManager;
                String result = Connection.connectHttp("geo/cities");

                if (result != null) {
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        JSONArray jsonArray = jsonObject.getJSONArray("data");

                        List<City> cities = new ArrayList<>();

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject cityJson = jsonArray.getJSONObject(i);
                            String name = cityJson.getString("name");
                            String country = cityJson.getString("country");
                            String country_code = cityJson.getString("countryCode");
                            String region = cityJson.getString("region");
                            long latitude =  cityJson.getLong("latitude");
                            long longitude = cityJson.getLong("longitude");

                            City city = new City(name, country, country_code, region, latitude, longitude);
                            cities.add(city);
                        }

                        if (listener != null) {
                            listener.CityFetchSuccess(cities);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        if (listener != null) {
                            listener.CityFetchError();
                        }
                    }
                } else {
                    if (listener != null) {
                        listener.CityFetchError();
                    }
                }
            }
        });
    }

    public interface CityFetchListener {
        void CityFetchSuccess(List<City> cities);
        void CityFetchError();
    }
}
