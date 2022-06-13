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

import java.util.ArrayList;

public class MyAdapterInbox extends RecyclerView.Adapter<MyAdapterInbox.MyViewHolder> {

    Context context;
    ArrayList<InboxClass> list;

    public MyAdapterInbox(Context context, ArrayList<InboxClass> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.inbox_layout,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        InboxClass inboxClass = list.get(position);
        holder.title.setText(inboxClass.getTitle());
        holder.user.setText(inboxClass.getUser());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,InboxView.class);
                intent.putExtra("titleExtra",inboxClass.getTitle());
                intent.putExtra("whatExtra",inboxClass.getContent());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView title, content, user;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.inboxTitleTv);
            user = itemView.findViewById(R.id.inboxUserTv);
            cardView = itemView.findViewById(R.id.inboxCard);
        }
    }
}
