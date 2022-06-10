package com.example.adminligtasystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Announcements extends AppCompatActivity {

    RecyclerView recyclerView;
    MyAdapterAnnounce myAdapterAnnounce;
    DatabaseReference dbReference;
    ArrayList<Announcement> list;
    Button btnAddAnnouncements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcements);

        btnAddAnnouncements = findViewById(R.id.btnAddAnnouncements);

        recyclerView = findViewById(R.id.recyclerAnnounce);
        dbReference = FirebaseDatabase.getInstance().getReference("Announcements");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        myAdapterAnnounce = new MyAdapterAnnounce(this,list);
        recyclerView.setAdapter(myAdapterAnnounce);

        dbReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Announcement announcement = dataSnapshot.getValue(Announcement.class);
                    list.add(announcement);
                }

                myAdapterAnnounce.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnAddAnnouncements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Announcements.this,Add_Announcements.class);
                startActivity(intent);
            }
        });




    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}