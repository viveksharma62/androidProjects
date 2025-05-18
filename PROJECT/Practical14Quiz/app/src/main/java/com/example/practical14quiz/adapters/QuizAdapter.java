package com.example.practical14quiz.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practical14quiz.R;
import com.example.practical14quiz.models.Quiz;
import com.example.practical14quiz.utils.ColorPicker;
import com.example.practical14quiz.utils.IconPicker;

import java.util.List;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.QuizViewHolder> {

    private final Context context;
    private final List<Quiz> quizzes;

    public QuizAdapter(@NonNull Context context, @NonNull List<Quiz> quizzes) {
        this.context = context;
        this.quizzes = quizzes;
    }

    @NonNull
    @Override
    public QuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.quiz_item, parent, false);
        return new QuizViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.textViewTitle.setText(quizzes.get(position).getTitle());
        holder.cardContainer.setCardBackgroundColor(Color.parseColor(ColorPicker.getColor()));
        holder.iconView.setImageResource(IconPicker.getIcon());

        // Use lambda instead of anonymous class
        holder.itemView.setOnClickListener(view ->
                Toast.makeText(context, quizzes.get(position).getTitle(), Toast.LENGTH_SHORT).show()
        );
    }

    @Override
    public int getItemCount() {
        return quizzes.size();
    }

    public static class QuizViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle;
        ImageView iconView;
        CardView cardContainer;

        public QuizViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.quizTitle);
            iconView = itemView.findViewById(R.id.quizIcon);
            cardContainer = itemView.findViewById(R.id.cardContainer);
        }
    }
}
