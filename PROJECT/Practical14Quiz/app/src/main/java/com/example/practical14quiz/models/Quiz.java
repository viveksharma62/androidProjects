package com.example.practical14quiz.models;

import java.util.HashMap;
import java.util.Map;

public class Quiz {

    final  String id;
    final String title;
    private Map<String, Question> questions = new HashMap<>();

    // Constructor
    public Quiz(String title, String id) {
        this.title = title;
        this.id = id;
    }

    // Getter for title
    public String getTitle() {
        return title;
    }

    // Getter for id
    public String getId() {
        return id;
    }

    // Getter for questions
    public Map<String, Question> getQuestions() {
        return questions;
    }

    // Setter for questions (optional, if needed)
    public void setQuestions(Map<String, Question> questions) {
        this.questions = questions;
    }

    // Method to add a question to the quiz (Usage of setQuestions)
    public void addQuestion(String questionId, Question question) {
        questions.put(questionId, question);
    }

    // Method to retrieve a question by ID (Usage of getQuestions)
    public Question getQuestion(String questionId) {
        return questions.get(questionId);
    }
}
