package com.example.l7_recycler_view;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<ContactModel> arrcontacts = new ArrayList<>();
    RecycleContactAdapter adapter;
    RecyclerView recyclerView;
    FloatingActionButton btnOpenDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize RecyclerView and adapter
        recyclerView = findViewById(R.id.RecyclerContact);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize FloatingActionButton
        btnOpenDialog = findViewById(R.id.btnOpenDialog);

        // Initialize the adapter with the existing contact list
        adapter = new RecycleContactAdapter(this, arrcontacts);
        recyclerView.setAdapter(adapter);

        // Pre-populate contacts (you can update the list or make this dynamic later)
        arrcontacts.add(new ContactModel(R.drawable.a, "A", "1234567890"));
        arrcontacts.add(new ContactModel(R.drawable.b, "B", "1234567891"));
        arrcontacts.add(new ContactModel(R.drawable.c, "C", "1234567892"));
        arrcontacts.add(new ContactModel(R.drawable.d, "D", "1234567893"));
        arrcontacts.add(new ContactModel(R.drawable.e, "E", "1234567894"));
        arrcontacts.add(new ContactModel(R.drawable.f, "F", "1234567895"));
        arrcontacts.add(new ContactModel(R.drawable.a, "G", "1234567896"));
        arrcontacts.add(new ContactModel(R.drawable.b, "H", "1234567897"));
        arrcontacts.add(new ContactModel(R.drawable.c, "I", "1234567898"));
        arrcontacts.add(new ContactModel(R.drawable.d, "J", "1234567899"));
        arrcontacts.add(new ContactModel(R.drawable.e, "K", "1234567900"));
        arrcontacts.add(new ContactModel(R.drawable.f, "L", "1234567901"));
        arrcontacts.add(new ContactModel(R.drawable.a, "M", "1234567902"));
        arrcontacts.add(new ContactModel(R.drawable.b, "N", "1234567903"));
        arrcontacts.add(new ContactModel(R.drawable.c, "O", "1234567904"));
        arrcontacts.add(new ContactModel(R.drawable.d, "P", "1234567905"));
        arrcontacts.add(new ContactModel(R.drawable.e, "Q", "1234567906"));
        arrcontacts.add(new ContactModel(R.drawable.f, "L", "1234567907"));

        // Open dialog to add new contact when FAB is clicked
        btnOpenDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a dialog and set its layout
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.activity_add_update_lay);


                EditText edtName = dialog.findViewById(R.id.edtName);
                EditText edtNumber = dialog.findViewById(R.id.edtNumber);
                Button btnAction = dialog.findViewById(R.id.btnAction);


                btnAction.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name = edtName.getText().toString();
                        String number = edtNumber.getText().toString();

                        if (name.isEmpty()) {
                            Toast.makeText(MainActivity.this, "Please Enter Contact Name", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (number.isEmpty()) {
                            Toast.makeText(MainActivity.this, "Please Enter Contact Number", Toast.LENGTH_SHORT).show();
                            return;
                        }


                        arrcontacts.add(new ContactModel(R.drawable.a, name, number));


                        adapter.notifyItemInserted(arrcontacts.size() - 1);
                        recyclerView.scrollToPosition(arrcontacts.size() - 1);

                        dialog.dismiss();
                    }
                });

                // Show the dialog
                dialog.show();
            }
        });
    }
}
