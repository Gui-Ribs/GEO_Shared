package async;

import android.os.Handler;
import android.os.HandlerThread;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import model.Bioma;
import response.Connection;
import response.ConnectionGeo;

public class BiomaFetcher {

    private Handler handler;
    private BiomaFetchListener listener;

    BiomaFetcher(Handler handler, BiomaFetchListener listener){
        this.handler = handler;
        this.listener = listener;
    }

    public void fetchBiomas() {
        HandlerThread handlerThread = new HandlerThread("BiomaFetcherThread");
        handlerThread.start();

        Handler handler = new Handler(handlerThread.getLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                Connection ConnectionManager;
                String result = ConnectionGeo.connectGeo("Bioma");

                if (result != null) {
                    try {
                        JSONArray jsonArray = new JSONArray(result);

                        List<Bioma> biomas = new ArrayList<>();

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject cityJson = jsonArray.getJSONObject(i);
                            String name = cityJson.getString("name");
                            String clima = cityJson.getString("clima");
                            String urlImage = cityJson.getString("urlImage");
                            String description = cityJson.getString("description");

                            Bioma bioma = new Bioma(name, clima, urlImage, description);
                            biomas.add(bioma);
                        }

                        if (listener != null) {
                            listener.BiomaFetchSuccess(biomas);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        if (listener != null) {
                            listener.BiomaFetchError();
                        }
                    }
                } else {
                    if (listener != null) {
                        listener.BiomaFetchError();
                    }
                }
            }
        });
    }

    public interface BiomaFetchListener {
        void BiomaFetchSuccess(List<Bioma> biomas);
        void BiomaFetchError();
    }
}
