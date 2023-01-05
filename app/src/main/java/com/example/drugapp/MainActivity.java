package com.example.drugapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView tv ;
    Button add ,overview ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    protected void initViews(){
        tv = (TextView) findViewById(R.id.textView_hello);
        add = (Button) findViewById(R.id.button_add);
        overview = (Button)findViewById(R.id.button_Overwiew) ;
        add.setOnClickListener(View->onAddButton());
        overview.setOnClickListener(View->onOverviewButton());

    }

    protected void onAddButton(){
        // wenn diesr Button gecklickt wird , wird activity_add geöffnet
        Intent toAddActivity = new Intent(this,AddActivity.class );
        startActivity(toAddActivity);
        // Zur Veranschalichung : Nahcrict wird gezeigt wenn on add Button gecklickt wird.
        Toast.makeText(MainActivity.this, "go to activity_add" , Toast.LENGTH_SHORT).show();
    }

    protected void onOverviewButton(){
        // wenn diesr Button gecklickt wird , wird activity_overview geöffnet
        Intent toOverviewActivity = new Intent(this,OverviewActivity.class);
        startActivity(toOverviewActivity);
        // Zur Veranschalichung : Nahcrict wird gezeigt wenn on add Button gecklickt wird.
        Toast.makeText(MainActivity.this, "go to the activity_overview", Toast.LENGTH_SHORT).show();
    }
}