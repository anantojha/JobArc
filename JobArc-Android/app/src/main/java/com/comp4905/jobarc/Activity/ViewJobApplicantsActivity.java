package com.comp4905.jobarc.Activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.comp4905.jobarc.Adapters.ApplicantAdapter;
import com.comp4905.jobarc.Models.ApplicantsModel;
import com.comp4905.jobarc.Models.Resume;
import com.comp4905.jobarc.R;
import com.comp4905.jobarc.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewJobApplicantsActivity extends AppCompatActivity {


    private ApplicantAdapter adapterApplicants;
    private LinearLayoutManager manager;
    private List<Resume> resumeList = new ArrayList<>();

    private long userId;
    private String username;
    private String accountType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_applicants);

        username = getIntent().getStringExtra("username");
        userId = getIntent().getLongExtra("id", -1L);
        accountType = getIntent().getStringExtra("accountType");

        RecyclerView rv = findViewById(R.id.viewApplicantRecycler);

        adapterApplicants = new ApplicantAdapter(ViewJobApplicantsActivity.this, resumeList, userId, username, accountType);
        manager = new LinearLayoutManager(ViewJobApplicantsActivity.this);
        rv.setAdapter(adapterApplicants);
        rv.setLayoutManager(manager);

        loadAllApplicants(getIntent().getLongExtra("jobId", -1L));
    }

    private void loadAllApplicants(long id){
        Call<ApplicantsModel> call = RetrofitClient
                .getInstance()
                .getAPI()
                .getApplicantsDetails(id);

        call.enqueue(new Callback<ApplicantsModel>() {
            @Override
            public void onResponse(Call<ApplicantsModel> call, Response<ApplicantsModel> response) {
                if(response.errorBody() != null){
                    Log.w("ViewJobApplicants", "onResponse: " + response.errorBody());
                } else {
                    ApplicantsModel am = response.body();
                    if(!am.getItems().isEmpty()) {
                        resumeList.addAll(am.getItems());
                        adapterApplicants.notifyDataSetChanged();
                    } else {
                        Toast.makeText(ViewJobApplicantsActivity.this, "No Applicants for this Job.", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ApplicantsModel> call, Throwable t) {
                Toast.makeText(ViewJobApplicantsActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
