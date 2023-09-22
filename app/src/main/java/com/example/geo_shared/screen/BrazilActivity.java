package com.example.geo_shared.screen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.widget.TextView;

import com.example.geo_shared.R;

import java.util.List;

import async.CountryFetcher;
import model.Country;

public class BrazilActivity extends AppCompatActivity  implements CountryFetcher.OnCountryFetchListener{

    private HandlerThread countryHandlerThread;
    TextView countrySp, numRegions, code, callingCode, capital;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brazil);

        countryHandlerThread = new HandlerThread("CountryFetcherThread");
        countryHandlerThread.start();

        CountryFetcher countryFetcher = new CountryFetcher(new Handler(countryHandlerThread.getLooper()), this);
        countryFetcher.fetchCountries("BR");
        get_resources();
    }

    public void get_resources() {
        countrySp = findViewById(R.id.country_name);
        numRegions = findViewById(R.id.regions);
        code = findViewById(R.id.code);
        callingCode = findViewById(R.id.calling);
        capital = findViewById(R.id.capital);
    }

    @Override
    public void CountryFetchSuccess(List<Country> countries) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                StringBuilder countryNames = new StringBuilder();
                StringBuilder numRegion = new StringBuilder();
                StringBuilder codes = new StringBuilder();
                StringBuilder callingCodes = new StringBuilder();
                StringBuilder capitals = new StringBuilder();
                for (Country country : countries) {
                    countryNames.append(country.getName()).append("\n");
                    numRegion.append(country.getNumRegions());
                    codes.append(country.getCode());
                    callingCodes.append(country.getCallingCode());
                    capitals.append(country.getCapital());
                }
                countrySp.setText(countryNames.toString());
                numRegions.setText(numRegion.toString());
                code.setText(codes.toString());
                callingCode.setText(callingCodes.toString());
                capital.setText(capitals.toString());
            }
        });
    }

    @Override
    public void CountryFetchError() {
        Log.e("Saas", "Erro na busca de paises");
    }
}