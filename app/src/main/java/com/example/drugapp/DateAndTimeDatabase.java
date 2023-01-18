package com.example.drugapp;





import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.SyncStateContract;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DateAndTimeDatabase extends SQLiteOpenHelper
{
    private Context context;
    private static final String DATABASE_NAME = "TimeAndDate5.db";
    private static final int DATABASE_VERSION = 1 ;

    private static final String TABLE_NAME = "Tage_und_Uhrzeiten";
    private static final String COLUMN_NR = "_nr";
    private static final String COLUMN_UHRZEIT = "Uhrzeit";
    private static final String COLUMN_NAME = "NAME";
    private static final String COLUMN_DATUM = "Datum";
    private static final String COLUMN_ID = "id";

    ArrayList<String> time_a, date_a, id_a, name_a;

    public DateAndTimeDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_NR + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_ID + " TEXT, " +
                COLUMN_UHRZEIT + " TEXT, " +
                COLUMN_DATUM + " TEXT, " +
                COLUMN_NAME + " TEXT);";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    //ToDo Uhrzeit und Datum noch richtig formattieren
    void saveTimeAndDate(String id, LocalTime time, LocalDate day, String name)
    {
        SQLiteDatabase db = this.getWritableDatabase(); //objekt der sqlitedatabase erstellen um objekte in die datenbank einzutragen
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ID, id);
        //diese id ist die id von der normalen Datenbank
        // - dort wird auto increment genutzt, hier nicht
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O)
        {
            int min = time.getMinute();
            int hour = time.getHour();
            String zeit = hour + ":" + min;
            cv.put(COLUMN_UHRZEIT, zeit);
        }

        cv.put(COLUMN_DATUM, day.toString());
        cv.put(COLUMN_NAME, name);

        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1)
        {
            Toast.makeText(context, "Fehler beim hinzufügen in Datum.. Datenbank!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "Erfolgreich hinzugefügt. ", Toast.LENGTH_SHORT).show();
        }
        System.out.println("Hab hinzugefügt");
    }

    Cursor readAllData()
    {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null)
        {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    //ToDo glaube brauche den Datentypen brauch ich nicht & glaube generell die ganze Fkt nicht :)
    Map<String, List<String>> readData(String id)
    {
        time_a = new ArrayList<>();
        date_a = new ArrayList<>();
        id_a = new ArrayList<>();
        name_a = new ArrayList<>();

        Map<String, List<String>> all = new HashMap<>();

        Cursor cursor = readAllData();
        if(cursor.getCount() == 0)
        {
            Toast.makeText(context, "Kein Tag/Datum eingetragen", Toast.LENGTH_SHORT).show();
        }
        else
        {
            while(cursor.moveToNext())
            //alle Inhalte sind in cursor drin - hier durch alle druchiterieren und in jew. Array speichern
            {
                //-> 0 ist _nr in DB, 1 ist id, 2 Uhrzeit, 3 Datum
                if(cursor.getString(1).equals(id))
                {
                    id_a.add(cursor.getString(1));
                    time_a.add(cursor.getString(2));
                    date_a.add(cursor.getString(3));
                    name_a.add(cursor.getString(4));

                    System.out.println("ID: " + cursor.getString(1));
                    System.out.println("Time :" + cursor.getString(2));
                    System.out.println("Date :" + cursor.getString(3));
                    System.out.println("Name :" + cursor.getString(4));
                }

            }
            all.put("Time", time_a);
            all.put("Date", date_a);
        }
        return all;
    }

    void deleteAllData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }

}


