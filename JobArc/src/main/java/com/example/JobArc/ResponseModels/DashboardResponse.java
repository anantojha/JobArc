package com.example.JobArc.ResponseModels;

import com.example.JobArc.Entity.Job;

import java.util.Collection;

public class DashboardResponse {

    private Collection<Job> jobList;

    public DashboardResponse(Collection<Job> jobList){
        this.jobList = jobList;
    }

    public Collection<Job> getJobList() { return jobList; }
}
