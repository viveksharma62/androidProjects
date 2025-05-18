package com.example.practical14quiz.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practical14quiz.R;
import com.example.practical14quiz.adapters.QuizAdapter;
import com.example.practical14quiz.models.Quiz;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActionBarDrawerToggle actionBarDrawerToggle;
    DrawerLayout mainDrawer;
    QuizAdapter adapter;
    final List<Quiz> quizList = new ArrayList<>();
    RecyclerView quizRecyclerView;
    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("MainActivity", "onCreate: Starting initialization");
        try {
            initializeViews();
            populateDummyData();
            setUpFireStore();
        } catch (Exception e) {
            Log.e("MainActivity", "Initialization error: " + e.getMessage(), e);
            Toast.makeText(this, "Something went wrong. Please try again later.", Toast.LENGTH_SHORT).show();
        }
    }

    private void initializeViews() {
        try {
            // Set up Toolbar
            Toolbar appBar = findViewById(R.id.appBar);
            setSupportActionBar(appBar);

            // Set up Drawer Layout
            mainDrawer = findViewById(R.id.mainDrawer);
            actionBarDrawerToggle = new ActionBarDrawerToggle(
                    this,
                    mainDrawer,
                    appBar,
                    R.string.app_name,
                    R.string.app_name
            );
            mainDrawer.addDrawerListener(actionBarDrawerToggle);
            actionBarDrawerToggle.syncState();

            // Set up RecyclerView
            quizRecyclerView = findViewById(R.id.quizRecyclerView);
            quizRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
            adapter = new QuizAdapter(this, quizList);
            quizRecyclerView.setAdapter(adapter);

            Log.d("MainActivity", "initializeViews: Views initialized successfully");

        } catch (Exception e) {
            Log.e("initializeViews", "Error during initialization: " + e.getMessage(), e);
            throw e;
        }
    }

    private void populateDummyData() {
        Log.d("MainActivity", "populateDummyData: Adding dummy data");
        quizList.clear();
        quizList.add(new Quiz("12-05-2024", "06-06-2024"));
        quizList.add(new Quiz("12-06-2024", "12-07-2024"));
        quizList.add(new Quiz("12-07-2024", "12-08-2024"));
        quizList.add(new Quiz("12-08-2024", "12-09-2024"));
        quizList.add(new Quiz("12-09-2024", "12-10-2024"));
        quizList.add(new Quiz("12-10-2024", "12-11-2024"));
        adapter.notifyItemRangeInserted(0, quizList.size());
    }

    private void setUpFireStore() {
        firestore = FirebaseFirestore.getInstance();
        CollectionReference collectionReference = firestore.collection("quizzes");

        collectionReference.addSnapshotListener((value, error) -> {
            if (value == null || error != null) {
                Toast.makeText(MainActivity.this, "Error fetching data", Toast.LENGTH_SHORT).show();
                return;
            }

            for (DocumentChange documentChange : value.getDocumentChanges()) {
                switch (documentChange.getType()) {
                    case ADDED:
                        Quiz addedQuiz = documentChange.getDocument().toObject(Quiz.class);
                        quizList.add(addedQuiz);
                        adapter.notifyItemInserted(quizList.size() - 1);
                        break;

                    case MODIFIED:
                        Quiz modifiedQuiz = documentChange.getDocument().toObject(Quiz.class);
                        int modifiedIndex = getQuizIndex(documentChange.getDocument().getId());
                        if (modifiedIndex != -1) {
                            quizList.set(modifiedIndex, modifiedQuiz);
                            adapter.notifyItemChanged(modifiedIndex);
                        }
                        break;

                    case REMOVED:
                        int removedIndex = getQuizIndex(documentChange.getDocument().getId());
                        if (removedIndex != -1) {
                            quizList.remove(removedIndex);
                            adapter.notifyItemRemoved(removedIndex);
                        }
                        break;
                }
            }
        });
    }

    private int getQuizIndex(String id) {
        for (int i = 0; i < quizList.size(); i++) {
            if (quizList.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
