package com.example.btd03k12contactsqlite.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.btd03k12contactsqlite.R;
import com.example.btd03k12contactsqlite.model.Contact;

import java.util.Locale;

public abstract class ContactDialog extends Dialog {

    private EditText edtName, edtPhone, edtAddress;
    private Button btnSave, btnCancel;

    public ContactDialog(@NonNull Context context) {
        super(context);
    }

    protected abstract void processAddNewContact(Contact contact);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_contact);
        setCancelable(false);
        // Bind id
        edtName = findViewById(R.id.edtDialogContactName);
        edtPhone = findViewById(R.id.edtDialogContactPhone);
        edtAddress = findViewById(R.id.edtDialogContactAddress);
        btnCancel = findViewById(R.id.btnDialogCancel);
        btnSave = findViewById(R.id.btnDialogSave);

        // An dialog
        btnCancel.setOnClickListener(view -> dismiss());

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtName.getText().toString().trim();
                String phone = edtPhone.getText().toString().trim();
                String address = edtAddress.getText().toString().trim();

                if(name.isEmpty() || phone.isEmpty() || address.isEmpty()){
                    Toast.makeText(getContext(),"Hãy nhập đủ dữ liệu",Toast.LENGTH_SHORT).show();
                    return;
                }
                Contact contact = new Contact(name, address, phone);
                processAddNewContact(contact);
                dismiss();
                edtAddress.setText("");
                edtName.setText("");
                edtPhone.setText("");
            }
        });

    }
}
