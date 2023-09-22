package com.example.geo_shared.screen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.geo_shared.R;

public class HallActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hall);
    }

    public void open_sign (View view) {
        MainActivity.redirect(this, SignActivity.class);
    }

    public void open_login (View view) {
        MainActivity.redirect(this, LoginActivity.class);
    }
}