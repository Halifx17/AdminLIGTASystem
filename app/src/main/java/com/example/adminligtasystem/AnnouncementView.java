package com.example.adminligtasystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class AnnouncementView extends AppCompatActivity {

    String who, what, when, by, title;
    TextView whoText, whatText, byText, titleText, whenText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement_view);

        whoText = findViewById(R.id.whoText);
        whatText = findViewById(R.id.whatText);
        whenText = findViewById(R.id.whenText);
        byText = findViewById(R.id.byText);
        titleText = findViewById(R.id.titleText);

        Intent extraIntent = getIntent();
        who = extraIntent.getStringExtra("whoExtra");
        what = extraIntent.getStringExtra("whatExtra");
        when = extraIntent.getStringExtra("whenExtra");
        by = extraIntent.getStringExtra("byExtra");
        title = extraIntent.getStringExtra("titleExtra");

        whoText.setText(who);
        whatText.setText(what);
        whenText.setText(when);
        byText.setText(by);
        titleText.setText(title);

    }
}