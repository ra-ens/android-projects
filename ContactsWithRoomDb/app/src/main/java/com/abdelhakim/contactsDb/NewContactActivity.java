package com.abdelhakim.contactsDb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.abdelhakim.contactsDb.interfaces.IContactDAO;
import com.abdelhakim.contactsDb.models.Contacts;

public class NewContactActivity extends AppCompatActivity {

    /**
     * type to identity the intent if its for new contact or to edit contact
     * id instance for the intent
     */
    private static final String ID = "id";
    private static final String TYPE = "type";


    boolean isEditing = false;
    int id ,type;

    // UI references.
    Toolbar toolbar;
    EditText firstName, lastName, phoneNumber, address;

    // the DAO to access database
    IContactDAO contactDAO;
    AppDatabase database;

    Contacts contactsDetails;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        // setup Database and get DAO
        database = Room.databaseBuilder(this, AppDatabase.class, "contactsdb")
                .allowMainThreadQueries()
                .build();
        contactDAO = database.getContactDAO();

        // initialize views
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        firstName = findViewById(R.id.First_Name);
        lastName = findViewById(R.id.Last_Name);
        phoneNumber = findViewById(R.id.Phone_Number);
        address = findViewById(R.id.Address);

        // get the intent and check if it was editing existing contact or create a new one
        Intent mIntent = getIntent();
        if (mIntent != null) {
            id = mIntent.getIntExtra(ID, 0);
            type = mIntent.getIntExtra(TYPE, 0);
            if (type == 1){
                isEditing = true;
                contactsDetails = contactDAO.getItemById(id);
                getSupportActionBar().setTitle("Edit Contact");
                firstName.setText(contactsDetails.getFirstName());
                lastName.setText(contactsDetails.getLastName());
                phoneNumber.setText(contactsDetails.getPhoneNumber());
                address.setText(contactsDetails.getAddress());
            }else {
                getSupportActionBar().setTitle("Add Contact");
            }
        }


    }


    private void setContact() {
        String firstName_s = firstName.getText().toString();
        String lastName_s = lastName.getText().toString();
        String phoneNumber_s = phoneNumber.getText().toString().trim();
        String address_s = address.getText().toString();
        if (firstName_s.isEmpty()){
            firstName.setError("cannot be empty");
            return;
        }
        if (lastName_s.isEmpty()){
            lastName.setError("cannot be empty");
            return;
        }
        if (phoneNumber_s.isEmpty() ){
            phoneNumber.setError("cannot be empty");
        }

        if (isEditing){
            Contacts contacts = new Contacts(contactsDetails.getUid(), firstName_s, lastName_s, phoneNumber_s, address_s);
            contactDAO.updateContacts(contacts);
            finish();
        }else {
            Contacts contacts = new Contacts(0, firstName_s, lastName_s, phoneNumber_s, address_s);
            contactDAO.insertContacts(contacts);
            finish();
        }
    }

    public void clearFields() {
        firstName.setText("");
        lastName.setText("");
        phoneNumber.setText("");
        address.setText("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.new_contact_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_save:
                // set contact and check if it was valid
                setContact();
                break;
            case R.id.menu_clear:
                clearFields();
        }
        return true;
    }
}
