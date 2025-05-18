package com.example.practical3sinup;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class myViewHolder extends RecyclerView.ViewHolder {
    ImageView imageview;
    TextView nameView , emailView;


    public myViewHolder(@NonNull View itemView) {
        super(itemView);
        imageview = imageview.findViewById(R.id.imageview);
        nameView = nameView.findViewById(R.id.name);
        emailView = emailView.findViewById(R.id.email);

    }
}
