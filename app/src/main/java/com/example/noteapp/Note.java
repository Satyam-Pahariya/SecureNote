package com.example.noteapp;

public class Note {
    private int id;          // Unique ID for the note
    private String title;    // Title of the note
    private String content;  // Content of the note

    // Constructor to initialize the note
    public Note(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    // Getters to access the fields
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}