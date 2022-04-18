package com.comp4905.jobarc.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.comp4905.jobarc.Models.JobPost;
import com.comp4905.jobarc.R;
import com.comp4905.jobarc.RetrofitClient;
import java.io.IOException;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostJobReviewActivity extends AppCompatActivity {

    Button submitButton;
    long id;
    String username;
    String title;
    String jobType;
    String location;
    String description;
    TextView  titleView;
    TextView  employerNameView;
    TextView  locationView;
    TextView  jobTypeView;
    TextView  descriptionView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_job_review);

        username = getIntent().getStringExtra("username");
        id = getIntent().getLongExtra("id", -1L);
        title = getIntent().getStringExtra("title");
        jobType = getIntent().getStringExtra("type");
        location = getIntent().getStringExtra("location");
        description = getIntent().getStringExtra("description");

        titleView = findViewById(R.id.titleReview);
        employerNameView = findViewById(R.id.employerNameReview);
        locationView = findViewById(R.id.locationReview);
        jobTypeView = findViewById(R.id.jobTypeReview);
        descriptionView = findViewById(R.id.descriptionReview);

        titleView.setText(title);
        employerNameView.setText(username);
        locationView.setText(location);
        descriptionView.setText(description);
        jobTypeView.setText(jobType);

        submitButton = findViewById(R.id.jobPostSubmitBtn);

        submitButton.setOnClickListener(v ->{

            Call<ResponseBody> call = RetrofitClient
                    .getInstance()
                    .getAPI()
                    .postJob(new JobPost(title, description, id, location, jobType));

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
                        Toast.makeText(PostJobReviewActivity.this, "Successfully posted job", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent();
                        setResult(2,intent);
                        finish();
                    } else {
                        Toast.makeText(PostJobReviewActivity.this, "error posting job!", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(PostJobReviewActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        });
    }
}