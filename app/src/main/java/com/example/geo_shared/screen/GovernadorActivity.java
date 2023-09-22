package com.example.geo_shared.screen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.HandlerThread;
import android.util.Log;

import com.example.geo_shared.R;

import java.util.List;

import async.GovernadorFetcher;
import model.Governador;
import model.Presidente;

public class GovernadorActivity extends AppCompatActivity  implements GovernadorFetcher.GovernadorFetchListener {


    private HandlerThread governadorHandlerThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_governador);
        governadorHandlerThread = new HandlerThread("governadorFetcherThread");
        governadorHandlerThread.start();
    }

    @Override
    public void GovernadorFetchSuccess(List<Governador> governadores) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                StringBuilder governadorNames = new StringBuilder();
                for (Governador governador : governadores) {
                    governadorNames.append(governador.getName()).append("\n");
                }
            }
        });
    }

    @Override
    public void GovernadorFetchError() {
        Log.e("GovernadorFetcher", "Erro na busca de governadores");
    }

    protected void onDestroy() {
        super.onDestroy();
        governadorHandlerThread.quit();
    }
}