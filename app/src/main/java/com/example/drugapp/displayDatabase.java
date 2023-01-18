package com.example.drugapp;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

// https://www.youtube.com/watch?v=hJPk50p7xwA&list=WL&index=37&t=530s
//https://www.youtube.com/watch?v=VQKq9RHMS_0&list=WL&index=30&t=3s
public class displayDatabase extends AppCompatActivity
{
    RecyclerView recyclerView_in_displayDatabase;

    Database myDB;
    ArrayList<String> med_name, med_amount, med_morgens, med_mittags, med_abends, med_time, med_id;

    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_database);

        myDB = new Database(displayDatabase.this);
        recyclerView_in_displayDatabase = findViewById(R.id.recyclerView_in_displayDatabase);

        med_name = new ArrayList<>();
        med_amount = new ArrayList<>();
        med_morgens = new ArrayList<>();
        med_mittags = new ArrayList<>();
        med_abends = new ArrayList<>();
        med_time = new ArrayList<>();
        med_id = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(displayDatabase.this, med_name, med_amount, med_morgens, med_mittags, med_abends, med_time, med_id);
        System.out.println("Anzahl elemente" + customAdapter.getItemCount());
        recyclerView_in_displayDatabase.setAdapter(customAdapter);
        recyclerView_in_displayDatabase.setLayoutManager(new LinearLayoutManager(displayDatabase.this));
    }



    void storeDataInArrays()
    {
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0)
        {
            Toast.makeText(this, "Keine medikamente eingetragen", Toast.LENGTH_SHORT).show();
        }
        else
        {
            while(cursor.moveToNext())
            //alle Inhalte sind in cursor drin - hier durch alle druchiterieren und in jew. Array speichern
            {
                //-> 0 = erstes, 1 = zweites....
                med_id.add(cursor.getString(0));
                med_name.add(cursor.getString(1));
                med_amount.add(cursor.getString(2));
                med_morgens.add(cursor.getString(3));
                med_mittags.add(cursor.getString(4));
                med_abends.add(cursor.getString(5));
                med_time.add(cursor.getString(6));
            }
        }
    }
}