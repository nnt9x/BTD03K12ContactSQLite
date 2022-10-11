package com.example.btd03k12contactsqlite.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.btd03k12contactsqlite.model.Contact;

import java.util.List;

@Dao
public interface ContactDAO {

    @Query("SELECT * FROM contacts")
    public List<Contact> getAll();

    @Query("SELECT * FROM contacts WHERE id=(:id)")
    public Contact getById(long id);

    @Insert
    public long insert(Contact contact);

    @Delete
    public void delete(Contact contact);

    @Update
    public void update(Contact contact);
}
