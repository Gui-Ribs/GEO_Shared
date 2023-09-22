package com.example.geo_shared.screen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.HandlerThread;
import android.util.Log;
import android.widget.TextView;

import com.example.geo_shared.R;

import java.util.List;

import async.BiomaFetcher;
import model.Bioma;

public class BiomaActivity extends AppCompatActivity implements BiomaFetcher.BiomaFetchListener {

    private HandlerThread biomaHandlerThread;
    TextView name, clima, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bioma);

        biomaHandlerThread = new HandlerThread("BiomaFetcherThread");
        biomaHandlerThread.start();
        get_resources();
    }

    public void get_resources() {
        name = findViewById(R.id.bioma_name);
        clima = findViewById(R.id.clima);
        description = findViewById(R.id.description);
    }

    @Override
    public void BiomaFetchSuccess(List<Bioma> biomas) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                StringBuilder biomaNames = new StringBuilder();
                for (Bioma bioma : biomas) {
                    biomaNames.append(bioma.getName()).append("\n");
                }
                name.setText(biomaNames.toString());
            }
        });
    }

    @Override
    public void BiomaFetchError() {
        Log.e("BiomaFetcher", "Erro na busca de biomas");
    }

    protected void onDestroy() {
        super.onDestroy();
        biomaHandlerThread.quit();
    }
}