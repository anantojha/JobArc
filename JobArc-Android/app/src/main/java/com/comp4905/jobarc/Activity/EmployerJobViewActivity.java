package com.comp4905.jobarc.Activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.comp4905.jobarc.Fragments.EmployerHomeFragment;
import com.comp4905.jobarc.Fragments.EmployerJobsFragment;
import com.comp4905.jobarc.Fragments.ProfileFragment;
import com.comp4905.jobarc.Fragments.SearchFragment;
import com.comp4905.jobarc.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class EmployerJobViewActivity extends AppCompatActivity {

    TextView jobId, jobEmployer, jobTitle, jobDescription, jobCreateDate, jobLocation, jobType;
    private BottomNavigationView bottomNavigationView;
    private long userId;
    private String username;

    private EmployerHomeFragment homeFragment;
    private EmployerJobsFragment jobsFragment;
    private SearchFragment searchFragment = new SearchFragment();
    private ProfileFragment profileFragment = new ProfileFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_view);

        jobId = findViewById(R.id.jobId);
        jobTitle = findViewById(R.id.jobTitle);
        jobDescription = findViewById(R.id.jobDescription);
        jobCreateDate= findViewById(R.id.jobCreateDate);
        jobLocation = findViewById(R.id.jobLocation);
        jobType = findViewById(R.id.jobType);
        jobEmployer = findViewById(R.id.jobEmployer);

        username = getIntent().getStringExtra("username");
        userId = getIntent().getLongExtra("id", -1L);
        jobId.setText(String.valueOf(getIntent().getLongExtra("id", -1L)));
        jobTitle.setText(getIntent().getStringExtra("title"));
        jobDescription.setText(getIntent().getStringExtra("description"));
        jobCreateDate.setText(getIntent().getStringExtra("createDate"));
        jobLocation.setText(getIntent().getStringExtra("location"));
        jobType.setText(getIntent().getStringExtra("type"));
        jobEmployer.setText(username);

        bottomNavigationView = findViewById(R.id.navMenuJobseeker);

        jobsFragment = new EmployerJobsFragment(userId, username);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {

                switch (item.getItemId()) {
                    case R.id.menuJobs:
                        finish();
                        setFragment(jobsFragment);
                    case R.id.menuSearch:
                        finish();
                        setFragment(searchFragment);
                        return true;
                    case R.id.menuProfile:
                        finish();
                        setFragment(profileFragment);
                        return true;
                    case R.id.menuHome:
                        finish();
                        return true;
                }

            return false;
        });
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.mainFrameJobseeker, fragment);
        ft.commit();
    }
}