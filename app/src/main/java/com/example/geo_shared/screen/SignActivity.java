package com.example.geo_shared.screen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.geo_shared.R;

import model.Database;

public class SignActivity extends AppCompatActivity {

    EditText name, password, repassword;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        get_resources();
        db = new Database(this);
    }

    public void get_resources() {
        name = findViewById(R.id.user);
        password = findViewById(R.id.password);
        repassword = findViewById(R.id.repassword);
    }

    public void open_logs (View view) {
        String user = name.getText().toString();
        String word = password.getText().toString();
        String repass = repassword.getText().toString();

        if(TextUtils.isEmpty(user) || TextUtils.isEmpty(word) || TextUtils.isEmpty(repass)) {
            Toast.makeText(SignActivity.this, "Todos os campos devem ser registrados", Toast.LENGTH_SHORT).show();
        }
        else {
            if(word.equals(repass)) {
                Boolean viewUser = db.viewName(user);
                if(viewUser == false) {
                    Boolean insert = db.insertDb(user, word);
                    if (insert == true) {
                        Toast.makeText(SignActivity.this, "Cadastro realizado com sucesso", Toast.LENGTH_SHORT).show();
                        MainActivity.redirect(this, HallActivity.class);
                    }
                    else {
                        Toast.makeText(SignActivity.this, "O cadastro falhou", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(SignActivity.this, "O usuário já existe", Toast.LENGTH_SHORT).show();
                }
            }
            else {
                Toast.makeText(SignActivity.this, "A senha não corresponde", Toast.LENGTH_SHORT).show();
            }
        }
    }
}