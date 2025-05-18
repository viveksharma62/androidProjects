package com.example.practical3sinup;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class recycleVIew extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recycle_view);


        RecyclerView recyclerView = findViewById(R.id.recycleView);

        List<item> items = new ArrayList<>();
        items.add(new item("Vivek Sharma", "viveksharma@28402@gmail.com", R.drawable.a));
        items.add(new item("Ansh Sharma", "anshsharma@28402@gmail.com", R.drawable.b));
        items.add(new item("Suraj Kumar", "surajthakur@28402@gmail.com", R.drawable.c));
        items.add(new item("Saurabh Singh", "saurabhsingh@28402@gmail.com", R.drawable.d));
        items.add(new item("Ashish Kumar", "ashishkumar@28402@gmail.com", R.drawable.e));
        items.add(new item("Ambuj Singh", "ambujsingh@28402@gmail.com", R.drawable.f));
        items.add(new item("Rohan Sharma", "rohansharma@28402@gmail.com", R.drawable.g));
        items.add(new item("Subham Kumar", "subhamkumar@28402@gmail.com", R.drawable.h));
        items.add(new item("Aditya Singh", "adityasingh@28402@gmail.com", R.drawable.a));
        items.add(new item("Bipul Kumar", "bipulkumar@28402@gmail.com", R.drawable.b));

        // Set layout manager and adapter
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(getApplicationContext(), items));
    }
}

