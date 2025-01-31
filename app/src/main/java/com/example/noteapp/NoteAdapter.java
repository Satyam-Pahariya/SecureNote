package com.example.noteapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {
    private List<Note> notes;  // List of notes

    // Constructor
    public NoteAdapter(List<Note> notes) {
        this.notes = notes;
    }

    // Create a ViewHolder for each note item
    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);
        return new NoteViewHolder(view);
    }

    // Bind data to the ViewHolder
    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = notes.get(position);
        holder.titleTextView.setText(note.getTitle());      // Set title
        holder.contentTextView.setText(note.getContent());  // Set content
    }

    // Return the number of notes
    @Override
    public int getItemCount() {
        return notes.size();
    }

    // ViewHolder class to hold the views for each note item
    static class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView, contentTextView;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);    // Find title TextView
            contentTextView = itemView.findViewById(R.id.contentTextView); // Find content TextView
        }
    }
}