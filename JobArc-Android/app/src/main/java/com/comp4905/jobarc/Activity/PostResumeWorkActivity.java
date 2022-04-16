package com.comp4905.jobarc.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.comp4905.jobarc.Models.RegistrationResponse;
import com.comp4905.jobarc.Models.ResumePost;
import com.comp4905.jobarc.Models.User;
import com.comp4905.jobarc.R;
import com.comp4905.jobarc.RetrofitClient;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostResumeWorkActivity extends AppCompatActivity {


    View workOne;
    View workTwo;
    boolean workTwoVisible = false;
    Button workTwoButton;
    View workThree;
    boolean workThreeVisible = false;
    Button workThreeButton;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_resume_work);


        workOne = findViewById(R.id.workRowOne);
        workTwo = findViewById(R.id.workRowTwo);
        workTwoButton = findViewById(R.id.addWorkTwoBtn);
        workThree = findViewById(R.id.workRowThree);
        workThreeButton = findViewById(R.id.addWorkThreeBtn);
        submit = findViewById(R.id.resumeWorkSubmitBtn);

        workTwoButton.setOnClickListener(view -> {
            if(workTwoVisible){
                workTwo.setVisibility(View.GONE);
                workTwoVisible = false;
            } else {
                workTwo.setVisibility(View.VISIBLE);
                workTwoVisible = true;
            }
        });

        workThreeButton.setOnClickListener(view -> {
            if(workThreeVisible){
                workThree.setVisibility(View.GONE);
                workThreeVisible = false;
            } else {
                workThree.setVisibility(View.VISIBLE);
                workThreeVisible = true;
            }
        });


        submit.setOnClickListener(view -> {

            EditText workOnePosition = workOne.findViewById(R.id.workPositionEditText);
            EditText workOneCompany = workOne.findViewById(R.id.workCompanyEditText);
            EditText workOneStart = workOne.findViewById(R.id.workStartEditTextDate);
            EditText workOneEnd = workOne.findViewById(R.id.workEndEditTextDate);
            EditText workOneResponsibilities = workOne.findViewById(R.id.workResponsibilitiesEditText);

            String workOnePositionText = workOnePosition.getText().toString();
            String workOneCompanyText = workOneCompany.getText().toString();
            String workOneStartText = workOneStart.getText().toString();
            String workOneEndText = workOneEnd.getText().toString();
            String workOneResponsibilitiesText = workOneResponsibilities.getText().toString();

            if(workOnePositionText.isEmpty()){
                workOnePosition.setError("Position required");
                workOnePosition.requestFocus();
                return;
            } else if (workOneCompanyText.isEmpty()){
                workOneCompany.setError("Company required");
                workOneCompany.requestFocus();
                return;
            } else if(workOneStartText.isEmpty()) {
                workOneStart.setError("Start Date required");
                workOneStart.requestFocus();
                return;
            } else if(workOneEndText.isEmpty()){
                workOneEnd.setError("End Date required");
                workOneEnd.requestFocus();
                return;
            } else if(workOneResponsibilitiesText.isEmpty()){
                workOneResponsibilities.setError("Responsibilities required");
                workOneResponsibilities.requestFocus();
                return;
            }


            String workOneText = workOnePositionText + "\n\n" + workOneCompanyText + "\n\n" +
                    workOneStartText + " to " + workOneEndText + "\n\n" + workOneResponsibilitiesText;

            String workTwoText = "";
            String workThreeText = "";

            if(workTwoVisible){
                EditText workTwoPosition = workTwo.findViewById(R.id.workPositionEditText);
                EditText workTwoCompany = workTwo.findViewById(R.id.workCompanyEditText);
                EditText workTwoStart = workTwo.findViewById(R.id.workStartEditTextDate);
                EditText workTwoEnd = workTwo.findViewById(R.id.workEndEditTextDate);
                EditText workTwoResponsibilities = workTwo.findViewById(R.id.workResponsibilitiesEditText);

                String workTwoPositionText = workTwoPosition.getText().toString();
                String workTwoCompanyText = workTwoCompany.getText().toString();
                String workTwoStartText = workTwoStart.getText().toString();
                String workTwoEndText = workTwoEnd.getText().toString();
                String workTwoResponsibilitiesText = workTwoResponsibilities.getText().toString();

                if(workTwoPositionText.isEmpty()){
                    workTwoPosition.setError("Position required");
                    workTwoPosition.requestFocus();
                    return;
                } else if (workTwoCompanyText.isEmpty()){
                    workTwoCompany.setError("Company required");
                    workTwoCompany.requestFocus();
                    return;
                } else if(workTwoStartText.isEmpty()) {
                    workTwoStart.setError("Start Date required");
                    workTwoStart.requestFocus();
                    return;
                } else if(workTwoEndText.isEmpty()){
                    workTwoEnd.setError("End Date required");
                    workTwoEnd.requestFocus();
                    return;
                } else if(workTwoResponsibilitiesText.isEmpty()){
                    workTwoResponsibilities.setError("Responsibilities required");
                    workTwoResponsibilities.requestFocus();
                    return;
                }

                workTwoText = workTwoPositionText + "\n\n" + workTwoCompanyText + "\n\n" +
                        workTwoStartText + " to " + workTwoEndText + "\n\n" + workTwoResponsibilitiesText;
            }

            if(workThreeVisible){
                EditText workThreePosition = workThree.findViewById(R.id.workPositionEditText);
                EditText workThreeCompany = workThree.findViewById(R.id.workCompanyEditText);
                EditText workThreeStart = workThree.findViewById(R.id.workStartEditTextDate);
                EditText workThreeEnd = workThree.findViewById(R.id.workEndEditTextDate);
                EditText workThreeResponsibilities = workThree.findViewById(R.id.workResponsibilitiesEditText);

                String workThreePositionText = workThreePosition.getText().toString();
                String workThreeCompanyText = workThreeCompany.getText().toString();
                String workThreeStartText = workThreeStart.getText().toString();
                String workThreeEndText = workThreeEnd.getText().toString();
                String workThreeResponsibilitiesText = workThreeResponsibilities.getText().toString();

                if(workThreePositionText.isEmpty()){
                    workThreePosition.setError("Position required");
                    workThreePosition.requestFocus();
                    return;
                } else if (workThreeCompanyText.isEmpty()){
                    workThreeCompany.setError("Company required");
                    workThreeCompany.requestFocus();
                    return;
                } else if(workThreeStartText.isEmpty()) {
                    workThreeStart.setError("Start Date required");
                    workThreeStart.requestFocus();
                    return;
                } else if(workThreeEndText.isEmpty()){
                    workThreeEnd.setError("End Date required");
                    workThreeEnd.requestFocus();
                    return;
                } else if(workThreeResponsibilitiesText.isEmpty()){
                    workThreeResponsibilities.setError("Responsibilities required");
                    workThreeResponsibilities.requestFocus();
                    return;
                }

                workThreeText = workThreePositionText + "\n\n" + workThreeCompanyText + "\n\n" +
                        workThreeStartText + " to " + workThreeEndText + "\n\n" + workThreeResponsibilitiesText;
            }

            Call<ResponseBody> call = RetrofitClient
                    .getInstance()
                    .getAPI()
                    .postResume(new ResumePost(getIntent().getLongExtra("id", -1L),
                            getIntent().getStringExtra("description"),
                            getIntent().getStringExtra("educationOne"),
                            getIntent().getStringExtra("educationTwo"),
                            getIntent().getStringExtra("educationThree"),
                            workOneText,
                            workTwoText,
                            workThreeText,
                            getIntent().getStringExtra("skills"),
                            getIntent().getStringExtra("certifications")));


            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    ResponseBody res = response.body();
                    if (res != null){
                        if (res.equals("\"SUCCESS\"")) {
                             Toast.makeText(PostResumeWorkActivity.this, "Successfully registered. Please login", Toast.LENGTH_LONG).show();
                             startActivity(new Intent(PostResumeWorkActivity.this, LoginActivity.class));
                        } else {
                            Toast.makeText(PostResumeWorkActivity.this, "Resume Upload Failed!", Toast.LENGTH_LONG).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(PostResumeWorkActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        });
    }
}
