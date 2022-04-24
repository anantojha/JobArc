package com.comp4905.jobarc;

import com.comp4905.jobarc.Models.ApplicantsModel;
import com.comp4905.jobarc.Models.JobApplicationRequest;
import com.comp4905.jobarc.Models.JobPost;
import com.comp4905.jobarc.Models.Login;
import com.comp4905.jobarc.Models.ModelHome;
import com.comp4905.jobarc.Models.Profile;
import com.comp4905.jobarc.Models.RegistrationResponse;
import com.comp4905.jobarc.Models.Resume;
import com.comp4905.jobarc.Models.ResumePost;
import com.comp4905.jobarc.Models.User;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {
    @POST("jobarc/register")
    Call<RegistrationResponse> createUser ( @Body User user );

    @POST("jobarc/login")
    Call<ResponseBody> checkUser ( @Body Login user );

    @GET("jobarc/dashboard")
    Call<ModelHome> getDashboard(@Query("id") long id);

    @POST("jobarc/post_job")
    Call<ResponseBody> postJob ( @Body JobPost jobPost );

    @POST("jobarc/post_resume")
    Call<ResponseBody> postResume ( @Body ResumePost resumePost );

    @GET("jobarc/profile")
    Call<Profile> getProfile(@Query("id") long id);

    @GET("jobarc/resume")
    Call<Resume> getResume(@Query("id") long id);

    @POST("jobarc/update_resume")
    Call<ResponseBody> updateResume ( @Body Resume resumeUpdate );

    @POST("jobarc/post_application")
    Call<ResponseBody> applyToJob ( @Body JobApplicationRequest jobApplication );

    @GET("jobarc/applicants")
    Call<ApplicantsModel> getApplicantsDetails(@Query("id") long id);

    @GET("jobarc/applicant_jobs")
    Call<ModelHome> getApplicantJobs(@Query("id") long id);

    @GET("jobarc/search_jobs")
    Call<ModelHome> getSearchJobs(@Query("keyword") String keyword);

}
