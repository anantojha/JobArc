package com.comp4905.jobarc;

import com.comp4905.jobarc.Models.ModelHome;
import com.comp4905.jobarc.Models.User;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {
    @POST("users/register")
    Call<ResponseBody> createUser (
            @Body User user
    );

    @POST("users/login")
    Call<ResponseBody> checkUser (
            @Body User user
    );

    @GET("users/dashboard")
    Call<ModelHome> getDashboard(@Query("id") long id);
}
