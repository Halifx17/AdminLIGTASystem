package com.example.adminligtasystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<User> list;

    public MyAdapter(Context context, ArrayList<User> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.users_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        User user = list.get(position);
        holder.name.setText(user.getFirstname()+" "+user.getLastname());
        holder.email.setText(user.getEmail());
        holder.contact.setText(user.getPhoneNumber());
        holder.address.setText(user.getAddress());
        holder.birthdate.setText(user.getBirthdate());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name, email, address, contact, birthdate;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.nameTv);
            email = itemView.findViewById(R.id.emailTv);
            address = itemView.findViewById(R.id.addressTv);
            contact = itemView.findViewById(R.id.contactTv);
            birthdate = itemView.findViewById(R.id.birthdateTv);

        }
    }
}
