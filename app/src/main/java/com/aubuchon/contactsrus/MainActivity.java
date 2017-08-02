package com.aubuchon.contactsrus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //define our widget variables
    private EditText firstnameET, lastnameET, addressET, phonenumberET, companyET;
    private Button addButton, deleteButton, showAllButton, deleteAllButton;

    //define database handler
    MyDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHandler = new MyDBHandler(this);

        //get reference to the widgets
        firstnameET = (EditText) findViewById(R.id.firstNameET);
        lastnameET = (EditText) findViewById(R.id.lastNameET);
        addressET = (EditText) findViewById(R.id.addressET);
        phonenumberET = (EditText) findViewById(R.id.phoneNumberET);
        companyET = (EditText) findViewById(R.id.companyET);
        addButton = (Button) findViewById(R.id.addButton);
        deleteButton = (Button) findViewById(R.id.deleteButton);
        showAllButton = (Button) findViewById(R.id.showAllButton);
        deleteAllButton = (Button) findViewById(R.id.deleteAllButton);

        firstnameET.requestFocus();
    }

    //add a contacts to the database
    public void addClick(View view) {

        Contacts contacts = new Contacts(firstnameET.getText().toString(), lastnameET.getText().toString(), addressET.getText().toString(), phonenumberET.getText().toString(), companyET.getText().toString());
        dbHandler.addContact(contacts);



        //clear text fields

        firstnameET.setText("");
        lastnameET.setText("");
        addressET.setText("");
        phonenumberET.setText("");
        companyET.setText("");
        //request focus on the firstname
        firstnameET.requestFocus();
    }

    //delete a contact
    public void deleteClick(View view) {

        String firstname = firstnameET.getText().toString();
        String lastname = lastnameET.getText().toString();
        dbHandler.deleteContact(firstname, lastname);

        firstnameET.setText("");
        lastnameET.setText("");
        //request focus on the firstname
        firstnameET.requestFocus();
    }

    public void showAllClick(View view) {

        //open up a new screen
        Intent i = new Intent(this, DisplayAllActivity.class);
        startActivity(i);

    }

    public void deleteAllClick(View view) {

        dbHandler.deleteAllContacts();

        firstnameET.setText("");
        lastnameET.setText("");
        //request focus on the firstname
        firstnameET.requestFocus();
    }

    public void sendOutBroadcast(View view) {
        Intent i = new Intent();
        i.setAction("com.aubuchon.sendbroadcast");
        i.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        sendBroadcast(i);
    }
}
