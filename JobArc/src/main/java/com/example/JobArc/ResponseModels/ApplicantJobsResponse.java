package com.example.JobArc.ResponseModels;

import com.example.JobArc.Entity.Job;
import java.util.Collection;

public class ApplicantJobsResponse {
    private Collection<Job> jobList;

    public ApplicantJobsResponse(Collection<Job> resumeList){
        this.jobList = resumeList;
    }

    public Collection<Job> getJobList() { return jobList; }
}
