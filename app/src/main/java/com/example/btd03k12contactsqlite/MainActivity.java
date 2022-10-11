package com.example.btd03k12contactsqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.btd03k12contactsqlite.adapter.ContactAdapter;
import com.example.btd03k12contactsqlite.dao.ContactDAO;
import com.example.btd03k12contactsqlite.dialog.ContactDialog;
import com.example.btd03k12contactsqlite.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lvContact;

    private ContactAdapter contactAdapter;
    private List<Contact> dataSource;

    private ContactDialog contactDialog = null;

    // Config DB
    private AppDatabase appDatabase;
    private ContactDAO contactDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvContact = findViewById(R.id.lvContact);

        // Init DB
        appDatabase = Room.databaseBuilder(this, AppDatabase.class,
                "myContact").allowMainThreadQueries().build();
        contactDAO = appDatabase.getContactDao();

        // Fake du lieu
        dataSource = contactDAO.getAll();

        contactAdapter = new ContactAdapter(this, dataSource);

        lvContact.setAdapter(contactAdapter);

        lvContact.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                new AlertDialog.Builder(MainActivity.this)
                        .setMessage("Bạn có muốn xóa " + dataSource.get(i).getName())
                        .setTitle("Cảnh báo")
                        .setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int j) {
                                Contact contact = dataSource.get(i);
                                contactDAO.delete(contact);

                                dataSource.remove(i);
                                contactAdapter.notifyDataSetChanged();
                            }
                        })
                        .create().show();
                return false;
            }
        });
        lvContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Gui Id sang DetailActiviyu
                Contact item = dataSource.get(i);
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("myID", item.getId());
                Log.d("myID", item.getId()+"");
                startActivity(intent);
            }
        });

    }

    public void showDialog(View view) {
        // Show dialog
        if (contactDialog == null) {
            contactDialog = new ContactDialog(this) {
                @Override
                protected void processAddNewContact(Contact contact) {
                    long lastId = contactDAO.insert(contact);
                    contact.setId(lastId);
                    dataSource.add(contact);
                    contactAdapter.notifyDataSetChanged();
                }
            };
        }
        contactDialog.show();
    }
}