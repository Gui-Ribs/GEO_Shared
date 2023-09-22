package async;

import android.os.Handler;
import android.os.HandlerThread;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import model.Bioma;
import model.City;
import model.Governador;
import response.Connection;
import response.ConnectionGeo;

public class GovernadorFetcher {


    private Handler handler;
    private GovernadorFetchListener listener;

    public GovernadorFetcher(Handler handler, GovernadorFetchListener listener ) {
        this.handler = handler;
        this.listener = listener;
    }

    public void fetchGovernadores() {
        HandlerThread handlerThread = new HandlerThread("GovernadorFetcherThread");
        handlerThread.start();

        Handler handler = new Handler(handlerThread.getLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                Connection ConnectionManager;
                String result = ConnectionGeo.connectGeo("Governador");

                if (result != null) {
                    try {
                        JSONArray jsonArray = new JSONArray(result);
                        List<Governador> governadores = new ArrayList<>();

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject governadorJson = jsonArray.getJSONObject(i);
                            String name = governadorJson.getString("name");
                            String policy = governadorJson.getString("policy");
                            String provincia = governadorJson.getString("provincia");
                            int age = governadorJson.getInt("age");
                            String urlImage = governadorJson.getString("urlImage");
                            String nationality = governadorJson.getString("nacionality");

                            Governador governador = new Governador(name, policy, provincia, age, urlImage, nationality);
                            governadores.add(governador);
                        }

                        if (listener != null) {
                            listener.GovernadorFetchSuccess(governadores);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        if (listener != null) {
                            listener.GovernadorFetchError();
                        }
                    }
                } else {
                    if (listener != null) {
                        listener.GovernadorFetchError();
                    }
                }
            }
        });
    }

    public interface GovernadorFetchListener {
        void GovernadorFetchSuccess(List<Governador> governadores);
        void GovernadorFetchError();
    }

}
