package com.example.btd03k12contactsqlite;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.btd03k12contactsqlite.dao.ContactDAO;
import com.example.btd03k12contactsqlite.model.Contact;

@Database(entities = {Contact.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ContactDAO getContactDao();
}
