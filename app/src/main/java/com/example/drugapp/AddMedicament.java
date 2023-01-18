package com.example.drugapp;



import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalTime;

import de.fhbielefeld.swe.medikamentenapp.R;

public class AddMedicament extends AppCompatActivity
{
    private EditText med_name_txt, med_amount_txt, med_time_txt;
    private RadioButton morgens_btn, mittags_btn, abends_btn;
    private Button leeren_btn, speichern_btn;

    boolean morgens, mittags, abends;


    Database db = new Database(AddMedicament.this);
    NotificationMed nm;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_medicament);

        med_name_txt = findViewById(R.id.name_edit_text);
        med_amount_txt = findViewById(R.id.amount_edit_text);
        med_time_txt = findViewById(R.id.etHour);

        morgens_btn = findViewById(R.id.morgens_btn2);
        mittags_btn = findViewById(R.id.edit_mittags_btn2);
        abends_btn = findViewById(R.id.edit_abends_btn2);

        activateRadioButtons();
        leeren_btn = findViewById(R.id.clear_button);
        speichern_btn = findViewById(R.id.save_button);

        speichern_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                morgens = morgens_btn.isChecked() ? true : false;
                mittags = mittags_btn.isChecked() ? true : false;
                abends = abends_btn.isChecked() ? true : false;

                //med_time_txt.getText().toString()
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                {
                    db.addMedicament(med_name_txt.getText().toString(), med_amount_txt.getText().toString(),
                            morgens, mittags, abends, LocalTime.parse(med_time_txt.getText().toString()));

                    nm = new NotificationMed(AddMedicament.this);
                    LocalTime lt = LocalTime.parse(med_time_txt.getText());
                    System.out.println("Test ob richtig gelesen: " +  lt.getHour() + ":" + lt.getMinute() + " Uhr");
                    nm.setAlarm(lt.getHour(), lt.getMinute());
                }
            }
        });

        leeren_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                med_name_txt.getText().clear();
                med_amount_txt.getText().clear();
                med_time_txt.getText().clear();

                morgens_btn.setChecked(false);
                mittags_btn.setChecked(false);
                abends_btn.setChecked(false);
            }
        });
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
