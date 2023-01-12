package com.example.drugapp;

import androidx.appcompat.app.AppCompatActivity;



import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import android.widget.Toast;



public class MainActivity extends AppCompatActivity {
    EditText etName , etAmount , etFrequency ;
    Button addButton , overviewButton ;
    ListView lv ;
    ArrayAdapter medDataArrayAdapter;
    DataBaceHelper dataBaceHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ayctivity_main2);

        etName = (EditText) findViewById(R.id.name);
        etAmount = (EditText) findViewById(R.id.amount);
        etFrequency = (EditText) findViewById(R.id.frequency);
        addButton = (Button) findViewById(R.id.add);
        overviewButton = (Button) findViewById(R.id.overview);
        lv = findViewById(R.id.lv);
        dataBaceHelper = new DataBaceHelper(MainActivity.this);

        showMedDataOnListView(dataBaceHelper);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MedData medData;
                try {
                    medData = new MedData(-1, etName.getText().toString(),
                            Integer.parseInt(etAmount.getText().toString()),
                            Integer.parseInt(etFrequency.getText().toString()));
                    Toast.makeText(MainActivity.this, medData.toString(), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    medData = new MedData(-1, "", 0, 0);
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }

                DataBaceHelper dataBaceHelper = new DataBaceHelper(MainActivity.this);
                boolean success = dataBaceHelper.addOne(medData);
                Toast.makeText(MainActivity.this, "Success" + success, Toast.LENGTH_SHORT).show();
                showMedDataOnListView(dataBaceHelper);

            }
        });


        overviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBaceHelper dataBaceHelper = new DataBaceHelper(MainActivity.this);
                showMedDataOnListView(dataBaceHelper);
            }
        });


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MedData clickedMedData = (MedData) parent.getItemAtPosition(position);
                dataBaceHelper.deleteOne(clickedMedData);
                showMedDataOnListView(dataBaceHelper);
                Toast.makeText(MainActivity.this, "Delete" + clickedMedData.toString(), Toast.LENGTH_SHORT).show();
            }
        });


    }


    private void showMedDataOnListView(DataBaceHelper dataBaceHelper) {
        medDataArrayAdapter = new ArrayAdapter<MedData>(MainActivity.this, android.R.layout.simple_list_item_1, dataBaceHelper.getEvryone());
        lv.setAdapter(medDataArrayAdapter);
    }



}
