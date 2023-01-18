package com.example.drugapp;




import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;

import de.fhbielefeld.swe.medikamentenapp.R;

public class NextMedicamentToTake extends AppCompatActivity {

    RecyclerView rv_to_take;

    Database myDB;
    ArrayList<String> med_name, med_amount, med_time, med_id;

    AdaptForNextToTake adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_medicament_to_take);

        myDB = new Database(NextMedicamentToTake.this);
        rv_to_take = findViewById(R.id.recyclerView_to_take);

        med_name = new ArrayList<>();
        med_amount = new ArrayList<>();
        med_time = new ArrayList<>();
        med_id = new ArrayList<>();

        storeDataInArrays();
        sortTheArrays();

        adapter = new AdaptForNextToTake(this, med_name, med_amount, med_time, med_id);
        rv_to_take.setAdapter(adapter);
        rv_to_take.setLayoutManager(new LinearLayoutManager(NextMedicamentToTake.this));
    }

    private void storeDataInArrays()
    {

        Cursor cursor = myDB.readAllData();

        System.out.println("store data in arrays: " + cursor.getCount());
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "Keine medikamente eingetragen", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext())
            //alle Inhalte sind in cursor drin - hier durch alle druchiterieren und in jew. Array speichern
            {
                //-> 0 = erstes, 1 = zweites....
                //das aus Database normal
                med_id.add(cursor.getString(0));
                med_name.add(cursor.getString(1));
                med_amount.add(cursor.getString(2));
                med_time.add(cursor.getString(6));
            }
        }
    }


    //vergleichen - und dreicksstausch wenn größer...

    void sortTheArrays() {
        //vergleicht die Uhrzeit vom aktuellen element in ArrayListe, mit Zeit des nächsten
        //sortiert von aktuell nach noch genug Zeit (also fürh -> spät)
        //dann sieht man die Medikamente die zuerst eingenommen werden müssen
        int length = med_name.size();

        for (int i = length-1; i > 0; i--)
        {
            for (int z = 0; z < i; z++)
            {
                //System.out.println(med_time.get(i));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                {
                    LocalTime currentMed = LocalTime.parse(med_time.get(z));
                    LocalTime nextMed = LocalTime.parse(med_time.get(z+1));
                    System.out.println("Current: " + currentMed);
                    System.out.println("Next: " + nextMed);
                    System.out.println("_______________________________\n" );


                    /*
                    if this LocalTime is greater than LocalTime passed as a parameter, then a positive value is returned
                    if this LocalTime is equal to the LocalTime passed as a parameter, then a zero (0) is returned
                    if this LocalTime is less than LocalTime passed as a parameter then a negative value is returned.
                    */
                    if(currentMed.isAfter(nextMed) || currentMed.equals(nextMed))
                    {
                        //zu tauschen: id, name, menge, zeit
                        String id_temp, name_temp, amount_temp, time_temp;
                        id_temp = med_id.get(z);
                        med_id.set(z, med_id.get(z+1));
                        med_id.set(z+1, id_temp);

                        name_temp = med_name.get(z);
                        med_name.set(z, med_name.get(z+1));
                        med_name.set(z+1, name_temp);

                        amount_temp = med_amount.get(z);
                        med_amount.set(z, med_amount.get(z+1));
                        med_amount.set(z+1, amount_temp);

                        time_temp = med_time.get(z);
                        med_time.set(z, med_time.get(z+1));
                        med_time.set(z+1, time_temp);
                    }
                }
            }
        }
    }
}