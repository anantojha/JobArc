package com.example.JobArc.RequestModels;

public class JobApplicationRequest {

    long jobseekerId;
    long jobId;

    public JobApplicationRequest(long jobseekerId, long jobId){
        this.jobseekerId = jobseekerId;
        this.jobId = jobId;
    }

    public long getJobseekerId() {
        return jobseekerId;
    }

    public void setJobseekerId(long jobseekerId) {
        this.jobseekerId = jobseekerId;
    }

    public long getJobId() {
        return jobId;
    }

    public void setJobId(long jobId) {
        this.jobId = jobId;
    }
}
