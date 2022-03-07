package com.comp4905.jobarc.Activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.comp4905.jobarc.Fragments.EmployerHomeFragment;
import com.comp4905.jobarc.Fragments.JobsFragment;
import com.comp4905.jobarc.Fragments.ProfileFragment;
import com.comp4905.jobarc.Fragments.SearchFragment;
import com.comp4905.jobarc.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class EmployerJobViewActivity extends AppCompatActivity {

    TextView jobId, jobTitle, jobDescription, jobCreateDate;
    private BottomNavigationView bottomNavigationView;

    private EmployerHomeFragment homeFragment;
    private JobsFragment jobsFragment = new JobsFragment();
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
                    case R.id.menuSettings:
                        setFragment(jobsFragment);
                        getSupportActionBar().setTitle("Settings");
                        return true;
                    case R.id.menuSearch:
                        setFragment(searchFragment);
                        getSupportActionBar().setTitle("Search");
                        return true;
                    case R.id.menuProfile:
                        setFragment(profileFragment);
                        getSupportActionBar().setTitle("Profile");
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