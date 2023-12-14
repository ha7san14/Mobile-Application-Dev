package com.example.mad_proj_bcsf20m525.Activities.PostAPI;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Methods {

    @FormUrlEncoded
    @POST("/api/users")
    Call<Model> getUserData(@Field("name")String name,@Field("job") String job);
}