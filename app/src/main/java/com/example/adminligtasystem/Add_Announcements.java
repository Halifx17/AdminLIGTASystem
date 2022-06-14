package com.example.adminligtasystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class Add_Announcements extends AppCompatActivity {

    DatePickerDialog datePickerDialog;
    TextInputLayout editTextTitle, editTextDate, editTextAbout;
    EditText  editTitle, editDate, editAbout;
    FirebaseAuth mAuth;
    DatabaseReference referenceId;
    long maxId = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_announcements);

        initDatePicker();
        editTextTitle = findViewById(R.id.EditText_title);
        editTextDate = findViewById(R.id.EditText_dateTxt);
        editTextAbout = findViewById(R.id.EditText_aboutTxt);


        editTitle = findViewById(R.id.Edit_title);
        editDate = findViewById(R.id.Edit_dateTxt);
        editAbout = findViewById(R.id.Edit_aboutTxt);


        referenceId = FirebaseDatabase.getInstance().getReference("Announcements");

        referenceId.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    maxId = (snapshot.getChildrenCount());
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                month = month + 1;
                String date = makeDateString(day, month, year);
                editDate.setText(date);

            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this,style,dateSetListener,year,month,day);

    }

    private String makeDateString(int day, int month, int year) {

        return getMonthFormat(month)+" "+day+", "+year;
    }

    private String getMonthFormat(int month) {

        if (month == 1)
            return "JAN";
        if (month == 2)
            return "FEB";
        if (month == 3)
            return "MAR";
        if (month == 4)
            return "APR";
        if (month == 5)
            return "MAY";
        if (month == 6)
            return "JUNE";
        if (month == 7)
            return "JULY";
        if (month == 8)
            return "AUG";
        if (month == 9)
            return "SEP";
        if (month == 10)
            return "OCT";
        if (month == 11)
            return "NOV";
        if (month == 12)
            return "DEC";

        return "JAN";

    }

    public void openDatePicker(View view) {

        datePickerDialog.show();

    }

    public void announceFirebase(View view) {

        String title, date, about;

        title = editTitle.getText().toString().trim();
        date = editDate.getText().toString().trim();
        about = editAbout.getText().toString().trim();



        if (title.isEmpty() && date.isEmpty() && about.isEmpty()) {
            Toast.makeText(Add_Announcements.this, "Fields are Required", Toast.LENGTH_LONG).show();
            editTextTitle.setError("Field is Required");
            editTextDate.setError("Field is Required");
            editTextAbout.setError("Field is Required");

        } else if (title.isEmpty()) {
            Toast.makeText(Add_Announcements.this, "Field is Required", Toast.LENGTH_LONG).show();
            editTextTitle.setError("Field is Required");
            editTextDate.setError(null);
            editTextAbout.setError(null);


        } else if (date.isEmpty()) {
            Toast.makeText(Add_Announcements.this, "Field is Required", Toast.LENGTH_LONG).show();
            editTextTitle.setError(null);
            editTextDate.setError("Field is Required");
            editTextAbout.setError(null);

        } else if (about.isEmpty()) {
            Toast.makeText(Add_Announcements.this, "Field is Required", Toast.LENGTH_LONG).show();
            editTextTitle.setError(null);
            editTextDate.setError(null);
            editTextAbout.setError("Field is Required");

        } else {
                Announcement announcement = new Announcement(title,date,about);




            referenceId.push().setValue(announcement).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        if (task.isSuccessful()) {
                                            openDialog();
                                        } else {
                                            Toast.makeText(Add_Announcements.this, "Failed Registration", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                    }

        }
    public void openDialog(){

        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);
        builder.setTitle("Success");
        builder.setMessage("Announcement Posted");
        builder.setPositiveButton("Return", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Intent intent = new Intent(getApplicationContext(),Announcements.class);
                finish();
                startActivity(intent);

            }
        });

        builder.show();
    }
}