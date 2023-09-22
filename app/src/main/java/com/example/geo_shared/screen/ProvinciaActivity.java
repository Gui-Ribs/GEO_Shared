package com.example.geo_shared.screen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.HandlerThread;
import android.util.Log;

import com.example.geo_shared.R;

import java.util.List;

import async.ProvinciaFetcher;
import model.Provincia;

public class ProvinciaActivity extends AppCompatActivity implements ProvinciaFetcher.ProvinciaFetchListener {

    private HandlerThread provinciaHandlerThread;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provincia);
        provinciaHandlerThread = new HandlerThread("provinciaFetcherThread");
        provinciaHandlerThread.start();
    }

    @Override
    public void ProvinciaFetchSuccess(List<Provincia> provincias) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                StringBuilder provinciaNames = new StringBuilder();
                for (Provincia provincia : provincias) {
                    provinciaNames.append(provincia.getName()).append("\n");
                }
            }
        });
    }

    @Override
    public void ProvinciaFetchError() {
        Log.e("ProvinciaFetcher", "Erro na busca de provincias");
    }

    protected void onDestroy() {
        super.onDestroy();
        provinciaHandlerThread.quit();
    }
}