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
import model.Presidente;
import response.Connection;
import response.ConnectionGeo;

public class PresidenteFetcher {


    private Handler handler;
    private PresidenteFetchListener listener;


    public PresidenteFetcher(Handler handler, PresidenteFetchListener listener) {
        this.handler = handler;
        this.listener = listener;
    }

    public void fetchPresidentes() {
        HandlerThread handlerThread = new HandlerThread("PresidenteFetcherThread");
        handlerThread.start();

        Handler handler = new Handler(handlerThread.getLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                Connection ConnectionManager;
                String result = ConnectionGeo.connectGeo("Presidente");

                if (result != null) {
                    try {
                        JSONArray jsonArray = new JSONArray(result);
                        List<Presidente> presidentes = new ArrayList<>();

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject presidenteJson = jsonArray.getJSONObject(i);
                            String name = presidenteJson.getString("name");
                            String country = presidenteJson.getString("country");
                            String policy = presidenteJson.getString("policy");
                            int age = presidenteJson.getInt("age");
                            String nationality = presidenteJson.getString("nationality");
                            String urlImage = presidenteJson.getString("urlImage");

                            Presidente presidente = new Presidente(name, country, policy, age, nationality, urlImage);
                            presidentes.add(presidente);
                        }

                        if (listener != null) {
                            listener.PresidenteFetchSuccess(presidentes);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        if (listener != null) {
                            listener.PresidenteFetchError();
                        }
                    }
                } else {
                    if (listener != null) {
                        listener.PresidenteFetchError();
                    }
                }
            }
        });
    }

    public interface PresidenteFetchListener {
        void PresidenteFetchSuccess(List<Presidente> presidentes);
        void PresidenteFetchError();
    }

}
