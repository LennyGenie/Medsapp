package com.example.drugapp;



import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalTime;
import java.util.ArrayList;

import de.fhbielefeld.swe.medikamentenapp.R;

public class ShowMedsAdapter extends RecyclerView.Adapter<ShowMedsAdapter.MyViewHolder>
{
    private Context context;
    private ArrayList med_id, med_name, taken_day, taken_time;

    ShowMedsAdapter(Context context, ArrayList med_id, ArrayList med_name,
                    ArrayList taken_day, ArrayList taken_time)
    {
        this.context = context;
        this.med_id = med_id;
        this.med_name = med_name;
        this.taken_day = taken_day;
        this.taken_time = taken_time;
    }

    @NonNull
    @Override
    public ShowMedsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_for_meds_taken, parent, false);
        return new ShowMedsAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowMedsAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position)
    {
        System.out.println("Im Show Adapert" + med_id + " " + taken_day + "Item Count" +
                getItemCount());

        holder.med_id_txt.setText(String.valueOf(position+1));
        holder.med_name_txt.setText(String.valueOf(med_name.get(position)));
        holder.day_txt.setText(String.valueOf(taken_day.get(position)));
        holder.time_txt.setText(String.valueOf(taken_time.get(position)));

    }

    @Override
    public int getItemCount() {
        return taken_time.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView med_id_txt, med_name_txt, day_txt, time_txt;

        LinearLayout layout_show;
        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            med_id_txt = itemView.findViewById(R.id.id_s);
            med_name_txt = itemView.findViewById(R.id.med_name_s);
            day_txt = itemView.findViewById(R.id.date_s);
            time_txt = itemView.findViewById(R.id.time_s);

            layout_show = itemView.findViewById(R.id.lin_show);
        }
    }


}







