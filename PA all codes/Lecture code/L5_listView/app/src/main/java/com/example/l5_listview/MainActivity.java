package com.example.l5_listview;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    Spinner spinner;
    AutoCompleteTextView actxtView;
    ArrayList<String> ararNames = new ArrayList<>();
    ArrayList<String> arrIds = new ArrayList<>();
    ArrayList<String> arrLanguage = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        spinner = findViewById(R.id.spinner);
        actxtView = findViewById(R.id.actxtView);

        // ListView setup
        ararNames.add("vivek");
        ararNames.add("kumar");
        ararNames.add("sharma");
        ararNames.add("ansh");
        ararNames.add("kumar");
        ararNames.add("sharma");
        ararNames.add("suraj");
        ararNames.add("kumar");
        ararNames.add("thakur");
        ararNames.add("saurav");
        ararNames.add("kumar");
        ararNames.add("singh");
        ararNames.add("aditya");
        ararNames.add("kumar");
        ararNames.add("singh");

        ArrayAdapter<String> listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ararNames);
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if (position == 0) {
                    Toast.makeText(MainActivity.this, "Clicked first item", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Spinner setup
        arrIds.add("Aadhar card");
        arrIds.add("Pan card");
        arrIds.add("Voter id card");
        arrIds.add("Driving licence card");
        arrIds.add("Ration card");
        arrIds.add("Xth Score card");
        arrIds.add("XIIth Score card");

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, arrIds);
        spinner.setAdapter(spinnerAdapter);

        // AutoCompleteTextView setup
        arrLanguage.add("C");
        arrLanguage.add("JAVA");
        arrLanguage.add("C++");
        arrLanguage.add("PHP");
        arrLanguage.add("Objective C");
        arrLanguage.add("C#");
        arrLanguage.add("JavaScript");

        ArrayAdapter<String> actAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrLanguage);
        actxtView.setAdapter(actAdapter);
        actxtView.setThreshold(1);
    }
}
