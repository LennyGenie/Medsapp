package com.example.drugapp;




import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.fhbielefeld.swe.medikamentenapp.R;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>
{
    private Context context;
    private ArrayList med_name, med_amount, med_time, med_id, med_morgens, med_mittags, med_abends;

    CustomAdapter(Context context, ArrayList med_name, ArrayList med_amount,
                  ArrayList med_morgens, ArrayList med_mittags, ArrayList med_abends,
                  ArrayList med_time, ArrayList med_id)
    {
        this.context = context;
        this.med_name = med_name;
        this.med_amount = med_amount;
        this.med_morgens = med_morgens;
        this.med_mittags = med_mittags;
        this.med_abends = med_abends;
        this.med_time = med_time;
        this.med_id = med_id;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, @SuppressLint("RecyclerView") int position)
    {
        int haufig = 0;

        if(med_morgens.get(position).equals("1"))
        {
            haufig += 1;
        }
        if(med_mittags.get(position).equals("1"))
        {
            haufig += 1;
        }
        if(med_abends.get(position).equals("1"))
        {
            haufig += 1;
        }
        String anzeigen = haufig + "mal";
        holder.med_name_txt.setText(String.valueOf(med_name.get(position)));
        holder.med_amount_txt.setText(String.valueOf(med_amount.get(position)));
        holder.med_haufigkeit_txt.setText(anzeigen);
        holder.med_time_txt.setText(String.valueOf(med_time.get(position)));
        holder.med_id_txt.setText(String.valueOf(position+1));


        holder.main_linear_my_row.setOnClickListener(view ->
        {
            Intent intent = new Intent(context, edit_medicament.class);
            intent.putExtra("id", String.valueOf(med_id.get(position)));
            intent.putExtra("Name", String.valueOf(med_name.get(position)));
            intent.putExtra("Menge", String.valueOf(med_amount.get(position)));
            intent.putExtra("Morgens", String.valueOf(med_morgens.get(position)));
            intent.putExtra("Mittags", String.valueOf(med_mittags.get(position)));
            intent.putExtra("Abends", String.valueOf(med_abends.get(position)));
            intent.putExtra("Uhrzeit", String.valueOf(med_time.get(position)));

            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return med_name.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {

        TextView med_name_txt, med_amount_txt, med_haufigkeit_txt, med_time_txt, med_id_txt;

        LinearLayout main_linear_my_row;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            med_name_txt = itemView.findViewById(R.id.med_name_txt);
            med_amount_txt = itemView.findViewById(R.id.med_menge_txt);
            med_haufigkeit_txt = itemView.findViewById(R.id.med_häufigkeit_txt);
            med_time_txt = itemView.findViewById(R.id.med_uhrzeit_txt);
            med_id_txt = itemView.findViewById(R.id.medikament_nr_txt);

            main_linear_my_row = itemView.findViewById(R.id.main_linear_my_row);
        }
    }
}

