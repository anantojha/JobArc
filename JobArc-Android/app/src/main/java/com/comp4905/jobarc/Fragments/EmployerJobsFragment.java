package com.comp4905.jobarc.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.comp4905.jobarc.Activity.PostJobActivity;
import com.comp4905.jobarc.R;

public class EmployerJobsFragment extends Fragment {

    private static final String TAG = "Employer Jobs Fragment: ";
    private Long id;
    private String username;
    private String accountType;
    EditText newJobTitle;


    public EmployerJobsFragment(Long id, String username, String accountType) {
        // Required empty public constructor
        this.id = id;
        this.username= username;
        this.accountType = accountType;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_jobs_employer, container, false);
        newJobTitle = view.findViewById(R.id.etJobTitle);
        view.findViewById(R.id.startNewJobButton).setOnClickListener(v -> startJobPostActivity(view));
        return view;
    }

    private void startJobPostActivity(View view){

        String jobTitle = newJobTitle.getText().toString().trim();

        if (jobTitle.isEmpty()) {
            newJobTitle.setError("Title is required");
            newJobTitle.requestFocus();
            return;
        }


        Intent intent = new Intent(view.getContext(), PostJobActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("username", username);
        intent.putExtra("title", jobTitle);
        startActivity(intent);
        newJobTitle.setText("");
    }
}
