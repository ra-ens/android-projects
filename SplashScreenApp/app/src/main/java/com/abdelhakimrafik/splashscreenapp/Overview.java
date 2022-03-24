package com.abdelhakimrafik.splashscreenapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Overview extends AppCompatActivity {

    TextView tvUsername, tvPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        tvUsername = findViewById(R.id.tv_username);
        tvPassword = findViewById(R.id.tv_password);

        Intent intent = getIntent();
        String username = tvUsername.getText().toString();
        String password = tvPassword.getText().toString();

        username += " : " + intent.getStringExtra("username");
        password += " : " + intent.getStringExtra("password");

        tvUsername.setText(username);
        tvPassword.setText(password);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast toast = Toast.makeText(getApplicationContext(), "OnStart", Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast toast = Toast.makeText(getApplicationContext(), "OnResume", Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast toast = Toast.makeText(getApplicationContext(), "OnPause", Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast toast = Toast.makeText(getApplicationContext(), "OnRestart", Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast toast = Toast.makeText(getApplicationContext(), "OnStop", Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast toast = Toast.makeText(getApplicationContext(), "OnDestroy", Toast.LENGTH_LONG);
        toast.show();
    }
}