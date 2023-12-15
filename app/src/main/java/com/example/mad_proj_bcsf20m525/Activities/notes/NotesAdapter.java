package com.example.mad_proj_bcsf20m525.Activities.notes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mad_proj_bcsf20m525.R;
import com.example.mad_proj_bcsf20m525.databinding.NotesItemViewBinding;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteHolder> {

    private List<Note> notes = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // ViewBinding to inflate the layout
        NotesItemViewBinding binding = NotesItemViewBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new NoteHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        Note currentNote = notes.get(position);

        // Use ViewBinding to access views
        holder.binding.textViewTitle.setText(currentNote.getTitle());
        holder.binding.textViewDescription.setText(currentNote.getDescription());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    class NoteHolder extends RecyclerView.ViewHolder {
        // Use ViewBinding for easy access to views
        private final NotesItemViewBinding binding;

        public NoteHolder(@NonNull NotesItemViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            // Set click listeners for edit and delete icons
            binding.imageViewEdit.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onEditClick(notes.get(position));
                }
            });

            binding.imageViewDelete.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onDeleteClick(notes.get(position));
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onEditClick(Note note);

        void onDeleteClick(Note note);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
