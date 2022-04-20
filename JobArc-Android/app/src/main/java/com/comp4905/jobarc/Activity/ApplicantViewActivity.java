package com.comp4905.jobarc.Activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.comp4905.jobarc.R;

public class ApplicantViewActivity extends AppCompatActivity {


    private long userId;
    private String username;
    private String accountType;

    private TextView resumeIdView;
    private TextView jobseekerNameView;
    private TextView descriptionView;
    private TextView createDateView;
    private TextView eduOneView;
    private TextView eduTwoView;
    private TextView eduThreeView;
    private TextView workOneView;
    private TextView workTwoView;
    private TextView workThreeView;
    private TextView skillsView;
    private TextView certificationsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_applicant);

        resumeIdView = findViewById(R.id.applicantionIdLabel);
        jobseekerNameView = findViewById(R.id.applicantNameLabel);
        descriptionView = findViewById(R.id.applicantSummaryLabel);
        createDateView = findViewById(R.id.applicationCreateLabel);
        eduOneView = findViewById(R.id.applicantEducationOneLabel);
        eduTwoView = findViewById(R.id.applicantEducationTwoLabel);
        eduThreeView = findViewById(R.id.applicantEducationThreeLabel);
        workOneView = findViewById(R.id.applicantWorkOneLabel);
        workTwoView = findViewById(R.id.applicantWorkTwoLabel);
        workThreeView = findViewById(R.id.applicantWorkThreeLabel);
        skillsView = findViewById(R.id.applicantSkillsLabel);
        certificationsView = findViewById(R.id.applicantCertificationsLabel);


        userId = getIntent().getLongExtra("userId", -1L);
        username = getIntent().getStringExtra("username");
        accountType = getIntent().getStringExtra("accountType");

        resumeIdView.setText(String.valueOf(getIntent().getLongExtra("resumeId", -1L)));
        jobseekerNameView.setText(getIntent().getStringExtra("jobseekerName"));
        descriptionView.setText(getIntent().getStringExtra("description"));
        createDateView.setText(getIntent().getStringExtra("createDate"));
        eduOneView.setText(getIntent().getStringExtra("eduOne"));
        eduTwoView.setText(getIntent().getStringExtra("eduTwo"));
        eduThreeView.setText(getIntent().getStringExtra("eduThree"));
        workOneView.setText(getIntent().getStringExtra("workOne"));
        workTwoView.setText(getIntent().getStringExtra("workTwo"));
        workThreeView.setText(getIntent().getStringExtra("workThree"));
        skillsView.setText(getIntent().getStringExtra("skills"));
        certificationsView.setText(getIntent().getStringExtra("certifications"));
    }
}
