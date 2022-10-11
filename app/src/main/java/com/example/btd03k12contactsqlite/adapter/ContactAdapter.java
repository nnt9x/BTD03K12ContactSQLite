package com.example.btd03k12contactsqlite.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.btd03k12contactsqlite.R;
import com.example.btd03k12contactsqlite.model.Contact;

import java.util.List;

public class ContactAdapter extends BaseAdapter {

    //Context, Datasource
    private Context context;
    private List<Contact> dataSource;

    public ContactAdapter(Context context, List<Contact> dataSource) {
        this.context = context;
        this.dataSource = dataSource;
    }

    @Override
    public int getCount() {
        return dataSource.size();
    }

    @Override
    public Object getItem(int i) {
        return dataSource.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_contact, viewGroup, false);
        }
        // Bind id
        TextView tvName, tvPhone;
        tvName = view.findViewById(R.id.tv_item_contact_name);
        tvPhone = view.findViewById(R.id.tv_item_contact_phone);

        // Do du lieu vao view
        Contact item = dataSource.get(i);
        tvName.setText(item.getName());
        tvPhone.setText(item.getPhone());

        return view;
    }
}
