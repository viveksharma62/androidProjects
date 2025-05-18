package com.example.ytcodeinad;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnNext = findViewById(R.id.btnNext);
        EditText val = findViewById(R.id.val);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create and set up Intent with the current value of EditText
                Intent inext = new Intent(MainActivity.this, SecondActivity.class);
                inext.putExtra("title", "sharma");
                inext.putExtra("name", val.getText().toString());  // Pass the text content of the EditText

                // Start SecondActivity with the Intent
                startActivity(inext);
            }
        });
    }
}
