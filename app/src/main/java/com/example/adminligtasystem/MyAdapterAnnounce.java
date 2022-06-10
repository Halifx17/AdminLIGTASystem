package com.example.adminligtasystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapterAnnounce extends RecyclerView.Adapter<MyAdapterAnnounce.MyViewHolder> {

    Context context;
    ArrayList<Announcement> list;

    public MyAdapterAnnounce(Context context, ArrayList<Announcement> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.announcement_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Announcement announcement = list.get(position);
        holder.title.setText(announcement.getTitle());
        holder.when.setText(announcement.getWhen());
        holder.by.setText(announcement.getBy());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView when, title, by;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.titleTv);
            when = itemView.findViewById(R.id.whenTv);
            by = itemView.findViewById(R.id.byTv);

        }
    }
}
