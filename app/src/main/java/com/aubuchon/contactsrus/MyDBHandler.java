package com.aubuchon.contactsrus;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by saubu on 7/12/2017.
 */

public class MyDBHandler extends SQLiteOpenHelper {


    //define database variables
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contacts.db";
    public static final String TABLE_NAME = "contacts";
    public static final String  COLUMN_ID = "_id";
    public static final String COLUMN_FIRSTNAME = "firstname";
    public static final String COLUMN_LASTNAME = "lastname";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_PHONENUMBER = "phonenumber";
    public static final String COLUMN_COMPANY = "company";

    private SQLiteDatabase database;

    //constructor
    public MyDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //responsible for creating a table for the first time
    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_NAME + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_FIRSTNAME + " TEXT NOT NULL, " + COLUMN_LASTNAME + " TEXT NOT NULL " +
                COLUMN_ADDRESS + " TEXT NOT NULL, " + COLUMN_PHONENUMBER + " TEXT NOT NULL, " + COLUMN_COMPANY + " TEXT NOT NULL" + ");";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //delete the entire table if it exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        //recreate the table with the new properties
        onCreate(db);

    }

    public MyDBHandler open() throws SQLException{
        database = getWritableDatabase();  // Get reference to the database
        return this;
    }

    //add new row to the database
    public void addContact(Contacts contact){

        //content values is built into Android that allows you to add several values in one statement
        ContentValues values = new ContentValues();
        values.put(COLUMN_FIRSTNAME, contact.getFirstname());
        values.put(COLUMN_LASTNAME, contact.getLastname());
        values.put(COLUMN_PHONENUMBER, contact.getPhoneNumber());
        values.put(COLUMN_ADDRESS, contact.getAddress());
        values.put(COLUMN_COMPANY, contact.getCompany());

        open();
        database.insert(TABLE_NAME, null, values);

        //once you are done with database, then close it out to give memory back
        close();

    }


    //delete a contacts from the database
    public void deleteContact(String firstname, String lastname){

        //call the open method to get reference to the database
        open();
        //delete the row with matching firstname and lastname
        database.execSQL("DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_FIRSTNAME + "=\"" + firstname + "\"" + " AND " + COLUMN_LASTNAME + "=\"" + lastname + "\";");

    }

    //Delete all contacts from the database
    public void deleteAllContacts (){

        open();

        database.execSQL("DELETE FROM " + TABLE_NAME + ";");

    }

    public Cursor readEntry(){
        String[] allColumns = new String[]{
                COLUMN_ID, COLUMN_FIRSTNAME, COLUMN_LASTNAME, COLUMN_PHONENUMBER, COLUMN_ADDRESS, COLUMN_COMPANY};
        Cursor c = database.query(TABLE_NAME, allColumns, null , null , null, null, null);

        if(c!= null){
            c.moveToFirst();
        }
        return c;
    }
}

