package com.comp4905.jobarc.Activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.comp4905.jobarc.Models.Resume;
import com.comp4905.jobarc.R;
import com.comp4905.jobarc.RetrofitClient;
import java.io.IOException;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateResumeActivity extends AppCompatActivity {

    long jobseekerId;
    EditText description;
    EditText skills;
    EditText certifications;
    EditText educationOne;
    EditText educationTwo;
    EditText educationThree;
    EditText workOne;
    EditText workTwo;
    EditText workThree;
    Resume resume;
    Button update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_resune);

        // load all the data.
        jobseekerId = getIntent().getLongExtra("id", -1L);
        update = findViewById(R.id.resumeUpdateBtn);
        description = findViewById(R.id.resumeDescriptionEntry);
        skills = findViewById(R.id.resumeSkillsEntry);
        certifications = findViewById(R.id.resumeCertificationsEntry);

        educationOne = findViewById(R.id.resumeEducationOneEntry);
        educationTwo = findViewById(R.id.resumeEducationTwoEntry);
        educationThree = findViewById(R.id.resumeEducationThreeEntry);

        workOne = findViewById(R.id.resumeWorkOneEntry);
        workTwo = findViewById(R.id.resumeWorkTwoEntry);
        workThree = findViewById(R.id.resumeWorkThreeEntry);

        Call<Resume> loadResumeCall = RetrofitClient
                .getInstance()
                .getAPI()
                .getResume(jobseekerId);


        loadResumeCall.enqueue(new Callback<Resume>() {
            @Override
            public void onResponse(Call<Resume> call, Response<Resume> response) {
                resume = response.body();
                if (resume != null){

                    loadCurrentResume();

                }else {
                    Toast.makeText(UpdateResumeActivity.this, "Error: Resume response NULL!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Resume> call, Throwable t) {
                Toast.makeText(UpdateResumeActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


        update.setOnClickListener(view -> {

            String descriptionText = description.getText().toString();
            String skillsText = skills.getText().toString();
            String certificationsText = certifications.getText().toString();

            String educationOneText = educationOne.getText().toString();
            String educationTwoText = educationTwo.getText().toString();
            String educationThreeText = educationThree.getText().toString();

            String workOneText = workOne.getText().toString();
            String workTwoText = workTwo.getText().toString();
            String workThreeText = workThree.getText().toString();

            if (descriptionText.isEmpty()) {
                description.setError("Description is required");
                description.requestFocus();
                return;
            } else if (skillsText.isEmpty()) {
                skills.setError("Skills is required");
                skills.requestFocus();
                return;
            } else if (educationOneText.isEmpty()) {
                skills.setError("Education #1 is required");
                skills.requestFocus();
                return;
            } else if (workOneText.isEmpty()) {
                skills.setError("Work #1 is required");
                skills.requestFocus();
                return;
            }

            resume.setDescription(descriptionText);
            resume.setEducationOne(educationOneText);
            resume.setEducationTwo(educationTwoText);
            resume.setEducationThree(educationThreeText);
            resume.setWorkOne(workOneText);
            resume.setWorkTwo(workTwoText);
            resume.setWorkThree(workThreeText);
            resume.setSkills(skillsText);
            resume.setCertifications(certificationsText);
            resume.setCreateDate(null);

            Call<ResponseBody> updateCall = RetrofitClient
                    .getInstance()
                    .getAPI()
                    .updateResume(resume);

            updateCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    String s = "";
                    try {
                        s = response.body().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (s.equals("\"SUCCESS\"")) {
                        Toast.makeText(UpdateResumeActivity.this, "Successfully updated Resume.", Toast.LENGTH_LONG).show();
                        finish();
                    } else {
                        Toast.makeText(UpdateResumeActivity.this, "Resume Update Failed!", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(UpdateResumeActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        });
    }


    public void loadCurrentResume(){


        description.setText(resume.getDescription());

        skills.setText(resume.getSkills());

        if(resume.getCertifications() != null && !resume.getCertifications().isEmpty())
            certifications.setText(resume.getCertifications());

        educationOne.setText(resume.getEducationOne());
        educationTwo.setText(resume.getEducationTwo());
        educationThree.setText(resume.getEducationThree());

        workOne.setText(resume.getWorkOne());
        workTwo.setText(resume.getWorkTwo());
        workThree.setText(resume.getWorkThree());

    }
}
