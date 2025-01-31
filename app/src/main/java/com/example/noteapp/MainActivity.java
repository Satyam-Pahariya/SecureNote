package com.example.noteapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;  // RecyclerView to display notes
    private NoteAdapter noteAdapter;    // Adapter for RecyclerView
    private DatabaseHelper databaseHelper;  // Database helper

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  // Set the layout

        recyclerView = findViewById(R.id.recyclerView);  // Find RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));  // Set layout manager

        databaseHelper = new DatabaseHelper(this);  // Initialize database helper

        // FloatingActionButton to add new notes
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
            startActivity(intent);  // Open AddNoteActivity
        });

        loadNotes();  // Load notes from the database
    }

    // Method to load notes from the database
    private void loadNotes() {
        List<Note> notes = databaseHelper.getAllNotes();  // Get all notes
        noteAdapter = new NoteAdapter(notes);             // Create adapter
        recyclerView.setAdapter(noteAdapter);             // Set adapter to RecyclerView
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadNotes();  // Refresh the list of notes when the activity resumes
    }
}