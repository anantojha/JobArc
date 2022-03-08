package com.comp4905.jobarc.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.comp4905.jobarc.R;

public class PostJobActivity extends AppCompatActivity {

    EditText newJobLocation;
    RadioButton fullTimeRadioBtn;
    RadioButton partTimeRadioBtn;
    RadioGroup jobTypeRadioGroup;
    CheckBox jobLocationCheckBox;
    Button jobNextButton;
    String username;
    long id;
    String jobTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_job);

        username = getIntent().getStringExtra("username");
        id = getIntent().getLongExtra("id", -1L);
        jobTitle = getIntent().getStringExtra("title");

        newJobLocation = findViewById(R.id.etLocation);
        jobTypeRadioGroup = findViewById(R.id.jobTypeRadioGroup);
        fullTimeRadioBtn = findViewById(R.id.fullTimeRadioButton);
        partTimeRadioBtn = findViewById(R.id.partTimeRadioButton);
        jobLocationCheckBox = findViewById(R.id.locationRemoteCheckBox);
        jobNextButton = findViewById(R.id.jobPostNextBtn);

        jobLocationCheckBox.setOnClickListener(v -> {
            if (((CheckBox) v).isChecked()) { newJobLocation.setEnabled(false); }
            else { newJobLocation.setEnabled(true); }
        });

        jobNextButton.setOnClickListener(v ->{

            String jobLocation = newJobLocation.getText().toString().trim();
            String jobType = "";

            if (jobTypeRadioGroup.getCheckedRadioButtonId() == -1){
                fullTimeRadioBtn.setError("Job Type required");
                jobTypeRadioGroup.requestFocus();
                return;
            } if(!jobLocationCheckBox.isChecked() && jobLocation.isEmpty()){
                newJobLocation.setError("Location is required");
                newJobLocation.requestFocus();
                return;
            }

            Intent intent = new Intent(v.getContext(), PostJobDescriptionActivity.class);
            intent.putExtra("id", id);
            intent.putExtra("username", username);
            intent.putExtra("title", jobTitle);
            if(fullTimeRadioBtn.isChecked()){ jobType = "Full-Time"; }
            else { jobType = "Part-Time"; }
            intent.putExtra("type", jobType);
            if(jobLocationCheckBox.isChecked()) { jobLocation = "Remote"; }
            intent.putExtra("location", jobLocation);
            startActivityForResult(intent, 2);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==2)
        {
            Intent intent = new Intent();
            setResult(2,intent);
            finish();
        }
    }
}