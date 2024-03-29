package com.example.mad_proj_bcsf20m525.Activities.form;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mad_proj_bcsf20m525.R;

public class MyViewHolder extends RecyclerView.ViewHolder {
    public TextView nameView;
    public TextView emailView;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        nameView = itemView.findViewById(R.id.name);
        emailView = itemView.findViewById(R.id.email);
    }
}
