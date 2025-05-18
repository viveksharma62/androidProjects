package com.example.practical14quiz.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.practical14quiz.R;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

     EditText edEmailAddress, edPassword, edCnfPassword;
     Button btnSignUp;
    private FirebaseAuth firebaseAuth;
     TextView btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Initialize FirebaseAuth
        firebaseAuth = FirebaseAuth.getInstance();

        // Bind views
        edEmailAddress = findViewById(R.id.edEmailAddress);
        edPassword = findViewById(R.id.edPassword);
        edCnfPassword = findViewById(R.id.edCnfPassword);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnLogin = findViewById(R.id.txtdesc);

        // Navigate to the login activity using a lambda expression
        btnLogin.setOnClickListener(view -> {
            Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });

        // Set click listener for the sign-up button using a lambda expression
        btnSignUp.setOnClickListener(v -> signUpUser());
    }

    private void signUpUser() {
        // Get user input
        String email = edEmailAddress.getText().toString().trim();
        String password = edPassword.getText().toString().trim();
        String confirmPassword = edCnfPassword.getText().toString().trim();

        // Validate input
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPassword)) {
            Toast.makeText(this, "Email and password can't be blank", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Password and confirm password do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create user with Firebase
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(SignUpActivity.this, "Sign-up Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        String errorMessage = task.getException() != null ? task.getException().getMessage() : "Unknown error";
                        Toast.makeText(SignUpActivity.this, "Error creating user: " + errorMessage, Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
