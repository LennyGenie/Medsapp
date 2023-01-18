package com.example.drugapp;


import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.widget.Toast;
import androidx.annotation.Nullable;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/*
    ToDo
        - fkt um timer vom nächsten Medikament anzuzeigen
        - speichern, wenn ein Medikament eingenommen wurde


     - eine Activity, die die Einträge in Datenbank (speichert und) anzeigt
     - objekte in die Datenbank hinzufügen
        - noch die richtigen parameter etc von karim abfragen
     - objekte aus datenbank entfernen
     - objekte in der datenbank updaten
     - sich objekte aus der datenbank anzeigen lassen
     - objekte in der datenbank suchen
 */


//https://youtu.be/hJPk50p7xwA
public class Database extends SQLiteOpenHelper
{
    private Context context;
    private static final String DATABASE_NAME = "MedicamentLibrary3.db";
    private static final int DATABASE_VERSION = 1 ;

    private static final String TABLE_NAME = "Ihre_Medikamente";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_MED_NAME = "Medikamenten_Name";
    private static final String COLUMN_AMOUNT = "Menge";
    private static final String COLUMN_TIME  = "Uhrzeit";
    private static final String COLUMN_MORGENS  = "Morgens";
    private static final String COLUMN_MITTAGS  = "Mittags";
    private static final String COLUMN_ABENDS  = "Abends";


    public Database(@Nullable Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_MED_NAME + " TEXT, " +
                COLUMN_AMOUNT + " TEXT, " +
                COLUMN_MORGENS + " TEXT, " +
                COLUMN_MITTAGS + " TEXT, " +
                COLUMN_ABENDS + " TEXT, " +
                COLUMN_TIME + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    //Medikament einfügen
    //ToDo id auch mit hinzufügen ??? o geschieht es automatisch ????
    void addMedicament(String med_name, String amount, boolean morgens, boolean mittags, boolean abends, LocalTime time)
    {
        SQLiteDatabase db = this.getWritableDatabase(); //objekt der sqlitedatabase erstellen um objekte in die datenbank einzutragen
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_MED_NAME, med_name);
        cv.put(COLUMN_AMOUNT, amount);
        cv.put(COLUMN_MORGENS, morgens);
        cv.put(COLUMN_MITTAGS, mittags);
        cv.put(COLUMN_ABENDS, abends);
        String zeit = time.toString();
        cv.put(COLUMN_TIME, zeit);

        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1)
        {
            Toast.makeText(context, "Fehler beim hinzufügen!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "Erfolgreich hinzugefügt. ", Toast.LENGTH_SHORT).show();
        }
    }

    //Medikament löschen
    void deleteMedicament(String row_id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Fehler beim löschen!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Erfolgreich gelöscht.", Toast.LENGTH_SHORT).show();
        }

    }

    //Medikament Eintrag updaten
    //ToDo noch vervollständigen
    void updateMedicament(String row_id, String med_name, String amount, boolean morgens, boolean mittags, boolean abends, LocalTime time)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_MED_NAME, med_name);
        cv.put(COLUMN_AMOUNT, amount);
        cv.put(COLUMN_MORGENS, morgens);
        cv.put(COLUMN_MITTAGS, mittags);
        cv.put(COLUMN_ABENDS, abends);
        String zeit = time.toString();
        cv.put(COLUMN_TIME, zeit);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if(result == -1)
        {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }
    }


    //alle Daten lesen
    Cursor readAllData()
    {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null) //es gibt also Inhalt in Datenbank
        {
            cursor = db.rawQuery(query, null);
            //cursor wird alle Daten aus Datenbank beinhalten
        }
        return cursor;
    }

    //dia ganze Datenbank löschen
    void deleteAllData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }
}

