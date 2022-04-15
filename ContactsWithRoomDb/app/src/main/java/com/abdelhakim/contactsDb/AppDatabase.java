package com.abdelhakim.contactsDb;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.abdelhakim.contactsDb.interfaces.IContactDAO;
import com.abdelhakim.contactsDb.models.Contacts;

@Database(entities = {Contacts.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract IContactDAO getContactDAO();
}
