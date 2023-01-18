package com.example.drugapp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalTime;

public class edit_medicament extends AppCompatActivity
{
    EditText med_name, med_amount, med_time;
    RadioButton morgens_btn, mittags_btn, abends_btn;

    Button speichern_btn, delete_btn;

    String id, name_med, amount_med, time_med, morgens, mittags, abends;
    Boolean morgens_b, mittags_b, abends_b;
    Database db = new Database(edit_medicament.this);

    NotificationMed nm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_medicament_act);

        med_name = findViewById(R.id.E_name_edit_text2);
        med_amount = findViewById(R.id.E_amount_edit_text);
        med_time = findViewById(R.id.etHour);


        morgens_btn = findViewById(R.id.edit_morgens_btn);
        mittags_btn = findViewById(R.id.edit_mittags_btn);
        abends_btn = findViewById(R.id.edit_abends_btn);

        getAndSetIntentData();

        activateRadioButtons();

        delete_btn = findViewById(R.id.E_clear_button);
        delete_btn.setOnClickListener(view ->
        {
            db.deleteMedicament(id);
            Intent getBack = new Intent(edit_medicament.this, displayDatabase.class);
            startActivity(getBack);
        });

        speichern_btn = findViewById(R.id.E_save_button);
        speichern_btn.setOnClickListener(view ->
        {
            name_med = med_name.getText().toString();
            amount_med = med_amount.getText().toString();
            time_med = med_time.getText().toString();
            morgens_b = morgens_btn.isChecked() ? true : false;
            mittags_b = mittags_btn.isChecked() ? true : false;
            abends_b = abends_btn.isChecked() ? true : false;

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                db.updateMedicament(id, name_med, amount_med, morgens_b, mittags_b, abends_b, LocalTime.parse(time_med));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                {
                    nm = new NotificationMed(edit_medicament.this);
                    LocalTime lt = LocalTime.parse(time_med);
                    System.out.println("Test ob richtig gelesen: " +  lt.getHour() + ":" + lt.getMinute() + " Uhr");
                    nm.setAlarm(lt.getHour(), lt.getMinute());
                }
            }
        });
    }

    void getAndSetIntentData()
    {

        if(getIntent().hasExtra("id") )
        {

            //Daten erst abrufen
            id = getIntent().getStringExtra("id");
            name_med = getIntent().getStringExtra("Name");
            amount_med = getIntent().getStringExtra("Menge");
            time_med = getIntent().getStringExtra("Uhrzeit");
            morgens = getIntent().getStringExtra("Morgens");
            mittags = getIntent().getStringExtra("Mittags");
            abends = getIntent().getStringExtra("Abends");

            morgens_b = morgens.equals("1") ? true : false;
            mittags_b = mittags.equals("1") ? true : false;
            abends_b = abends.equals("1") ? true : false;

            //dann setzen
            med_name.setText(name_med);
            med_amount.setText(amount_med);
            med_time.setText(time_med);


            if(morgens_b)
            {
                morgens_btn.setChecked(true);
            }
            else
            {
                morgens_btn.setChecked(false);
            }

            if(mittags_b)
            {
                mittags_btn.setChecked(true);
            }
            else
            {
                mittags_btn.setChecked(false);
            }

            if(abends_b)
            {
                abends_btn.setChecked(true);
            }
            else
            {
                abends_btn.setChecked(false);
            }
        }
        else
        {
            Toast.makeText(this, "Keine Daten vorhanden!", Toast.LENGTH_SHORT).show();
        }
    }

    void activateRadioButtons()
    {
        morgens_btn.setOnClickListener(view -> {
            if(morgens_btn.isSelected())
            {
                morgens_btn.setSelected(false);
                morgens_btn.setChecked(false);
                morgens_btn.setClickable(true);
            }
            else
            {
                morgens_btn.setSelected(true);
                morgens_btn.setClickable(true);
            }

        });

        mittags_btn.setOnClickListener(view -> {
            if(mittags_btn.isSelected())
            {
                mittags_btn.setSelected(false);
                mittags_btn.setChecked(false);
                mittags_btn.setClickable(true);
            }
            else
            {
                mittags_btn.setSelected(true);
                mittags_btn.setClickable(true);
            }

        });

        abends_btn.setOnClickListener(view -> {
            if(abends_btn.isSelected())
            {
                abends_btn.setSelected(false);
                abends_btn.setChecked(false);
                abends_btn.setClickable(true);
            }
            else
            {
                abends_btn.setSelected(true);
                abends_btn.setClickable(true);
            }

        });
    }
}
