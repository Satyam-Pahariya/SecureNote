package com.example.noteapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddNoteActivity extends AppCompatActivity {
    private EditText titleEditText, contentEditText;  // Input fields
    private Button saveButton;                       // Save button
    private DatabaseHelper databaseHelper;           // Database helper

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);  // Set the layout

        titleEditText = findViewById(R.id.titleEditText);    // Find title EditText
        contentEditText = findViewById(R.id.contentEditText); // Find content EditText
        saveButton = findViewById(R.id.saveButton);          // Find save button
        databaseHelper = new DatabaseHelper(this);           // Initialize database helper

        // Save button click listener
        saveButton.setOnClickListener(view -> {
            String title = titleEditText.getText().toString();    // Get title
            String content = contentEditText.getText().toString(); // Get content
            databaseHelper.addNote(title, content);              // Add note to the database
            finish();  // Close the activity
        });
    }
}