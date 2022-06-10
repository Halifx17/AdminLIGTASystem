package com.example.adminligtasystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    EditText editEmail, editPassword;
    TextInputLayout editTextEmail, editTextPassword;
    Button logInButton, googleButton;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logInButton = findViewById(R.id.LogInButton);
        editPassword = findViewById(R.id.LogIn_Edit_Password);
        editTextPassword = (TextInputLayout) findViewById(R.id.EditText_Password);
        mAuth = FirebaseAuth.getInstance();


    }

    public void LogIn(View view) {

        String email, password;
        email = "Fred@gmail.com";
        password = editPassword.getText().toString().trim();

        if(password.isEmpty()){
            Toast.makeText(MainActivity.this, "Fields cannot be Empty", Toast.LENGTH_SHORT).show();
            editTextPassword.setError("Password is required");
        }

        else{
            editTextPassword.setError(null);


            mAuth.signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){

                                Toast.makeText(MainActivity.this, "Log In Successful", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                                startActivity(intent);

                            }else{
                                Toast.makeText(MainActivity.this, "Email or Password is incorrect", Toast.LENGTH_LONG).show();
                            }

                        }
                    });
        }

    }
}