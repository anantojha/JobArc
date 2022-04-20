package com.comp4905.jobarc.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.comp4905.jobarc.Activity.ApplicantViewActivity;
import com.comp4905.jobarc.Activity.EmployerJobViewActivity;
import com.comp4905.jobarc.Activity.JobseekerJobViewActivity;
import com.comp4905.jobarc.Activity.ViewJobApplicantsActivity;
import com.comp4905.jobarc.Models.Job;
import com.comp4905.jobarc.Models.Resume;
import com.comp4905.jobarc.R;

import java.sql.Timestamp;
import java.util.List;

public class ApplicantAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "Adapter Home: ";
    private Context context;
    private List<Resume> resumeList;
    private long userId;
    private String username;
    private String accountType;

    public ApplicantAdapter(Context context, List<Resume> resumeList, long userId, String username, String accountType){
        this.context = context;
        this.resumeList = resumeList;
        this.userId = userId;
        this.username = username;
        this.accountType = accountType;
    }

    class ResumeHolder extends RecyclerView.ViewHolder {

        TextView jobseekerNameView;
        TextView resumeCreateDate;

        public ResumeHolder(@NonNull View itemView) {
            super(itemView);
            jobseekerNameView = itemView.findViewById(R.id.applicantRowJobseekerName);
            resumeCreateDate = itemView.findViewById(R.id.applicantRowCreateDate);
        }

        public void setData(Resume data) {

            Long getId = data.getResumeId();
            String jobseekerName = data.getJobseekerName();
            String getDescription = data.getDescription();
            Timestamp getCreateDate = data.getCreateDate();
            String eduOne = data.getEducationOne();
            String eduTwo = data.getEducationTwo();
            String eduThree = data.getEducationThree();
            String workOne = data.getWorkOne();
            String workTwo = data.getWorkTwo();
            String workThree = data.getWorkThree();
            String skills = data.getSkills();
            String certifications = data.getCertifications();

            itemView.setOnClickListener(view -> {
                Intent intent = new Intent(view.getContext(), ApplicantViewActivity.class);
                intent.putExtra("userId", userId);
                intent.putExtra("username", username);
                intent.putExtra("accountType", accountType);
                intent.putExtra("resumeId", getId);
                intent.putExtra("jobseekerName", jobseekerName);
                intent.putExtra("description", getDescription);
                intent.putExtra("createDate", getCreateDate.toString());
                intent.putExtra("eduOne", eduOne);
                intent.putExtra("eduTwo", eduTwo);
                intent.putExtra("eduThree", eduThree);
                intent.putExtra("workOne", workOne);
                intent.putExtra("workTwo", workTwo);
                intent.putExtra("workThree", workThree);
                intent.putExtra("skills", skills);
                intent.putExtra("certifications", certifications);
                view.getContext().startActivity(intent);
            });

            jobseekerNameView.setText(jobseekerName);
            resumeCreateDate.setText(getCreateDate.toString());
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.row_applicant, parent, false);
        return new ResumeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Resume resume = resumeList.get(position);
        ResumeHolder resumeHolder = (ResumeHolder) holder;
        resumeHolder.setData(resume);
    }

    @Override
    public int getItemCount() {
        return resumeList.size();
    }
}
