package com.example.ytcodeinad;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Button btnBack = findViewById(R.id.btnBack);

        // Receive data from the Intent
        Intent fromAct = getIntent();
        String title = fromAct.getStringExtra("title");
        String name = fromAct.getStringExtra("name");

        // Set the received data to TextView
        TextView txtStudentInfo = findViewById(R.id.textStudentInfo);
        txtStudentInfo.setText("Title: " + title + ", Name: " + name);

        // Set the ActionBar title if it's available
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }

        // Set up the Back button click listener
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate back to MainActivity
                Intent IBack = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(IBack);
            }
        });
    }
}
