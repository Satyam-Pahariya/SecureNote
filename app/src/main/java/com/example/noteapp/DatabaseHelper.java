package com.example.noteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "notes.db";  // Database name
    private static final int DATABASE_VERSION = 1;          // Database version
    private static final String TABLE_NAME = "notes";       // Table name
    private static final String COLUMN_ID = "id";           // Column for ID
    private static final String COLUMN_TITLE = "title";     // Column for title
    private static final String COLUMN_CONTENT = "content"; // Column for content

    // Constructor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Called when the database is created for the first time
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_CONTENT + " TEXT)";
        db.execSQL(createTableQuery);  // Execute the SQL query to create the table
    }

    // Called when the database needs to be upgraded
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);  // Drop the old table
        onCreate(db);  // Create a new table
    }

    // Method to add a new note to the database
    public void addNote(String title, String content) {
        SQLiteDatabase db = this.getWritableDatabase();  // Get writable database
        ContentValues values = new ContentValues();      // Store values to insert
        values.put(COLUMN_TITLE, title);                // Add title
        values.put(COLUMN_CONTENT, content);            // Add content
        db.insert(TABLE_NAME, null, values);            // Insert into the table
        db.close();                                     // Close the database
    }

    // Method to retrieve all notes from the database
    public List<Note> getAllNotes() {
        List<Note> notes = new ArrayList<>();           // List to store notes
        SQLiteDatabase db = this.getReadableDatabase(); // Get readable database
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);  // Query to get all notes

        // Loop through the cursor to get all notes
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                String title = cursor.getString(cursor.getColumnIndex(COLUMN_TITLE));
                String content = cursor.getString(cursor.getColumnIndex(COLUMN_CONTENT));
                notes.add(new Note(id, title, content));  // Add note to the list
            } while (cursor.moveToNext());
        }

        cursor.close();  // Close the cursor
        db.close();      // Close the database
        return notes;    // Return the list of notes
    }
}