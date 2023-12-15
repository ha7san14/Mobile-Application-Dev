package com.example.mad_proj_bcsf20m525.Activities.API;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

import com.example.mad_proj_bcsf20m525.databinding.ActivityApiBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiActivity extends AppCompatActivity {

    private JsonPlaceholderApi jsonPlaceholderApi;
    private ListView listView;

    private ActivityApiBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize ViewBinding
        binding = ActivityApiBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Replace findViewById with ViewBinding
        listView = binding.listView;

        // Create Retrofit instance
        jsonPlaceholderApi = RetrofitClient.getRetrofitInstance().create(JsonPlaceholderApi.class);

        // Make API call to get quotes
        Call<QuoteListResponse> call = jsonPlaceholderApi.getQuotes();
        call.enqueue(new Callback<QuoteListResponse>() {
            @Override
            public void onResponse(Call<QuoteListResponse> call, Response<QuoteListResponse> response) {
                if (response.isSuccessful()) {
                    QuoteListResponse quoteListResponse = response.body();

                    // Display quote data in ListView
                    if (quoteListResponse != null) {
                        displayQuotes(quoteListResponse.getQuotes());
                    }
                } else {
                    // Handle error
                    Log.e("API Error", "Response not successful. Code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<QuoteListResponse> call, Throwable t) {
                // Handle failure
                Log.e("API Error", "Failed to fetch quotes: " + t.getMessage());
            }
        });
    }

    private void displayQuotes(List<Quote> quotes) {
        // Create a custom adapter to populate the ListView
        ApiAdapter adapter = new ApiAdapter(this, quotes);

        // Set the adapter to the ListView
        listView.setAdapter(adapter);
    }
}
