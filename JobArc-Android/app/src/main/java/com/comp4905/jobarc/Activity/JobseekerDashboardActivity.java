package com.comp4905.jobarc.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.comp4905.jobarc.Fragments.JobSeekerHomeFragment;
import com.comp4905.jobarc.Fragments.EmployerJobsFragment;
import com.comp4905.jobarc.Fragments.JobSeekerJobsFragment;
import com.comp4905.jobarc.Fragments.ProfileFragment;
import com.comp4905.jobarc.Fragments.SearchFragment;
import com.comp4905.jobarc.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class JobseekerDashboardActivity extends AppCompatActivity {

    private JobSeekerHomeFragment homeFragment;
    private JobSeekerJobsFragment jobsFragment;
    private SearchFragment searchFragment = new SearchFragment();
    private ProfileFragment profileFragment;

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_jobseeker);

        String username = getIntent().getStringExtra("name");
        long id = getIntent().getLongExtra("id", -1L);
        String accountType = getIntent().getStringExtra("accountType");

        homeFragment = new JobSeekerHomeFragment(id, username, accountType);
        jobsFragment = new JobSeekerJobsFragment(id, username, accountType);
        profileFragment = new ProfileFragment(id);

        bottomNavigationView = findViewById(R.id.navMenuJobseeker);
        setFragment(homeFragment);
        bottomNavigationView.setSelectedItemId(R.id.menuHome);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            if(item.isChecked()){
                return true;
            } else {
                switch (item.getItemId()) {
                    case R.id.menuJobs:
                        setFragment(jobsFragment);
                        return true;
                    case R.id.menuSearch:
                        setFragment(searchFragment);
                        return true;
                    case R.id.menuProfile:
                        setFragment(profileFragment);
                        return true;
                    default:
                        setFragment(homeFragment);
                        return true;
                }
            }
        });
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.mainFrameJobseeker, fragment);
        ft.commit();
    }

}