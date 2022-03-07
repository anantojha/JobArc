package com.comp4905.jobarc.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.comp4905.jobarc.Fragments.EmployerHomeFragment;
import com.comp4905.jobarc.Fragments.JobsFragment;
import com.comp4905.jobarc.Fragments.ProfileFragment;
import com.comp4905.jobarc.Fragments.SearchFragment;
import com.comp4905.jobarc.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class EmployerDashboardActivity extends AppCompatActivity {

    private EmployerHomeFragment homeFragment;
    private JobsFragment jobsFragment = new JobsFragment();
    private SearchFragment searchFragment = new SearchFragment();
    private ProfileFragment profileFragment = new ProfileFragment();

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_jobseeker);

        String username = getIntent().getStringExtra("username");
        long id = getIntent().getLongExtra("id", -1L);
        String accountType = getIntent().getStringExtra("accountType");

        homeFragment = new EmployerHomeFragment(id);

        bottomNavigationView = findViewById(R.id.navMenuJobseeker);
        setFragment(homeFragment);
        bottomNavigationView.setSelectedItemId(R.id.menuHome);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            if(item.isChecked()){
                return true;
            } else {
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
                    default:
                        setFragment(homeFragment);
                        getSupportActionBar().setTitle("Home");
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