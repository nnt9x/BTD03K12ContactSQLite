package com.example.btd03k12contactsqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.btd03k12contactsqlite.dao.ContactDAO;
import com.example.btd03k12contactsqlite.model.Contact;

public class DetailActivity extends AppCompatActivity {


    // Config DB
    private AppDatabase appDatabase;
    private ContactDAO contactDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent i = getIntent();
        if(i!=null ){
            long id = i.getLongExtra("myID",-1);
            if(id != -1){
                // Init DB
                appDatabase = Room.databaseBuilder(this, AppDatabase.class,
                        "myContact").allowMainThreadQueries().build();
                contactDAO = appDatabase.getContactDao();

                Contact contact = contactDAO.getById(id);
                Toast.makeText(DetailActivity.this,contact.toString(),Toast.LENGTH_SHORT).show();
            }
        }
    }
}