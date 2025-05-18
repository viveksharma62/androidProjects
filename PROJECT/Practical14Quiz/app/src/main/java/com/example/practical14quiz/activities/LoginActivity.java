package com.example.practical14quiz.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.practical14quiz.R;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private EditText etEmailAddress, etPassword;
     Button btnLogin;
     TextView btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize Firebase Auth
        firebaseAuth = FirebaseAuth.getInstance();

        // Initialize UI components
        etEmailAddress = findViewById(R.id.etEmailAddress);
        etPassword = findViewById(R.id.edPassword); // Fixed ID from edPassword to etPassword
        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);

        // Set click listener for "Sign Up" button using lambda
        btnSignUp.setOnClickListener(view -> {
            // Navigate to the Sign Up Activity
            Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(intent);
            finish();
        });

        // Set click listener for "Login" button using lambda
        btnLogin.setOnClickListener(v -> login());
    }

    private void login() {
        String email = etEmailAddress.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        // Validate inputs
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Email/Password cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        // Authenticate with Firebase
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Successful login
                        Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        // Login failed
                        String errorMessage = task.getException() != null ? task.getException().getMessage() : "Unknown Error";
                        Toast.makeText(this, "Authentication Failed: " + errorMessage, Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
