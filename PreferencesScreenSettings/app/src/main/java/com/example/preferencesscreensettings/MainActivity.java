package com.example.preferencesscreensettings;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.prefs.Preferences;

public class MainActivity extends AppCompatActivity {

    private ConstraintLayout container;
    private TextView tvText;
    private boolean darkMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        container = findViewById(R.id.container);
        tvText = findViewById(R.id.tv_text);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Paramètres");


        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        boolean performSync = prefs.getBoolean("veille",true);
        String syncInterval = prefs.getString("veille_intervale","20");
        String fullName = prefs.getString("full_name","");
        String email = prefs.getString("email_address","");
        darkMode = prefs.getBoolean("dark_mode", false);

        Toast.makeText(this,performSync+ "",Toast.LENGTH_SHORT).show();
        Toast.makeText(this,syncInterval+ "",Toast.LENGTH_SHORT).show();
        Toast.makeText(this,fullName+ "",Toast.LENGTH_SHORT).show();
        Toast.makeText(this,email+ "",Toast.LENGTH_SHORT).show();

        changeTheme();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.settings:
                startActivity(new Intent(this, Settings.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void changeTheme() {
        if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES || darkMode == true) {
            container.setBackgroundColor(Color.BLACK);
            tvText.setTextColor(Color.WHITE);
        }
        else {
            container.setBackgroundColor(Color.WHITE);
            tvText.setTextColor(Color.BLACK);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        changeTheme();
    }
}
