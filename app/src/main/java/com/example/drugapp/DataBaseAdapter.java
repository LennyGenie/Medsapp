package com.example.drugapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.ContentView;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseAdapter extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contactsManager";
    private static final String TABLE_MEDDATA = "medData";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_AMOUNT = "amount";
    private static final String KEY_FREQUENCY = "frequency";

    // Konstruktor
    public DataBaseAdapter(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_MEDDATA+ "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_NAME + " TEXT,"
                + KEY_AMOUNT + " INT"
                + KEY_FREQUENCY + "INT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEDDATA);

        // Create tables again
        onCreate(db);
    }

    //********************************//
    // code to add the new  medData
    public void addOne(MedData medData){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME , medData.getName());
        cv.put(KEY_AMOUNT, medData.getAmount());
        cv.put(KEY_FREQUENCY, medData.getFrequency());
        db.insert(TABLE_MEDDATA, null , cv );
    }

    //******************************//
    // code to get the single contact
    MedData getMedData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_MEDDATA, new String[] {KEY_NAME, KEY_AMOUNT ,KEY_FREQUENCY }
                , KEY_NAME + "=?", new String[] { String.valueOf(id) },
                null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        MedData medData = new MedData();
        // return medData
        return medData;
    }

    //****************************************//
    // code to get all contacts in a list view
    public List<MedData> getAllMedData() {
        List<MedData> medDataListe = new ArrayList<MedData>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_MEDDATA;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                MedData medData = new MedData();
                medData.setName(cursor.getString(1));
                medData.setAmount(Integer.parseInt(cursor.getString(2)));

                // Adding contact to list
                medDataListe.add(medData);
            } while (cursor.moveToNext());
        }

        // return contact list
        return medDataListe ;
    }


    //*************************************************//
    // code to update the single contact
    public int updateContact(MedData medData) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, medData.getName());
        values.put(KEY_AMOUNT, medData.getAmount());
        values.put(KEY_FREQUENCY, medData.getFrequency());

        return
    }

    // Deleting single contact
    public void deleteMedData(MedData medData) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MEDDATA, KEY_ID + " = ?",
                new String[] { String.valueOf(medData.getID()) });
        db.close();
    }






}
