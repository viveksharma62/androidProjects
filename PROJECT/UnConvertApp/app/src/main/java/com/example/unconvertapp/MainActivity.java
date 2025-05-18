package com.example.unconvertapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
   private Button button;
   private TextView textView;
   private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editText);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                String s = editText.getText().toString();
                int kg = Integer.parseInt(s);
                double pound = 2.205 * kg;
                double unit = 3.60 * kg;
                textView.setText("Your electric bill price : ₹" + unit);
                Toast.makeText(MainActivity.this,"create by vivek sharma", Toast.LENGTH_SHORT).show();

            }
        });
    }
}