package com.abdelhakim.contactsDb;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.abdelhakim.contactsDb.interfaces.IContactDAO;
import com.abdelhakim.contactsDb.models.Contacts;

public class ContactsDetailsActivity extends AppCompatActivity {

    private static final String ID = "id";
    private static final String TYPE = "type";
    private static int id;


    // the DAO to access database
    IContactDAO contactDAO;
    AppDatabase database;
    Contacts contactsDetails;

    // UI references.
    TextView contactNumber, contactAddress;
    Toolbar toolbar;
    Button btnCall, btnMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_details);

        // setup Database and get DAO
        database = Room.databaseBuilder(this, AppDatabase.class, "contactsdb")
                .allowMainThreadQueries()
                .build();
        contactDAO = database.getContactDAO();

        // get contact id
        Intent mIntent = getIntent();
        if (mIntent != null) {
            id = mIntent.getIntExtra(ID, 0);
        }

        // initialize views
        toolbar = findViewById(R.id.toolbar);
        contactNumber = findViewById(R.id.Contact_Number);
        contactAddress = findViewById(R.id.Contact_Address);
        btnCall = findViewById(R.id.Btn_Call);
        btnMessage = findViewById(R.id.Btn_Message);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", contactsDetails.getPhoneNumber(), null));
                startActivity(intent);
            }
        });

        btnMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", contactsDetails.getPhoneNumber(), null));
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.settings, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        contactsDetails = contactDAO.getItemById(id);
        Log.e("ttt", "called " + contactsDetails.getFirstName());
        toolbar.setTitle(contactsDetails.getFirstName() + " " + contactsDetails.getLastName());
        contactNumber.setText(contactsDetails.getPhoneNumber());
        contactAddress.setText(contactsDetails.getAddress());
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_edit:
                Intent intent = new Intent(this, NewContactActivity.class);
                intent.putExtra(TYPE, 1);
                intent.putExtra(ID, contactsDetails.getUid());
                startActivity(intent);
                return true;
            case R.id.menu_delete:
                contactDAO.deleteContacts(contactsDetails);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
