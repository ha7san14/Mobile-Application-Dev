package com.example.mad_proj_bcsf20m525.Activities.notes;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mad_proj_bcsf20m525.Model.Note.Note;
import com.example.mad_proj_bcsf20m525.Model.Note.NoteDao;
import com.example.mad_proj_bcsf20m525.Model.Note.NoteDatabase;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NotesViewModel extends AndroidViewModel {

    private final NoteDao noteDao;
    private final LiveData<List<Note>> allNotes;
    private final ExecutorService executorService;

    public NotesViewModel(Application application) {
        super(application);
        NoteDatabase database = NoteDatabase.getInstance(application);
        noteDao = database.noteDao();
        allNotes = noteDao.getAllNotes();
        executorService = Executors.newSingleThreadExecutor();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

    public void insert(Note note) {
        executorService.execute(() -> {
            noteDao.insert(note);
        });
    }

    public void update(Note note) {
        executorService.execute(() -> {
            noteDao.update(note);
        });
    }

    public void delete(Note note) {
        executorService.execute(() -> {
            noteDao.delete(note);
        });
    }
}