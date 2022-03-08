package com.comp4905.jobarc.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.comp4905.jobarc.Activity.EmployerDashboardActivity;
import com.comp4905.jobarc.Activity.LoginActivity;
import com.comp4905.jobarc.Activity.PostJobActivity;
import com.comp4905.jobarc.Adapters.HomeAdapter;
import com.comp4905.jobarc.Models.Job;
import com.comp4905.jobarc.R;

import java.util.ArrayList;
import java.util.List;

public class EmployerJobsFragment extends Fragment {

    private static final String TAG = "Employer Jobs Fragment: ";
    private Long id;
    private String username;
    EditText newJobTitle;


    public EmployerJobsFragment(Long id, String username) {
        // Required empty public constructor
        this.id = id;
        this.username= username;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_jobs, container, false);
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
