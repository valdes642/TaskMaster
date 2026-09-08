package com.example.taskmaster.models;

public class Task {
    private String title;
    private String category;

    public Task(String title, String category) {
        this.title = title;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }
    public String getCategory() {
        return category;
    }
}
