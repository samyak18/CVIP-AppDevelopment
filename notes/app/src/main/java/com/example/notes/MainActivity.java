package com.example.notes;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText editTextNote;
    private TextView textViewNote;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNote = findViewById(R.id.editTextNote);
        textViewNote = findViewById(R.id.textViewNote);
        Button buttonSave = findViewById(R.id.buttonSave);
        Button buttonShare = findViewById(R.id.buttonShare);

        sharedPreferences = getSharedPreferences("notes", Context.MODE_PRIVATE);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String note = editTextNote.getText().toString();
                saveNoteToSharedPreferences(note);
            }
        });

        buttonShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String note = textViewNote.getText().toString();
                shareNote(note);
            }
        });

        loadNoteFromSharedPreferences();
    }

    private void saveNoteToSharedPreferences(String note) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("note", note);
        editor.apply();
        textViewNote.setText(note);
    }

    private void loadNoteFromSharedPreferences() {
        String note = sharedPreferences.getString("note", "");
        textViewNote.setText(note);
    }

    private void shareNote(String note) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, note);
        startActivity(Intent.createChooser(intent, "Share Note"));
    }
}
