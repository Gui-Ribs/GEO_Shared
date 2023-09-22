package com.example.geo_shared.screen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.HandlerThread;
import android.util.Log;
import android.widget.TextView;

import com.example.geo_shared.R;

import java.util.List;

import async.ContinenteFetcher;
import model.Continente;

public class ContinenteActivity extends AppCompatActivity implements ContinenteFetcher.ContinenteFetchListener {

    private HandlerThread continenteHandlerThread;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_continente);
        continenteHandlerThread = new HandlerThread("continenteFetcherThread");
        continenteHandlerThread.start();

    }

    @Override
    public void ContinenteFetchSuccess(List<Continente> continentes) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                StringBuilder continenteNames = new StringBuilder();
                for (Continente continente : continentes) {
                    continenteNames.append(continente.getName()).append("\n");
                }
            }
        });
    }

    @Override
    public void ContinenteFetchError() {
        Log.e("ContinenteFetcher", "Erro na busca de continentes");
    }

    protected void onDestroy() {
        super.onDestroy();
        continenteHandlerThread.quit();
    }
}