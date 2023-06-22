package com.example.geo_shared.screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.geo_shared.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import async.CityFetcher;
import async.CountryFetcher;
import model.City;
import model.Country;
import response.Connection;

public class MainActivity extends AppCompatActivity implements CityFetcher.CityFetchListener, CountryFetcher.OnCountryFetchListener {

    private HandlerThread countryHandlerThread, cityHandlerThread;
    TextView countryName, cityScore;
    ImageView  countryFlags;
    EditText search_input;
    CardView viewCountry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countryHandlerThread = new HandlerThread("CountryFetcherThread");
        countryHandlerThread.start();

        cityHandlerThread = new HandlerThread("CityFetcherThread");
        cityHandlerThread.start();


        CityFetcher cityFetcher = new CityFetcher(new Handler(cityHandlerThread.getLooper()), this);


        cityFetcher.fetchCities();

        get_resources();

    }


    public void get_resources() {
        countryName = findViewById(R.id.name_country);
        countryFlags = findViewById(R.id.country_image);
        viewCountry = findViewById(R.id.search);
        search_input = findViewById(R.id.input_search);
    }

    public static void redirect(Activity activity, Class Class) {
        Intent intent = new Intent(activity, Class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

    @Override
    public void CountryFetchSuccess(List<Country> countries) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                StringBuilder countryNames = new StringBuilder();
                for (Country country : countries) {
                    countryNames.append(country.getName()).append("\n");
                }
                countryName.setText(countryNames.toString());
            }
        });
    }


    @Override
    public void CountryFetchError() {
        Log.e("CityFetcher", "Erro na busca de paises");
    }

    @Override
    public void CityFetchSuccess(List<City> cities) {
    }

    @Override
    public void CityFetchError() {
        Log.e("CityFetcher", "Erro na busca de cidades");
    }
    private String lastSearch = "";
    public void search_countries(View v) {

        viewCountry.setVisibility(v.VISIBLE);
        String search = search_input.getText().toString();

        if (!search.equals(lastSearch)) {
            lastSearch = search;
            CountryFetcher countryFetcher = new CountryFetcher(new Handler(countryHandlerThread.getLooper()), this);
            countryFetcher.fetchCountries(search);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        countryHandlerThread.quit();
        cityHandlerThread.quit();
    }

}