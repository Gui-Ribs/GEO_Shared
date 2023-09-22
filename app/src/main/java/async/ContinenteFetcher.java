package async;

import android.os.Handler;
import android.os.HandlerThread;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import model.Continente;
import model.Governador;
import response.Connection;
import response.ConnectionGeo;

public class ContinenteFetcher {

    private Handler handler;
    private ContinenteFetchListener listener;

    public ContinenteFetcher(Handler handler, ContinenteFetchListener listener) {
        this.handler = handler;
        this.listener = listener;
    }

    public void fetchContinentes() {
        HandlerThread handlerThread = new HandlerThread("ContinenteFetcherThread");
        handlerThread.start();

        Handler handler = new Handler(handlerThread.getLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                Connection ConnectionManager;
                String result = ConnectionGeo.connectGeo("Continente");

                if (result != null) {
                    try {
                        JSONArray jsonArray = new JSONArray(result);
                        List<Continente> continentes = new ArrayList<>();

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject continenteJson = jsonArray.getJSONObject(i);
                            String code = continenteJson.getString("code");
                            String name = continenteJson.getString("name");
                            float latitude = continenteJson.getLong("latitude");
                            float longitude = continenteJson.getLong("longitude");
                            String bioma = continenteJson.getString("bioma");
                            String urlImage = continenteJson.getString("urlImage");

                            Continente continente = new Continente(code, name, latitude, longitude, bioma, urlImage);
                            continentes.add(continente);
                        }

                        if (listener != null) {
                            listener.ContinenteFetchSuccess(continentes);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        if (listener != null) {
                            listener.ContinenteFetchError();
                        }
                    }
                } else {
                    if (listener != null) {
                        listener.ContinenteFetchError();
                    }
                }
            }
        });
    }


    public interface ContinenteFetchListener {
        void ContinenteFetchSuccess(List<Continente> continentes);
        void ContinenteFetchError();
    }
}
