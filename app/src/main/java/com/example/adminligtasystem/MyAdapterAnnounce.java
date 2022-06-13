package com.example.adminligtasystem;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MyAdapterAnnounce extends RecyclerView.Adapter<MyAdapterAnnounce.MyViewHolder> {

    Context context;
    ArrayList<Announcement> list;
    DatabaseReference referenceId;


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
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,AnnouncementView.class);
                intent.putExtra("titleExtra",announcement.getTitle());
                intent.putExtra("whoExtra",announcement.getWho());
                intent.putExtra("whatExtra",announcement.getWhat());
                intent.putExtra("whenExtra",announcement.getWhen());
                intent.putExtra("byExtra",announcement.getBy());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView when, title, by;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.titleTv);
            when = itemView.findViewById(R.id.whenTv);
            by = itemView.findViewById(R.id.byTv);
            cardView = itemView.findViewById(R.id.announcementCard);

        }
    }
}
