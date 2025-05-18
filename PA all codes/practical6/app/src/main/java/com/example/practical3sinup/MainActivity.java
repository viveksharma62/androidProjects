package com.example.practical3sinup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button sigin_btn, button2;
    EditText email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        sigin_btn = findViewById(R.id.btnSignIn);
        button2 = findViewById(R.id.button2);

        sigin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(email.getText().toString().equals("viveksharma28402@gmail.com") && password.getText().toString().equals("vivek1234")){
                    Intent intent = new Intent(MainActivity.this,recycleVIew.class);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "Successfully logged in", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
