package com.abdelhakimrafik.sqlapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.abdelhakimrafik.sqlapp.entities.User;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText etUsername;
    private Button btnAdd;
    private ListView lvUsers;

    private SqlLiteDb db;
    private ArrayList<User> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsername = findViewById(R.id.username);
        btnAdd = findViewById(R.id.btn_add);
        lvUsers = findViewById(R.id.lv_users);

        // create database
        db = new SqlLiteDb(this);

        // get users list
        list = db.getAllUsers();
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        lvUsers.setAdapter(arrayAdapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etUsername.getText().toString().trim();
                // don't do anything if name is empty
                if(name.isEmpty()) return;

                // create user
                User user = new User();
                user.setName(name);
                // add user to databse
                db.addUser(user);
                etUsername.setText("");
                arrayAdapter.notifyDataSetChanged();
            }
        });
    }
}