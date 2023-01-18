package com.example.drugapp;


import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class AdaptForNextToTake extends RecyclerView.Adapter<AdaptForNextToTake.MyViewHolder>
{

    private Context context;
    private ArrayList med_name, med_amount, med_time, med_id;

    private DateAndTimeDatabase datd;

    AdaptForNextToTake(Context context, ArrayList med_name, ArrayList med_amount,
                       ArrayList med_time, ArrayList med_id)
    {
        //die Parameter den globalen Vraiablen zuweisen
        System.out.println("Konstruktor Customadapter");
        //this.activity = activity;
        this.context = context;
        this.med_name = med_name;
        this.med_amount = med_amount;
        this.med_time = med_time;
        this.med_id = med_id;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_for_next_to_take, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position)
    {
        //vorm zuweisen erst nach Zeit... sortieren
        holder.med_name_txt.setText(String.valueOf(med_name.get(position)));
        holder.med_amount_txt.setText(String.valueOf(med_amount.get(position)));
        holder.med_time_txt.setText(String.valueOf(med_time.get(position)));
        holder.med_id_txt.setText(String.valueOf(position+1));

        holder.speichern_btn.setOnClickListener(view ->
        {
            datd = new DateAndTimeDatabase(context);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            {
                datd.saveTimeAndDate(med_id.get(position).toString(), LocalTime.now(), LocalDate.now(), med_name.get(position).toString());
            }
        });
    }

    @Override
    public int getItemCount() {
        return med_name.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView med_name_txt, med_amount_txt, med_time_txt, med_id_txt;

        Button speichern_btn;

        LinearLayout row_to_take;
        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);

            med_id_txt = itemView.findViewById(R.id.medikament_nr_txt);
            med_name_txt = itemView.findViewById(R.id.med_name_txt);
            med_amount_txt = itemView.findViewById(R.id.med_menge_txt);
            med_time_txt = itemView.findViewById(R.id.med_uhrzeit_txt);
            med_id_txt = itemView.findViewById(R.id.medikament_nr_txt);

            speichern_btn = itemView.findViewById(R.id.speichern_btn);

            row_to_take = itemView.findViewById(R.id.row_to_take);
        }
    }

}
