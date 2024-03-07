package com.example.notes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {

    private ArrayList<String> notes;
    private DeleteNoteListener deleteNoteListener;

    public interface DeleteNoteListener {
        void deleteNote(String note);
    }

    public NotesAdapter(ArrayList<String> notes, MainActivity mainActivity) {
        this.notes = notes;
    }

    public void setDeleteNoteListener(DeleteNoteListener listener) {
        this.deleteNoteListener = listener;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        final String note = notes.get(position);
        holder.bind(note);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (deleteNoteListener != null) {
                    deleteNoteListener.deleteNote(note);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public static class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNote;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNote = itemView.findViewById(R.id.textViewNote);
        }

        public void bind(String note) {
            textViewNote.setText(note);
        }
    }
}

