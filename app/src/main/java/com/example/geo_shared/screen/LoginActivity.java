package com.example.geo_shared.screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.geo_shared.R;

import model.Database;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    Database DB;
    SharedPreferences preferences;
    private static final String SHARED_MAIN = "Kmain";
    private static final String SHARED_NAME = "Kname";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        get_resources();
        DB = new Database(this);
    }

    public void get_resources() {
        username = findViewById(R.id.user);
        password = findViewById(R.id.password);
    }

    public void open_main(View view) {
        String name = username.getText().toString();
        String pass = password.getText().toString();

        preferences = getSharedPreferences(SHARED_MAIN , MODE_PRIVATE);
        String check = preferences.getString(SHARED_NAME, null);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(SHARED_NAME, name);
        editor.apply();

        if (check != null) {

            if(TextUtils.isEmpty(name) || TextUtils.isEmpty(pass)) {
                Toast.makeText(LoginActivity.this, "Os campos não podem ficar nulos", Toast.LENGTH_SHORT).show();
            }
            else {
                Boolean viewMoast = DB.viewAll(name, pass);
                if(viewMoast == true) {
                    Toast.makeText(LoginActivity.this, "Login efetuado", Toast.LENGTH_SHORT).show();
                    MainActivity.redirect(this, MainActivity.class);
                    finish();
                }
                else {
                    Toast.makeText(LoginActivity.this, "Usuário ou senha incorretos", Toast.LENGTH_SHORT).show();
                }
            }

        }
        else {
            Toast.makeText(LoginActivity.this, "O campo nome está nulo", Toast.LENGTH_SHORT).show();
        }
    }

    public void open_hall(View view) {
        MainActivity.redirect(this, HallActivity.class);
    }
}