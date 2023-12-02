package com.example.mad_proj_bcsf20m525.Activities.API;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceholderApi {
    @GET("quotes")
    Call<QuoteListResponse> getQuotes();
}
