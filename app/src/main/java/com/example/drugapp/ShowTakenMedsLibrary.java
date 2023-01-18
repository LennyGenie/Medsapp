package com.example.drugapp;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ShowTakenMedsLibrary extends AppCompatActivity
{
    RecyclerView rv_meds;

    //Database db;
    DateAndTimeDatabase datd;
    ArrayList<String> med_id, med_name, taken_day, taken_time;

    ShowMedsAdapter showAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_taken_meds_library);

        //db = new Database(ShowTakenMedsLibrary.this);
        datd = new DateAndTimeDatabase(ShowTakenMedsLibrary.this);
        rv_meds = findViewById(R.id.rv_meds_taken);

        med_id = new ArrayList<>();
        med_name = new ArrayList<>();
        taken_day = new ArrayList<>();
        taken_time = new ArrayList<>();

        putDataInArrays();
        sortTheArrays();

        showAdapter = new ShowMedsAdapter(ShowTakenMedsLibrary.this, med_id, med_name, taken_day, taken_time);
        rv_meds.setAdapter(showAdapter);
        rv_meds.setLayoutManager(new LinearLayoutManager(ShowTakenMedsLibrary.this));
    }

    void putDataInArrays()
    {
        Cursor cursor = datd.readAllData();
        if(cursor.getCount() == 0)
        {
            Toast.makeText(this, "Keine medikamente eingetragen", Toast.LENGTH_SHORT).show();
        }
        else
        {
            while(cursor.moveToNext())
            //alle Inhalte sind in cursor drin - hier durch alle druchiterieren und in jew. Array speichern
            {
                med_id.add(cursor.getString(1));
                taken_time.add(cursor.getString(2));
                taken_day.add(cursor.getString(3));
                med_name.add(cursor.getString(4));
            }
        }
    }

    void sortTheArrays()
    {
        int length = med_name.size();
        for (int z = length-1; z > 0; z--)
        {
            for (int i = 0; i < z; i++) {
                String id_temp, time_temp, day_temp, name_temp;
                id_temp = med_id.get(i);
                med_id.set(i, med_id.get(i +1));
                med_id.set(i + 1, id_temp);

                time_temp = taken_time.get(i);
                taken_time.set(i, taken_time.get(i + 1));
                taken_time.set(i + 1, time_temp);

                day_temp = taken_day.get(i);
                taken_day.set(i, taken_day.get(i + 1));
                taken_day.set(i + 1, day_temp);

                name_temp = med_name.get(i);
                med_name.set(i, med_name.get(i +1));
                med_name.set(i +1, name_temp);
            }
        }
    }


}