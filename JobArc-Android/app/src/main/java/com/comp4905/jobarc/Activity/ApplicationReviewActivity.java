package com.comp4905.jobarc.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.comp4905.jobarc.Models.JobApplicationRequest;
import com.comp4905.jobarc.Models.Resume;
import com.comp4905.jobarc.Models.ResumePost;
import com.comp4905.jobarc.R;
import com.comp4905.jobarc.RetrofitClient;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApplicationReviewActivity extends AppCompatActivity {

    long userId;
    long jobId;
    String username;
    String accountType;
    Resume resume;
    TextView name;
    TextView summary;
    TextView skills;
    TextView certifications;
    TextView eduOne;
    TextView eduTwo;
    TextView eduThree;
    TextView workOne;
    TextView workTwo;
    TextView workThree;
    Button submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_review);

        userId = getIntent().getLongExtra("userId", -1L);
        jobId = getIntent().getLongExtra("jobId", -1L);
        username = getIntent().getStringExtra("username");
        accountType = getIntent().getStringExtra("accountType");

        if(accountType.equals("employer"))
        {
            submitBtn.setVisibility(View.GONE);
        }



        name = findViewById(R.id.applicantNameReviewLabel);
        summary = findViewById(R.id.resumeSummaryReviewLabel);
        skills = findViewById(R.id.resumeSkillsReviewLabel);
        certifications = findViewById(R.id.resumeCertificationsReviewLabel);
        eduOne = findViewById(R.id.resumeEducationOneReviewLabel);
        eduTwo = findViewById(R.id.resumeEducationTwoReviewLabel);
        eduThree = findViewById(R.id.resumeEducationThreeReviewLabel);
        workOne = findViewById(R.id.resumeWorkOneReviewLabel);
        workTwo = findViewById(R.id.resumeWorkTwoReviewLabel);
        workThree = findViewById(R.id.resumeWorkThreeReviewLabel);
        submitBtn = findViewById(R.id.resumeSubmitReviewBtn);

        Call<Resume> loadResumeCall = RetrofitClient
                .getInstance()
                .getAPI()
                .getResume(userId);


        loadResumeCall.enqueue(new Callback<Resume>() {
            @Override
            public void onResponse(Call<Resume> call, Response<Resume> response) {
                resume = response.body();
                if (resume != null){

                    loadResumeData();

                }else {
                    Toast.makeText(ApplicationReviewActivity.this, "Error: Resume response NULL!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Resume> call, Throwable t) {
                Toast.makeText(ApplicationReviewActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


        submitBtn.setOnClickListener(view -> {


            Call<ResponseBody> call = RetrofitClient
                    .getInstance()
                    .getAPI()
                    .applyToJob(new JobApplicationRequest(userId, jobId));


            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    String s = "";
                    try {
                        s = response.body().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (s.equals("\"SUCCESS\"")) {
                        Toast.makeText(ApplicationReviewActivity.this, "Application Submitted Successfully.", Toast.LENGTH_LONG).show();
                        finish();
                        finish();
                    } else {
                        Toast.makeText(ApplicationReviewActivity.this, "Already Applied for this Job.", Toast.LENGTH_LONG).show();
                        finish();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(ApplicationReviewActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });

        });
    }


    public void loadResumeData(){
        name.setText(resume.getJobseekerName());
        summary.setText(resume.getDescription());
        skills.setText(resume.getSkills());
        certifications.setText(resume.getCertifications());
        eduOne.setText(resume.getEducationOne());
        eduTwo.setText(resume.getEducationTwo());
        eduThree.setText(resume.getEducationThree());
        workOne.setText(resume.getWorkOne());
        workTwo.setText(resume.getWorkTwo());
        workThree.setText(resume.getWorkThree());
    }
}
