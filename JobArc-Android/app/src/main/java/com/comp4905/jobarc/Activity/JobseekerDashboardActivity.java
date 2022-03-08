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
    private JobSeekerJobsFragment jobsFragment = new JobSeekerJobsFragment();
    private SearchFragment searchFragment = new SearchFragment();
    private ProfileFragment profileFragment = new ProfileFragment();

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_jobseeker);

        String username = getIntent().getStringExtra("name");
        long id = getIntent().getLongExtra("id", -1L);
        String accountType = getIntent().getStringExtra("accountType");

        homeFragment = new JobSeekerHomeFragment(id, username);

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


//    TextView tvWelcome;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_dashboard_Employer);

//        tvWelcome = findViewById(R.id.tvWelcome);
//        long id = getIntent().getLongExtra("id", -1L);
//        getDashboardData(id);
//
//        findViewById(R.id.btnLogout).setOnClickListener(view ->
//                startActivity(new Intent(JobseekerDashboardActivity.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)));
//    }

//    private void getDashboardData(long id){
//        Call<ResponseBody> call = RetrofitClient
//                .getInstance()
//                .getAPI()
//                .getDashboard(id);
//
//        call.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                String s = "";
//                JSONObject jsonObject = null;
//
//                try {
//                    s = response.body().string();
//                    if (s != null) {
//                        jsonObject = new JSONObject(s);
//                        String welcomeText = "Welcome, " + jsonObject.getString("name") + ".";
//                        tvWelcome.setText(welcomeText);
//                    }
//                } catch (IOException | JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                Toast.makeText(JobseekerDashboardActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
//            }
//        });
//    }