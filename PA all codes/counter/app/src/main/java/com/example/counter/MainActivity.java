package com.example.counter;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView counterText;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the views
        counterText = findViewById(R.id.counterText);
        Button incrementButton = findViewById(R.id.incrementButton);
        Button decrementButton = findViewById(R.id.decrementButton);

        // Set click listeners for increment and decrement buttons
        incrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                counterText.setText(String.valueOf(counter));
            }
        });

        decrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(counter > 0){
                counter--;}
                else{
                    counter = 0;
                }
                counterText.setText(String.valueOf(counter));
            }
        });
    }
}
