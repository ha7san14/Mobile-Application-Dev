package com.example.mad_proj_bcsf20m525.Activities.form;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mad_proj_bcsf20m525.Adapter.MyAdapter;
import com.example.mad_proj_bcsf20m525.Model.Item;
import com.example.mad_proj_bcsf20m525.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class FormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        List<Item> items = new ArrayList<Item>();
        MyAdapter adapter;

        items.add(new Item("Hassan Ikram","hassanikram@gmail.com"));
        items.add(new Item("Ali Murad","alimurad@gmail.com"));
        items.add(new Item("Abdullah Afzal","abdullahafzal@gmail.com"));
        items.add(new Item("Nawab Safi","nawabsafi@gmail.com"));
        items.add(new Item("Ahsan Tahir","ahsantahir@gmail.com"));
        items.add(new Item("Nouman Yousaf","noumanyousuf@gmail.com"));
        items.add(new Item("Ahmad Siddiqui","ahmad@gmail.com"));
        items.add(new Item("Aksam Salik","aksamsalik@gmail.com"));
        items.add(new Item("Umair Javed","umairjaved@gmail.com"));
        items.add(new Item("Jahanzaib Hussain","jahanzaib@gmail.com"));


        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        FloatingActionButton fab = findViewById(R.id.fab);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdapter(getApplicationContext(),items);
        recyclerView.setAdapter(adapter);

        fab.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v){
               Dialog dialog = new Dialog(FormActivity.this);
               dialog.setContentView(R.layout.add_layout);

               EditText editTextName = dialog.findViewById(R.id.editTextName);
               EditText editTextEmail = dialog.findViewById(R.id.editTextEmail);
               Button saveButton = dialog.findViewById(R.id.saveButton);
               Button cancelButton = dialog.findViewById(R.id.cancelButton);

               saveButton.setOnClickListener(new View.OnClickListener(){
                   @Override
                   public void onClick(View v) {
                       String name = editTextName.getText().toString();
                       String email = editTextEmail.getText().toString();

                       if (name.isEmpty() || email.isEmpty()) {
                           Toast.makeText(FormActivity.this, "Please enter both name and email.", Toast.LENGTH_SHORT).show();
                       } else {
                           items.add(new Item(name, email));
                           adapter.notifyItemInserted(items.size() - 1);
                           recyclerView.scrollToPosition(items.size() - 1);
                           dialog.dismiss();
                       }
                   }
               });

               cancelButton.setOnClickListener(new View.OnClickListener(){
                   @Override
                   public void onClick(View v){
                       dialog.dismiss();
                   }
               });
               dialog.show();
           }
        });
}
}
