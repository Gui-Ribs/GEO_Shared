package com.example.geo_shared.screen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.geo_shared.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void open_main(View view) {
        MainActivity.redirect(this, MainActivity.class);
    }

    public void open_hall(View view) {
        MainActivity.redirect(this, HallActivity.class);
    }
}