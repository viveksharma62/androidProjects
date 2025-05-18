package com.example.practical14quiz.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.practical14quiz.R;
import com.google.firebase.auth.FirebaseAuth;

public class LoginIntro extends AppCompatActivity {

     FirebaseAuth auth;
     Button btnGetStarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_intro);

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance();

        // Check if the user is already logged in
        if (auth.getCurrentUser() != null) {
            Toast.makeText(this, "User is already logged in!", Toast.LENGTH_SHORT).show();
            redirect("MAIN");
        }

        // Initialize "Get Started" button
        btnGetStarted = findViewById(R.id.btnGeStarted);

        // Set click listener for the "Get Started" button
        btnGetStarted.setOnClickListener(v -> redirect("LOGIN"));
    }

    private void redirect(String name) {
        Intent intent;

        switch (name) {
            case "LOGIN":
                intent = new Intent(LoginIntro.this, LoginActivity.class);
                break;
            case "MAIN":
                intent = new Intent(LoginIntro.this, MainActivity.class);
                break;
            default:
                throw new IllegalArgumentException("No path exists for the specified destination");
        }

        startActivity(intent);
        finish();
    }
}
