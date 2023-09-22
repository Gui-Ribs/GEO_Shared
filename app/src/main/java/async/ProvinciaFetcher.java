package async;

import android.os.Handler;
import android.os.HandlerThread;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import model.Governador;
import model.Provincia;
import response.Connection;
import response.ConnectionGeo;

public class ProvinciaFetcher {


    private Handler handler;
    private ProvinciaFetchListener listener;

    public ProvinciaFetcher(Handler handler, ProvinciaFetchListener listener) {
        this.handler = handler;
        this.listener = listener;
    }

    public void fetchProvincias() {
        HandlerThread handlerThread = new HandlerThread("ProvinciasFetcherThread");
        handlerThread.start();

        Handler handler = new Handler(handlerThread.getLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                Connection ConnectionManager;
                String result = ConnectionGeo.connectGeo("Provincia");

                if (result != null) {
                    try {
                        JSONArray jsonArray = new JSONArray(result);
                        List<Provincia> provincias = new ArrayList<>();

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject provinciaJSON = jsonArray.getJSONObject(i);
                            String code = provinciaJSON.getString("code");
                            String name = provinciaJSON.getString("name");
                            String country = provinciaJSON.getString("country");
                            float longitude = provinciaJSON.getLong("longitude");
                            float latitude = provinciaJSON.getLong("latitude");
                            int population = provinciaJSON.getInt("population");
                            String bioma = provinciaJSON.getString("bioma");
                            String urlImage = provinciaJSON.getString("urlImage");
                            String ddd = provinciaJSON.getString("ddd");

                            Provincia provincia = new Provincia(code, name, country, longitude, latitude, population,
                                    bioma, urlImage, ddd);
                            provincias.add(provincia);
                        }

                        if (listener != null) {
                            listener.ProvinciaFetchSuccess(provincias);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        if (listener != null) {
                            listener.ProvinciaFetchError();
                        }
                    }
                } else {
                    if (listener != null) {
                        listener.ProvinciaFetchError();
                    }
                }
            }
        });
    }

    public interface ProvinciaFetchListener {
        void ProvinciaFetchSuccess(List<Provincia> provincias);
        void ProvinciaFetchError();
    }

}
