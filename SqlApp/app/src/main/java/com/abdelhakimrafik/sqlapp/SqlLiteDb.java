package com.abdelhakimrafik.sqlapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.abdelhakimrafik.sqlapp.entities.User;

import java.util.ArrayList;

public class SqlLiteDb extends SQLiteOpenHelper {

    private SQLiteDatabase db;

    public SqlLiteDb(Context context) {
        super(context, "users.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS Users (id INTERGER PRIMARY KEY, name TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Users");
    }

    // functions
    @SuppressLint("Range")
    public ArrayList<User> getAllUsers() {
        ArrayList<User> list = new ArrayList<>();
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM Users", null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            list.add(new User(cursor.getLong(0), cursor.getString(1)));
            cursor.moveToNext();
        }
        return list;
    }

    public Long addUser(User user) {
        ContentValues values = new ContentValues();
        values.put("id", user.getId());
        values.put("name", user.getName());
        return this.getWritableDatabase().insert("users", null, values);
    }

    public int removeUser(Long id) {
        return this.getWritableDatabase().delete("users", "id=?", new String[]{String.valueOf(id)});
    }

    public int updateUser(User user) {
        ContentValues values = new ContentValues();
        values.put("name", user.getName());
        return this.getWritableDatabase().update("users", values, "id=?", new String[]{String.valueOf(user.getId())});
    }
}
