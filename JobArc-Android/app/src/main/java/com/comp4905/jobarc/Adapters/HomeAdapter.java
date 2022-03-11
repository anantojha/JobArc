package com.comp4905.jobarc.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.comp4905.jobarc.Activity.EmployerJobViewActivity;
import com.comp4905.jobarc.Activity.JobseekerJobViewActivity;
import com.comp4905.jobarc.Models.Job;
import com.comp4905.jobarc.R;

import java.sql.Timestamp;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "Adapter Home: ";
    private Context context;
    private List<Job> jobList;
    private long userId;
    private String username;
    private String accountType;

    public HomeAdapter(Context context, List<Job> jobList, long userId, String username, String accountType){
        this.context = context;
        this.jobList = jobList;
        this.userId = userId;
        this.username = username;
        this.accountType = accountType;
    }

    class JobHolder extends RecyclerView.ViewHolder {

        TextView jobEmployerName;
        TextView jobTitle;
        TextView jobCreateDate;
        TextView jobLocation;

        public JobHolder(@NonNull View itemView) {
            super(itemView);
            jobEmployerName = itemView.findViewById(R.id.JobRowEmployer);
            jobTitle = itemView.findViewById(R.id.JobTitle);
            jobCreateDate = itemView.findViewById(R.id.JobCreateDate);
            jobLocation = itemView.findViewById(R.id.jobRowLocation);
        }

        public void setdata(Job data) {
            Long getId = data.getJobId();
            String getTitle = data.getTitle();
            String getDescription = data.getDescription();
            Timestamp getCreateDate = data.getCreateDate();
            String getLocation = data.getLocation();
            String getJobType = data.getJobType();
            String getEmplyerName = data.getEmployerName();

            itemView.setOnClickListener(view -> {
                if(accountType.equals("employer")) {
                    Intent intent = new Intent(view.getContext(), EmployerJobViewActivity.class);
                    intent.putExtra("userId", userId);
                    intent.putExtra("username", getEmplyerName);
                    intent.putExtra("accountType", accountType);
                    intent.putExtra("id", getId);
                    intent.putExtra("title", getTitle);
                    intent.putExtra("description", getDescription);
                    intent.putExtra("createDate", getCreateDate.toString());
                    intent.putExtra("location", getLocation);
                    intent.putExtra("type", getJobType);
                    view.getContext().startActivity(intent);
                } else {
                    Intent intent = new Intent(view.getContext(), JobseekerJobViewActivity.class);
                    intent.putExtra("userId", userId);
                    intent.putExtra("username", getEmplyerName);
                    intent.putExtra("accountType", accountType);
                    intent.putExtra("id", getId);
                    intent.putExtra("title", getTitle);
                    intent.putExtra("description", getDescription);
                    intent.putExtra("createDate", getCreateDate.toString());
                    intent.putExtra("location", getLocation);
                    intent.putExtra("type", getJobType);
                    view.getContext().startActivity(intent);
                }
            });

            jobEmployerName.setText(data.getEmployerName());
            jobTitle.setText(getTitle);
            jobCreateDate.setText(getCreateDate.toString());
            jobLocation.setText(getLocation);
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
