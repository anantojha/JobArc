package com.comp4905.jobarc.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import com.comp4905.jobarc.Activity.LoginActivity;
import com.comp4905.jobarc.Activity.UpdateResumeActivity;
import com.comp4905.jobarc.Models.Profile;
import com.comp4905.jobarc.Models.User;
import com.comp4905.jobarc.R;
import com.comp4905.jobarc.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {


    private static final String TAG = "Employer Jobs Fragment: ";
    private Long id;
    private Profile profile;
    TextView name;
    TextView username;
    TextView accounType;
    TextView userId;
    Button updateResumeBtn;



    public ProfileFragment(Long id) {
        // Required empty public constructor
        this.id = id;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        name = view.findViewById(R.id.profileName);
        username = view.findViewById(R.id.profileUsername);
        accounType = view.findViewById(R.id.profileAccountType);
        userId = view.findViewById(R.id.profileId);
        updateResumeBtn = view.findViewById(R.id.updateProfileResumeBtn);

        getUserProfile(id);

        view.findViewById(R.id.logoutButton).setOnClickListener(v -> logout(view));
        return view;
    }

    private void logout(View view) {
        Intent signout = new Intent(view.getContext(), LoginActivity.class);
        startActivity(signout);
    }

    private void getUserProfile(long id) {
        Call<Profile> call = RetrofitClient
                .getInstance()
                .getAPI()
                .getProfile(id);

        call.enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {
                if(response.errorBody() != null){
                    Log.w(TAG, "onResponse: " + response.errorBody());
                } else {
                    profile = response.body();
                    name.setText(profile.getName());
                    username.setText(profile.getUsername());
                    accounType.setText(profile.getAccountType());
                    userId.setText(String.valueOf(profile.getId()));

                    if (profile.getAccountType().equals("jobseeker")){
                        updateResumeBtn.setVisibility(View.VISIBLE);

                        updateResumeBtn.setOnClickListener(view -> {
                            Intent intent = new Intent(view.getContext(), UpdateResumeActivity.class);
                            intent.putExtra("id", Long.valueOf(profile.getId()));
                            startActivity(intent);
                        });
                    }
                }
            }

            @Override
            public void onFailure(Call<Profile> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
