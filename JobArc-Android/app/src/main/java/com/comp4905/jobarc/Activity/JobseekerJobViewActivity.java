package com.comp4905.jobarc.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.comp4905.jobarc.Fragments.JobSeekerHomeFragment;
import com.comp4905.jobarc.Fragments.JobSeekerJobsFragment;
import com.comp4905.jobarc.Fragments.ProfileFragment;
import com.comp4905.jobarc.Fragments.SearchFragment;
import com.comp4905.jobarc.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class JobseekerJobViewActivity extends AppCompatActivity {

    TextView jobIdLabel, jobTitle, jobDescription, jobCreateDate, jobEmployer, jobLocation, jobType;;
    private BottomNavigationView bottomNavigationView;
    Button applyButton;

    private JobSeekerHomeFragment homeFragment;
    private JobSeekerJobsFragment jobsFragment;
    private SearchFragment searchFragment;
    private ProfileFragment profileFragment;
    private long userId;
    private long jobId;

    private String username;
    private String accountType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_view_jobseeker);

        jobIdLabel = findViewById(R.id.jobId);
        jobTitle = findViewById(R.id.jobTitle);
        jobDescription = findViewById(R.id.jobDescription);
        jobCreateDate= findViewById(R.id.jobCreateDate);
        jobLocation = findViewById(R.id.jobLocation);
        jobType = findViewById(R.id.jobType);
        jobEmployer = findViewById(R.id.jobEmployer);

        username = getIntent().getStringExtra("username");
        userId = getIntent().getLongExtra("userId", -1L);
        accountType = getIntent().getStringExtra("accountType");

        jobId = getIntent().getLongExtra("id", -1L);
        jobIdLabel.setText(String.valueOf(jobId));
        jobTitle.setText(getIntent().getStringExtra("title"));
        jobDescription.setText(getIntent().getStringExtra("description"));
        jobCreateDate.setText(getIntent().getStringExtra("createDate"));
        jobLocation.setText(getIntent().getStringExtra("location"));
        jobType.setText(getIntent().getStringExtra("type"));
        jobEmployer.setText(username);

        applyButton = findViewById(R.id.ApplyButton);


        jobsFragment = new JobSeekerJobsFragment(userId, username, accountType);
        searchFragment = new SearchFragment(userId, username, accountType);
        profileFragment = new ProfileFragment(userId);

        bottomNavigationView = findViewById(R.id.navMenuJobseeker);

        applyButton.setOnClickListener(view -> {
            Intent intent = new Intent(JobseekerJobViewActivity.this, ApplicationReviewActivity.class);
            intent.putExtra("username", username);
            intent.putExtra("userId", userId);
            intent.putExtra("jobId", jobId);
            intent.putExtra("accountType", accountType);
            startActivity(intent);
        });


        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {

            switch (item.getItemId()) {
                case R.id.menuJobs:
                    finish();
                case R.id.menuSearch:
                    finish();
                    return true;
                case R.id.menuProfile:
                    finish();
                    return true;
                case R.id.menuHome:
                    finish();
                    return true;
            }

            return false;
        });
    }

    @SuppressLint("ResourceType")
    private void setFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.mainFrameJobseeker, fragment);
        ft.commit();
    }
}