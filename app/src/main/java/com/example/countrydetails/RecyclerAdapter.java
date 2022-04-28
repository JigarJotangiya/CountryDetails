package com.example.countrydetails;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    Activity activity;
   public static List<CountryDatum> modellist;



    public RecyclerAdapter(MainActivity mainActivity, List<CountryDatum> modelList) {

        activity = mainActivity;
                modellist=modelList;
    }

    public RecyclerAdapter(Activity activity, List<CountryDatum> detalist2) {
    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.name.setText(modellist.get(position).getName());
        Glide.with(activity).load(modellist.get(position).getFlags().getPng()).into(holder.flags);

        holder.country1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(activity,Country_detail_list.class);
                intent.putExtra("pos",position);
                activity.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return modellist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        ImageView flags;
        LinearLayout country1;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            country1 = itemView.findViewById(R.id.country1);
            name = itemView.findViewById(R.id.name);
            flags = itemView.findViewById(R.id.flags);

        }
    }
}
