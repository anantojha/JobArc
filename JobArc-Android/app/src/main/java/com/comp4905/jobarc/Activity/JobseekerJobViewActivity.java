package com.comp4905.jobarc.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.comp4905.jobarc.Fragments.JobSeekerHomeFragment;
import com.comp4905.jobarc.Fragments.EmployerJobsFragment;
import com.comp4905.jobarc.Fragments.JobSeekerJobsFragment;
import com.comp4905.jobarc.Fragments.ProfileFragment;
import com.comp4905.jobarc.Fragments.SearchFragment;
import com.comp4905.jobarc.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class JobseekerJobViewActivity extends AppCompatActivity {

    TextView jobId, jobTitle, jobDescription, jobCreateDate;
    private BottomNavigationView bottomNavigationView;

    private JobSeekerHomeFragment homeFragment;
    private JobSeekerJobsFragment jobsFragment = new JobSeekerJobsFragment();
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


        jobId.setText(String.valueOf(getIntent().getLongExtra("id", -1L)));
        jobTitle.setText(getIntent().getStringExtra("title"));
        jobDescription.setText(getIntent().getStringExtra("description"));
        jobCreateDate.setText(getIntent().getStringExtra("createDate"));

        bottomNavigationView = findViewById(R.id.navMenuJobseeker);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {

                switch (item.getItemId()) {
                    case R.id.menuJobs:
                        Intent intent = new Intent(JobseekerJobViewActivity.this, EmployerJobsFragment.class);
                        startActivity(intent);
                    case R.id.menuSearch:
                        setFragment(searchFragment);
                        return true;
                    case R.id.menuProfile:
                        setFragment(profileFragment);
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