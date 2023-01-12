package com.example.drugapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

class DataBaceHelper extends SQLiteOpenHelper{


    public static final String CREATE_TABLE = "CREATE TABLE";
    public static final String MEDDATA_TABLE = "MEDDATA_TABLE";
    public static final String COLUMN_MEDDATA_NAME = "MEDDATA_NAME";
    public static final String COLUMN_MEDDATA_AMOUNT = "MEDDATA_AMOUNT";
    public static final String COLUMN_MEDDATA_FREQUENCY = "MEDDATA_FREQUENCY";
    public static final String COLUMN_ID = "ID";

    public DataBaceHelper(@Nullable Context context) {
        super(context, "medData.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String  createTableStatment = "CREATE TABLE " + MEDDATA_TABLE + "  (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_MEDDATA_NAME + " TEXT , " + COLUMN_MEDDATA_AMOUNT + " INT , " + COLUMN_MEDDATA_FREQUENCY + " INT)";
        db.execSQL(createTableStatment);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(MedData medData) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_MEDDATA_NAME , medData.getName());
        cv.put(COLUMN_MEDDATA_AMOUNT, medData.getAmount());
        cv.put(COLUMN_MEDDATA_FREQUENCY ,  medData.getFrequency());
        long insert = db.insert(MEDDATA_TABLE, null, cv);
        if(insert==-1)
            return false;
        else {
            return true ;
        }
    }



    public boolean deleteOne(MedData clickedMedData){
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + MEDDATA_TABLE + "WHERE" + COLUMN_ID + " = " +clickedMedData.getId();
        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.moveToFirst()){
            return true ;
        }
        else{
            return false;
        }

    }


    public List<MedData> getEvryone(){
        List<MedData> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM " + MEDDATA_TABLE ;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString,  null);
        if(cursor.moveToFirst() ){
            do {
                int medDataID = cursor.getInt(0);
                String medDataNmae = cursor.getString(1);
                int medDataAmount = cursor.getInt(2);
                int medDataFrequency = cursor.getInt(3);
                MedData medData = new MedData(medDataID, medDataNmae ,medDataAmount ,medDataFrequency);
                returnList.add(medData);
            }while(cursor.moveToNext());
        }
        else{
            // failier. do not do anything  to the list.
        }
        cursor.close();
        db.close();
        return returnList;
    }



}