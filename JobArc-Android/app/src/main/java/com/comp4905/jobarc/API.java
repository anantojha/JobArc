package com.comp4905.jobarc;

import com.comp4905.jobarc.Models.JobPost;
import com.comp4905.jobarc.Models.Login;
import com.comp4905.jobarc.Models.ModelHome;
import com.comp4905.jobarc.Models.Profile;
import com.comp4905.jobarc.Models.RegistrationResponse;
import com.comp4905.jobarc.Models.ResumePost;
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
    Call<RegistrationResponse> createUser (
            @Body User user
    );

    @POST("users/login")
    Call<ResponseBody> checkUser (
            @Body Login user
    );

    @GET("users/dashboard")
    Call<ModelHome> getDashboard(@Query("id") long id);

    @POST("users/post_job")
    Call<ResponseBody> postJob (
            @Body JobPost jobPost
    );

    @POST("users/post_resume")
    Call<ResponseBody> postResume (
            @Body ResumePost resumePost
    );

    @GET("users/profile")
    Call<Profile> getProfile(@Query("id") long id);
}
