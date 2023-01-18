package com.example.drugapp;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

;


public class MainActivity extends AppCompatActivity
{
    //private Button Alarm, notifyBtn, Neurologe, Orthopäde, Hausarzt, HNO_Arzt, Zahnarzt,
           // Kardiologe, Notarzt, Apotheke, eingenommen;
    Button eingenommen;
    private TextView med_name, med_amount, med_time_txt;

    private FloatingActionButton add, show, s_next, s_taken;

    private NotificationMed nm;
    private Database db;
    private DateAndTimeDatabase dtad;

    private ArrayList med_time;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dtad = new DateAndTimeDatabase(MainActivity.this);
        db = new Database(MainActivity.this);

        createMain();
        floatingButtons();
        show_next_to_take();

    }

    private void createMain()
    {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationChannel channel = new NotificationChannel("My Notification", "My Notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }


        med_time = new ArrayList<>();


        //benachrichtigung nur einmal einstellen - beim ersten boot up

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if (!prefs.getBoolean("firstTime", false))
        {
            System.out.println("Nur das erste mal :)");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            {
                //Benachrichtigungen nur beim ersten boot einstellen
               // getData();
                int size = med_time.size();
                for (int i = 0; i < size - 1; i++)
                {
                    LocalTime lt = LocalTime.parse(med_time.get(i).toString());
                    System.out.println("Test ob richtig gelesen (Main Activity): " + lt.getHour() + ":" + lt.getMinute() + " Uhr");
                    nm = new NotificationMed(MainActivity.this);
                    nm.setAlarm(lt.getHour(), lt.getMinute());
                }

            }
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("firstTime", true);
            editor.apply();
        }
    }

    private void floatingButtons()
    {
        add = findViewById(R.id.add_float);
        add.setOnClickListener(view ->
        {
            Intent ne_intent = new Intent(MainActivity.this, AddMedicament.class);
            startActivity(ne_intent);
        });

        show = findViewById(R.id.s_all_meds_float);
        show.setOnClickListener(view ->
        {
            Intent db_intent = new Intent(MainActivity.this, displayDatabase.class);
            startActivity(db_intent);
        });

        s_next = findViewById(R.id.s_next_float);
        s_next.setOnClickListener(view -> {
            Intent ne_intent = new Intent(MainActivity.this, NextMedicamentToTake.class);
            startActivity(ne_intent);
        });

        s_taken = findViewById(R.id.s_all_float);
        s_taken.setOnClickListener(view -> {
            Intent sh_intent = new Intent(MainActivity.this, ShowTakenMedsLibrary.class);
            startActivity(sh_intent);
        });
    }

    private void show_next_to_take()
    {
        Cursor cursor = db.readAllData();
        ArrayList<String> id = new ArrayList<>();
        ArrayList<String> name = new ArrayList<>();
        ArrayList<String> time = new ArrayList<>();
        ArrayList<String> amount = new ArrayList<>();

        if(cursor.getCount() == 0)
        {
            Toast.makeText(MainActivity.this, "Keine medikamente eingetragen", Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {
            while(cursor.moveToNext())
            //alle Inhalte sind in cursor drin - hier durch alle druchiterieren und in jew. Array speichern
            {
                //-> 0 = erstes, 1 = zweites....
                id.add(cursor.getString(0));
                name.add(cursor.getString(1));
                amount.add(cursor.getString(2));
                time.add(cursor.getString(6));
            }
        }
        int size = name.size();

        //erst sortieren

        for (int i = size-1; i > 0; i--)
        {
            for (int z = 0; z < i; z++)
            {
                //System.out.println(med_time.get(i));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                {
                    LocalTime currentMed = LocalTime.parse(time.get(z));
                    LocalTime nextMed = LocalTime.parse(time.get(z+1));
                    if(currentMed.isAfter(nextMed) || currentMed.equals(nextMed))
                    {
                        //zu tauschen: id, name, menge, zeit
                        String id_temp, name_temp, amount_temp, time_temp;
                        id_temp = id.get(z);
                        id.set(z, id.get(z+1));
                        id.set(z+1, id_temp);

                        name_temp = name.get(z);
                        name.set(z, name.get(z+1));
                        name.set(z+1, name_temp);

                        amount_temp = amount.get(z);
                        amount.set(z, amount.get(z+1));
                        amount.set(z+1, amount_temp);

                        time_temp = time.get(z);
                        time.set(z, time.get(z+1));
                        time.set(z+1, time_temp);
                    }
                }
            }
        }

        //dann das aktuellste suchen

        int min = 0;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            for(int i = 0; i < size-1; i++)
            {
                System.out.println("Name :" + name);
                LocalTime currtTime = LocalTime.parse(time.get(i));
                LocalTime nextTime = LocalTime.parse(time.get(i+1));

                if(currtTime.isBefore(LocalTime.now()))
                {
                    continue;
                }
                if(nextTime.isAfter(currtTime) || nextTime.equals(LocalTime.now()))
                {
                    System.out.println("First: " + currtTime + "Now: " + LocalTime.now());
                    min = i;
                    System.out.println("MIN: " + min);
                    break;
                }
            }
        }


        String id_txt = id.get(min);
        String name_txt = name.get(min);
        med_name = findViewById(R.id.med_name);
        med_amount = findViewById(R.id.menge_txt);
        med_time_txt = findViewById(R.id.uhrzeit_txt);
        eingenommen = findViewById(R.id.eingenommen_btn);

        med_name.setText(name.get(min));
        med_amount.setText(amount.get(min));
        med_time_txt.setText(time.get(min));

        eingenommen.setOnClickListener(view -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            {
                dtad.saveTimeAndDate(id_txt, LocalTime.now(), LocalDate.now(), name_txt);
            }
        });

        //aktuellste Zeit raussuschen und entsprechendes Medikament in dem ConstraintLayout anzeigen


    }
}



