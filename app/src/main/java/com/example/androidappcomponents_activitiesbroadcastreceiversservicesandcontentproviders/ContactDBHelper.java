package com.example.androidappcomponents_activitiesbroadcastreceiversservicesandcontentproviders;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ContactDBHelper extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME = "contact_db";
    public static final int DATABASE_VERSION = 1;

    public static final String CREATE_TABLE = "create table "+ContactContract.ContactEntry.TABLE_NAME+"("+ContactContract.ContactEntry.CONTACT_ID+" number, "
            +ContactContract.ContactEntry.NAME+" text, "+ContactContract.ContactEntry.EMAIL+" text);";
    public  static final String DROP_TABLE = "drop table if exists "+ContactContract.ContactEntry.TABLE_NAME;
    public ContactDBHelper(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        Log.d("Database Operations","Database created...");
    }

    @Override
    public void onCreate(SQLiteDatabase database)
    {
        database.execSQL(CREATE_TABLE);
        Log.d("Database Operations","Table created...");
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion)
    {
        database.execSQL(DROP_TABLE);
        onCreate(database);
    }

    public void addContact(int id, String name, String email, SQLiteDatabase database)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ContactContract.ContactEntry.CONTACT_ID, id);
        contentValues.put(ContactContract.ContactEntry.NAME, name);
        contentValues.put(ContactContract.ContactEntry.EMAIL, email);

        database.insert(ContactContract.ContactEntry.TABLE_NAME, null, contentValues);
        Log.d("Database Operations","One Row Inserted...");
    }


    public Cursor readContacts(SQLiteDatabase database)
    {
        String []projections = {ContactContract.ContactEntry.CONTACT_ID,ContactContract.ContactEntry.NAME,ContactContract.ContactEntry.EMAIL};

        return database.query(ContactContract.ContactEntry.TABLE_NAME,projections,null,null,null,null,null);
    }

    public void updateContacts(int id, String name, String email, SQLiteDatabase database)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ContactContract.ContactEntry.NAME,name);
        contentValues.put(ContactContract.ContactEntry.EMAIL,email);

        String selection = ContactContract.ContactEntry.CONTACT_ID+ " = "+id;

        database.update(ContactContract.ContactEntry.TABLE_NAME,contentValues,selection,null);
    }

    public void deleteContacts(int id, SQLiteDatabase database)
    {
        String selection = ContactContract.ContactEntry.CONTACT_ID+" = "+id;

        database.delete(ContactContract.ContactEntry.TABLE_NAME,selection,null);
    }
}
