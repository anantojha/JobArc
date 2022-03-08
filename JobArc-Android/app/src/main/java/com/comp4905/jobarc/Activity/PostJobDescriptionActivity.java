package com.comp4905.jobarc.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.comp4905.jobarc.R;

public class PostJobDescriptionActivity extends AppCompatActivity {

    EditText newJobDescription;
    Button jobReviewButton;
    long id;
    String username;
    String title;
    String jobType;
    String location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_job_description);

        username = getIntent().getStringExtra("username");
        id = getIntent().getLongExtra("id", -1L);
        title = getIntent().getStringExtra("title");
        jobType = getIntent().getStringExtra("type");
        location = getIntent().getStringExtra("location");

        newJobDescription = findViewById(R.id.etJobDescription);
        jobReviewButton = findViewById(R.id.jobPostReviewBtn);

        jobReviewButton.setOnClickListener(v ->{

            String description = newJobDescription.getText().toString().trim();

            if (description.isEmpty()) {
                newJobDescription.setError("description is required");
                newJobDescription.requestFocus();
                return;
            }

            Intent intent = new Intent(v.getContext(), PostJobReviewActivity.class);
            intent.putExtra("id", id);
            intent.putExtra("username", username);
            intent.putExtra("title", title);
            intent.putExtra("type", jobType);
            intent.putExtra("location", location);
            intent.putExtra("description", description);
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