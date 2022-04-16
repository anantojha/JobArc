package com.comp4905.jobarc.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.comp4905.jobarc.R;

import java.util.Date;

public class PostResumeEducationActivity extends AppCompatActivity {

    View educationOne;
    View educationTwo;
    boolean educationTwoVisible = false;
    Button educationTwoButton;
    View educationThree;
    boolean educationThreeVisible = false;
    Button educationThreeButton;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_resume_education);

        educationOne = findViewById(R.id.educationRowOne);
        educationTwo = findViewById(R.id.educationRowTwo);
        educationTwoButton = findViewById(R.id.addEducationTwoBtn);
        educationThree = findViewById(R.id.educationRowThree);
        educationThreeButton = findViewById(R.id.addEducationThreeBtn);
        next = findViewById(R.id.resumeEducationNextBtn);

        educationTwoButton.setOnClickListener(view -> {
            if(educationTwoVisible){
                educationTwo.setVisibility(View.GONE);
                educationTwoVisible = false;
            } else {
                educationTwo.setVisibility(View.VISIBLE);
                educationTwoVisible = true;
            }
        });

        educationThreeButton.setOnClickListener(view -> {
            if(educationThreeVisible){
                educationThree.setVisibility(View.GONE);
                educationThreeVisible = false;
            } else {
                educationThree.setVisibility(View.VISIBLE);
                educationThreeVisible = true;
            }
        });


        next.setOnClickListener(view -> {
            Spinner educationOneLevel = educationOne.findViewById(R.id.educationLevelSpinner);
            EditText educationOneProgram = educationOne.findViewById(R.id.educationAreaOfStudyEditText);
            EditText educationOneSchool = educationOne.findViewById(R.id.educationSchoolEditText);
            EditText educationOneStart = educationOne.findViewById(R.id.educationStartEditTextDate);
            EditText educationOneEnd = educationOne.findViewById(R.id.educationEndEditTextDate);

            String educationOneLevelText = educationOneLevel.getSelectedItem().toString();
            String educationOneProgramText = educationOneProgram.getText().toString();
            String educationOneSchoolText = educationOneSchool.getText().toString();
            String educationOneStartText = educationOneStart.getText().toString();
            String educationOneEndText = educationOneEnd.getText().toString();


            if(educationOneProgramText.isEmpty()){
                educationOneProgram.setError("Area of Study required");
                educationOneProgram.requestFocus();
                return;
            } else if (educationOneSchoolText.isEmpty()){
                educationOneSchool.setError("School/Institution required");
                educationOneSchool.requestFocus();
                return;
            } else if(educationOneStartText.isEmpty()) {
                educationOneStart.setError("Start Date required");
                educationOneStart.requestFocus();
                return;
            } else if(educationOneEndText.isEmpty()){
                educationOneEnd.setError("End Date required");
                educationOneEnd.requestFocus();
                return;
            }

            String educationOneText = educationOneLevelText + "\n\n" + educationOneProgramText + "\n\n" +
                educationOneSchoolText + "\n\n" + educationOneStartText + " to " + educationOneEndText;

            String educationTwoText = "";
            String educationThreeText = "";

            if(educationTwoVisible){
                Spinner educationTwoLevel = educationTwo.findViewById(R.id.educationLevelSpinner);
                EditText educationTwoProgram = educationTwo.findViewById(R.id.educationAreaOfStudyEditText);
                EditText educationTwoSchool = educationTwo.findViewById(R.id.educationSchoolEditText);
                EditText educationTwoStart = educationTwo.findViewById(R.id.educationStartEditTextDate);
                EditText educationTwoEnd = educationTwo.findViewById(R.id.educationEndEditTextDate);

                String educationTwoLevelText = educationTwoLevel.getSelectedItem().toString();
                String educationTwoProgramText = educationTwoProgram.getText().toString();
                String educationTwoSchoolText = educationTwoSchool.getText().toString();
                String educationTwoStartText = educationTwoStart.getText().toString();
                String educationTwoEndText = educationTwoEnd.getText().toString();


                if(educationTwoProgramText.isEmpty()){
                    educationTwoProgram.setError("Area of Study required");
                    educationTwoProgram.requestFocus();
                    return;
                } else if (educationTwoSchoolText.isEmpty()){
                    educationTwoSchool.setError("School/Institution required");
                    educationTwoSchool.requestFocus();
                    return;
                } else if(educationTwoStartText.isEmpty()) {
                    educationTwoStart.setError("Start Date required");
                    educationTwoStart.requestFocus();
                    return;
                } else if(educationTwoEndText.isEmpty()){
                    educationTwoEnd.setError("End Date required");
                    educationTwoEnd.requestFocus();
                    return;
                }

                educationTwoText = educationTwoLevelText + "\n\n" + educationTwoProgramText + "\n\n" +
                        educationTwoSchoolText + "\n\n" + educationTwoStartText + " to " + educationTwoEndText;
            }

            if(educationThreeVisible){
                Spinner educationThreeLevel = educationThree.findViewById(R.id.educationLevelSpinner);
                EditText educationThreeProgram = educationThree.findViewById(R.id.educationAreaOfStudyEditText);
                EditText educationThreeSchool = educationThree.findViewById(R.id.educationSchoolEditText);
                EditText educationThreeStart = educationThree.findViewById(R.id.educationStartEditTextDate);
                EditText educationThreeEnd = educationThree.findViewById(R.id.educationEndEditTextDate);

                String educationThreeLevelText = educationThreeLevel.getSelectedItem().toString();
                String educationThreeProgramText = educationThreeProgram.getText().toString();
                String educationThreeSchoolText = educationThreeSchool.getText().toString();
                String educationThreeStartText = educationThreeStart.getText().toString();
                String educationThreeEndText = educationThreeEnd.getText().toString();


                if(educationThreeProgramText.isEmpty()){
                    educationThreeProgram.setError("Area of Study required");
                    educationThreeProgram.requestFocus();
                    return;
                } else if (educationThreeSchoolText.isEmpty()){
                    educationThreeSchool.setError("School/Institution required");
                    educationThreeSchool.requestFocus();
                    return;
                } else if(educationThreeStartText.isEmpty()) {
                    educationThreeStart.setError("Start Date required");
                    educationThreeStart.requestFocus();
                    return;
                } else if(educationThreeEndText.isEmpty()){
                    educationThreeEnd.setError("End Date required");
                    educationThreeEnd.requestFocus();
                    return;
                }

                educationThreeText = educationThreeLevelText + "\n\n" + educationThreeProgramText + "\n\n" +
                        educationThreeSchoolText + "\n\n" + educationThreeStartText + " to " + educationThreeEndText;
            }


            Intent intent = new Intent(PostResumeEducationActivity.this, PostResumeWorkActivity.class);
            intent.putExtra("id", getIntent().getLongExtra("id", -1L));
            intent.putExtra("description", getIntent().getStringExtra("description"));
            intent.putExtra("skills", getIntent().getStringExtra("skills"));
            intent.putExtra("certifications", getIntent().getStringExtra("certifications"));
            intent.putExtra("educationOne", educationOneText);
            intent.putExtra("educationTwo", educationTwoText);
            intent.putExtra("educationThree", educationThreeText);
            startActivity(intent);
        });
    }
}
