package com.example.practicaledit;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private TextView textView;
    private Button buttonAdd, buttonRead, buttonEdit, buttonDelete;
    private static final String FILE_NAME = "myfile.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonRead = findViewById(R.id.buttonRead);
        buttonEdit = findViewById(R.id.buttonEdit);
        buttonDelete = findViewById(R.id.buttonDelete);

        // Add button functionality
        buttonAdd.setOnClickListener(v -> {
            String textToSave = editText.getText().toString();
            if (!textToSave.isEmpty()) {
                saveToFile(textToSave);
                editText.setText("");
                showMessage("Text added successfully!");
            }
        });

        // Read button functionality
        buttonRead.setOnClickListener(v -> {
            String fileContent = readFromFile();
            if (!fileContent.isEmpty()) {
                toggleView(false); // Show TextView, hide EditText
                textView.setText(fileContent);
            }
        });

        // Edit button functionality
        buttonEdit.setOnClickListener(v -> {
            String fileContent = readFromFile();
            if (!fileContent.isEmpty()) {
                toggleView(true); // Show EditText, hide TextView
                editText.setText(fileContent);
            }
        });

        // Delete button functionality
        buttonDelete.setOnClickListener(v -> {
            deleteFile(FILE_NAME);
            editText.setText("");
            textView.setText("");
            showMessage("File deleted successfully!");
        });
    }

    // Method to save data to file
    private void saveToFile(String data) {
        try (FileOutputStream fos = openFileOutput(FILE_NAME, MODE_PRIVATE)) {
            fos.write(data.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to read data from file
    private String readFromFile() {
        try (FileInputStream fis = openFileInput(FILE_NAME)) {
            int c;
            StringBuilder temp = new StringBuilder();
            while ((c = fis.read()) != -1) {
                temp.append((char) c);
            }
            return temp.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    // Method to toggle between EditText and TextView
    private void toggleView(boolean showEditText) {
        if (showEditText) {
            editText.setVisibility(View.VISIBLE);
            textView.setVisibility(View.GONE);
        } else {
            editText.setVisibility(View.GONE);
            textView.setVisibility(View.VISIBLE);
        }
    }

    // Method to show message (Toast)
    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
