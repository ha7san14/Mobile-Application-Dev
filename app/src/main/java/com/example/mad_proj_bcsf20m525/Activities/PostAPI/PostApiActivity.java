package com.example.mad_proj_bcsf20m525.Activities.PostAPI;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.mad_proj_bcsf20m525.R;
import com.example.mad_proj_bcsf20m525.databinding.ActivityPostApiBinding;  // Import ViewBinding

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostApiActivity extends AppCompatActivity {

    private ActivityPostApiBinding binding;  // Using ViewBinding

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPostApiBinding.inflate(getLayoutInflater());  // Initialize ViewBinding
        setContentView(binding.getRoot());  // Set the root of the inflated view as the content view

        binding.btnPostData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get Data From TextBox
                String strName = binding.txtName.getText().toString();
                String strJob = binding.txtJob.getText().toString();

                if (TextUtils.isEmpty(strName)) {
                    Toast.makeText(PostApiActivity.this, "Please Enter Name", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(strJob)) {
                    Toast.makeText(PostApiActivity.this, "Please Enter Job", Toast.LENGTH_SHORT).show();
                } else {
                    // Make the POST request using Retrofit
                    Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);
                    Call<Model> call = methods.getUserData(strName, strJob);

                    call.enqueue(new Callback<Model>() {
                        @Override
                        public void onResponse(Call<Model> call, Response<Model> response) {
                            if (response.isSuccessful()) {
                                Log.d("Response", "onResponse: " + response.body());
                                String strOutput = "Id : " + response.body().getId() + "\n";
                                strOutput += "Name : " + response.body().getName() + "\n";
                                strOutput += "Job : " + response.body().getJob() + "\n";
                                strOutput += "Created At : " + response.body().getCreatedAt();
                                binding.lblOutput.setText(strOutput);
                            } else {
                                Log.e("ResponseError", "onResponse: " + response.message());
                                Toast.makeText(PostApiActivity.this, "Unsuccessful response", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Model> call, Throwable t) {
                            Log.e("RequestError", "onFailure: " + t.getMessage());
                            Toast.makeText(PostApiActivity.this, "Error Occurred", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}
