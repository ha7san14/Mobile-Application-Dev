package com.example.mad_proj_bcsf20m525.Activities.notes;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mad_proj_bcsf20m525.R;
import com.example.mad_proj_bcsf20m525.databinding.ActivityNotesBinding;
import com.example.mad_proj_bcsf20m525.databinding.DialogAddNoteBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class NotesActivity extends AppCompatActivity {

    private NotesViewModel notesViewModel;
    private ActivityNotesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize ViewBinding
        binding = ActivityNotesBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // Initialize RecyclerView for displaying notes
        RecyclerView recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        // Initialize the adapter for the RecyclerView
        final NotesAdapter adapter = new NotesAdapter();
        recyclerView.setAdapter(adapter);

        // Initialize the ViewModel using ViewModelProvider
        notesViewModel = new ViewModelProvider(this).get(NotesViewModel.class);

        // Observe changes in the list of notes and update the RecyclerView
        notesViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                adapter.setNotes(notes);
            }
        });

        // Set item click listeners for edit and delete actions in the RecyclerView
        adapter.setOnItemClickListener(new NotesAdapter.OnItemClickListener() {
            @Override
            public void onEditClick(Note note) {
                showEditNoteDialog(note);
            }

            @Override
            public void onDeleteClick(Note note) {
                showDeleteNoteDialog(note);
            }
        });

        // Initialize FloatingActionButton for adding new notes
        FloatingActionButton fab = binding.fab;
        fab.setOnClickListener(v -> showAddNoteDialog());
    }

    // Display a dialog for adding a new note
    private void showAddNoteDialog() {
        DialogAddNoteBinding dialogBinding = DialogAddNoteBinding.inflate(getLayoutInflater());
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setView(dialogBinding.getRoot());

        final AlertDialog alertDialog = dialogBuilder.create();

        // Define actions for the "Add" and "Cancel" buttons in the dialog
        dialogBinding.buttonAdd.setOnClickListener(v -> {
            String title = dialogBinding.editTextTitle.getText().toString().trim();
            String description = dialogBinding.editTextDescription.getText().toString().trim();

            // Check if both title and description are non-empty
            if (!title.isEmpty() && !description.isEmpty()) {
                // Insert the new note into the database using the ViewModel
                notesViewModel.insert(new Note(title, description));
                alertDialog.dismiss(); // Dismiss the dialog
            } else {
                // Show a toast message if either title or description is empty
                Toast.makeText(NotesActivity.this, "Please enter both title and description", Toast.LENGTH_SHORT).show();
            }
        });

        // Dismiss the dialog if the "Cancel" button is clicked
        dialogBinding.buttonCancel.setOnClickListener(v -> alertDialog.dismiss());

        // Show the dialog
        alertDialog.show();
    }

    // Display a dialog for editing an existing note
    private void showEditNoteDialog(final Note note) {
        DialogAddNoteBinding dialogBinding = DialogAddNoteBinding.inflate(getLayoutInflater());
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setView(dialogBinding.getRoot());

        // Pre-fill the dialog fields with the current values of the selected note
        dialogBinding.editTextTitle.setText(note.getTitle());
        dialogBinding.editTextDescription.setText(note.getDescription());

        final AlertDialog alertDialog = dialogBuilder.create();

        // Define actions for the "Add" and "Cancel" buttons in the dialog
        dialogBinding.buttonAdd.setOnClickListener(v -> {
            String title = dialogBinding.editTextTitle.getText().toString().trim();
            String description = dialogBinding.editTextDescription.getText().toString().trim();

            // Check if both title and description are non-empty
            if (!title.isEmpty() && !description.isEmpty()) {
                // Update the note in the database using the ViewModel
                note.setTitle(title);
                note.setDescription(description);
                notesViewModel.update(note);
                alertDialog.dismiss(); // Dismiss the dialog
            } else {
                // Show a toast message if either title or description is empty
                Toast.makeText(NotesActivity.this, "Please enter both title and description", Toast.LENGTH_SHORT).show();
            }
        });

        // Dismiss the dialog if the "Cancel" button is clicked
        dialogBinding.buttonCancel.setOnClickListener(v -> alertDialog.dismiss());

        // Show the dialog
        alertDialog.show();
    }

    // Display a confirmation dialog for deleting a note
    private void showDeleteNoteDialog(final Note note) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete Note");
        builder.setMessage("Are you sure you want to delete this note?");

        // Define actions for the "Yes" and "No" buttons in the confirmation dialog
        builder.setPositiveButton("Yes", (dialog, which) -> {
            // Delete the note from the database using the ViewModel
            notesViewModel.delete(note);
            Toast.makeText(NotesActivity.this, "Note deleted", Toast.LENGTH_SHORT).show();
        });

        builder.setNegativeButton("No", (dialog, which) -> dialog.dismiss());

        // Show the confirmation dialog
        builder.show();
    }
}
