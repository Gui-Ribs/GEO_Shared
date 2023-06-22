package async;

import android.os.Handler;
import android.os.HandlerThread;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import model.Country;
import response.Connection;

public class CountryFetcher {

    private Handler handler;
    private OnCountryFetchListener listener;

    public CountryFetcher(Handler handler, OnCountryFetchListener listener) {
        this.handler = handler;
        this.listener = listener;
    }

    public void fetchCountries(String codeN) {
        HandlerThread handlerThread = new HandlerThread("CountryFetcherThread");
        handlerThread.start();

        Handler handler = new Handler(handlerThread.getLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                String result = Connection.connectHttp("geo/countries/"+codeN);

                if (result != null) {
                    try {
                        JSONObject jsonObject = new JSONObject(result);
//                        JSONArray jsonArray = jsonObject.getJSONArray("data");

                        List<Country> countries = new ArrayList<>();


                        JSONObject countryJson = jsonObject.getJSONObject("data");
                        String code = countryJson.getString("code");
                        String name = countryJson.getString("name");
                        String flagUri = countryJson.getString("flagImageUri");
                        int numRegions = countryJson.getInt("numRegions");
                        String wikiDataId = countryJson.getString("wikiDataId");
                        String callingCode = countryJson.getString("callingCode");
                        String capital = countryJson.getString("capital");
                        Country country = new Country(code, name, flagUri, numRegions, wikiDataId, callingCode, capital);

                        countries.add(country);


                        if (listener != null) {
                            listener.CountryFetchSuccess(countries);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        if (listener != null) {
                            listener.CountryFetchError();
                        }
                    }

                } else {
                    if (listener != null) {
                        listener.CountryFetchError();
                    }
                }
            }
        });
    }

    public interface OnCountryFetchListener {
        void CountryFetchSuccess(List<Country> countries);
        void CountryFetchError();
    }
}