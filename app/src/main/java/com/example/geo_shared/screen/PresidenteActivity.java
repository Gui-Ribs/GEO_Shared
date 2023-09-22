package com.example.geo_shared.screen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.HandlerThread;
import android.util.Log;

import com.example.geo_shared.R;

import java.util.List;

import async.PresidenteFetcher;
import model.Continente;
import model.Presidente;

public class PresidenteActivity extends AppCompatActivity implements PresidenteFetcher.PresidenteFetchListener {

    private HandlerThread presidenteHandlerThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presidente);
        presidenteHandlerThread = new HandlerThread("presidenteFetcherThread");
        presidenteHandlerThread.start();
    }

    @Override
    public void PresidenteFetchSuccess(List<Presidente> presidentes) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                StringBuilder presidenteNames = new StringBuilder();
                for (Presidente presidente : presidentes) {
                    presidenteNames.append(presidente.getName()).append("\n");
                }
            }
        });
    }

    @Override
    public void PresidenteFetchError() {
        Log.e("ContinenteFetcher", "Erro na busca de presidentes");
    }

    protected void onDestroy() {
        super.onDestroy();
        presidenteHandlerThread.quit();
    }
}