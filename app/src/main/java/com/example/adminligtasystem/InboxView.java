package com.example.adminligtasystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class InboxView extends AppCompatActivity {

    String  what, title;
    TextView whatText, titleText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox_view);


        whatText = findViewById(R.id.inboxWhatText);
        titleText = findViewById(R.id.inboxTitleText);

        Intent extraIntent = getIntent();
        what = extraIntent.getStringExtra("whatExtra");
        title = extraIntent.getStringExtra("titleExtra");

        whatText.setText(what);
        titleText.setText(title);
    }
}