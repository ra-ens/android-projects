package com.abdelhakimrafik.contactapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.abdelhakimrafik.contactapp.model.Contact;

import java.util.ArrayList;

public class ContactListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Contact> list;

    public ContactListAdapter(Context context, ArrayList<Contact> list) {
        this.context = context;
        this.list = list;
        Log.e("Init", "Initi");
    }

    @Override
    public int getCount() {
        return this.list.size();
    }

    @Override
    public Object getItem(int i) {
        return this.list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null) {
            view = LayoutInflater.from(context).
                    inflate(R.layout.list_item, viewGroup, false);
        }

        TextView fullName, email, phone;
        fullName = view.findViewById(R.id.tv_fullName);
        email = view.findViewById(R.id.tv_email);
        phone = view.findViewById(R.id.tv_phone);
        ImageView iv = view.findViewById(R.id.iv_avatar);

        // get current contact
        Contact contact = list.get(i);

        fullName.setText(contact.getFullName());
        email.setText(contact.getEmail());
        phone.setText(contact.getPhone());

        return view;
    }
}
