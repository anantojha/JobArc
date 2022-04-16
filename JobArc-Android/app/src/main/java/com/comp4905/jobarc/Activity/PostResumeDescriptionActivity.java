package com.comp4905.jobarc.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.comp4905.jobarc.R;

public class PostResumeDescriptionActivity  extends AppCompatActivity {

    EditText description;
    EditText skills;
    EditText certifications;
    String descriptionText;
    String skillsText;
    String certificationsText;
    Button nextButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_resume_description);

        description = findViewById(R.id.resumeDescriptionEditText);
        skills = findViewById(R.id.resumeSkillsEditText);
        certifications = findViewById(R.id.resumeCertificationsEditText);
        nextButton = findViewById(R.id.resumeDescriptionNextBtn);

        nextButton.setOnClickListener(view -> {

            if (description.getText().toString().isEmpty()){
                description.setError("Description required");
                description.requestFocus();
                return;
            } else if(skills.getText().toString().isEmpty()){
                skills.setError("skills required");
                skills.requestFocus();
                return;
            }

            descriptionText = description.getText().toString();
            skillsText = skills.getText().toString();
            certificationsText = certifications.getText().toString();

            Intent intent = new Intent(PostResumeDescriptionActivity.this, PostResumeEducationActivity.class);
            intent.putExtra("id", getIntent().getLongExtra("id", -1L));
            intent.putExtra("description", descriptionText);
            intent.putExtra("skills", skillsText);
            intent.putExtra("certifications", certificationsText);
            startActivity(intent);
        });
    }
}
