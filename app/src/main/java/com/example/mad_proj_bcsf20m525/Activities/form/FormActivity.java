package com.example.mad_proj_bcsf20m525.Activities.form;

import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mad_proj_bcsf20m525.Activities.form.Item;
import com.example.mad_proj_bcsf20m525.Activities.form.MyAdapter;
import com.example.mad_proj_bcsf20m525.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class FormActivity extends AppCompatActivity {

    private static final String CHANNEL_ID = "my_channel";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        List<Item> items = new ArrayList<>();
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
        adapter = new MyAdapter(getApplicationContext(), items);
        recyclerView.setAdapter(adapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(FormActivity.this);
                dialog.setContentView(R.layout.add_layout);

                EditText editTextName = dialog.findViewById(R.id.editTextName);
                EditText editTextEmail = dialog.findViewById(R.id.editTextEmail);
                Button saveButton = dialog.findViewById(R.id.saveButton);
                Button cancelButton = dialog.findViewById(R.id.cancelButton);

                saveButton.setOnClickListener(new View.OnClickListener() {
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

                            // Add notification when a new item is added
                            showNotification("New Form Added", "Name: " + name + "\nEmail: " + email);

                            dialog.dismiss();
                        }
                    }
                });

                cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
    }

    private void showNotification(String title, String message) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Use ResourcesCompat to get the drawable
        Drawable drawable = getResources().getDrawable(R.drawable.logo, null);

        // Convert the drawable to a Bitmap for the large icon
        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
        Bitmap largeIcon = bitmapDrawable.getBitmap();

        // Check if the device is running Android 8.0 (API level 26) or above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create the notification channel if it doesn't exist
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "My Channel", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setLargeIcon(largeIcon)
                .setSmallIcon(R.drawable.logo)
                .setSubText(title)
                .setContentText(message)
                .build();

        // Generate a random ID for the notification
        int id = (int) (Math.random() * 10000);
        notificationManager.notify(id, notification);
    }
}
