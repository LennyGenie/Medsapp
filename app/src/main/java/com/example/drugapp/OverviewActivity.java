package com.example.drugapp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class OverviewActivity extends AppCompatActivity {
    RecyclerView datenList ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);
        initViews();
    }

    protected void initViews(){
        datenList =(RecyclerView) findViewById(R.id.daten_list);
    }
}
