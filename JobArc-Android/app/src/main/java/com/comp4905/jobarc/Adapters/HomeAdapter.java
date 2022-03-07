package com.comp4905.jobarc.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.comp4905.jobarc.Activity.JobseekerJobViewActivity;
import com.comp4905.jobarc.Models.Job;
import com.comp4905.jobarc.R;

import java.sql.Timestamp;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "Adapter Home: ";
    private Context context;
    private List<Job> jobList;

    public HomeAdapter(Context context, List<Job> jobList){
        this.context = context;
        this.jobList = jobList;
    }

    class JobHolder extends RecyclerView.ViewHolder {

        TextView jobId;
        TextView jobTitle;
        TextView jobCreateDate;

        public JobHolder(@NonNull View itemView) {
            super(itemView);
            jobId = itemView.findViewById(R.id.JobId);
            jobTitle = itemView.findViewById(R.id.JobTitle);
            jobCreateDate = itemView.findViewById(R.id.JobCreateDate);
        }

        public void setdata(Job data) {
            Long getId = data.getJobId();
            String getTitle = data.getTitle();
            String getDescription = data.getDescription();
            Timestamp getCreateDate = data.getCreateDate();

            itemView.setOnClickListener(view -> {
                Intent intent = new Intent(view.getContext(), JobseekerJobViewActivity.class);
                intent.putExtra("id", getId);
                intent.putExtra("title", getTitle);
                intent.putExtra("description", getDescription);
                intent.putExtra("createDate", getCreateDate.toString());
                view.getContext().startActivity(intent);
            });

            jobId.setText(getId.toString());
            jobTitle.setText(getTitle);
            jobCreateDate.setText(getCreateDate.toString());
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_home, parent, false);
        return new JobHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Job job = jobList.get(position);
        JobHolder youtubeHolder = (JobHolder) holder;
        youtubeHolder.setdata(job);
    }

    @Override
    public int getItemCount() {
        return jobList.size();
    }
}
