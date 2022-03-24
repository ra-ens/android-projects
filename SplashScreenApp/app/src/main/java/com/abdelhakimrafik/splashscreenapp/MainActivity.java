package com.abdelhakimrafik.splashscreenapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button btnLogin;
    EditText etUsername, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = findViewById(R.id.btn_login);
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Overview.class);
                intent.putExtra("username", etUsername.getText().toString());
                intent.putExtra("password", etPassword.getText().toString());
                startActivity(intent);
                finish();
            }
        });
    }
}