package com.example.taskmaster.models;

public class Task {
    private String title;
    private String category;
    private String priority;
    private boolean isUrgent;

    public Task(String title, String category, String priority, boolean isUrgent) {
        this.title = title;
        this.category = category;
        this.priority = priority;
        this.isUrgent = isUrgent;
    }

    public String getTitle() { return title; }
    public String getCategory() { return category; }
    public String getPriority() { return priority; }
    public boolean isUrgent() { return isUrgent; }
}
