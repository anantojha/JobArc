package com.comp4905.jobarc.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JobApplicationRequest {

    @SerializedName("jobseekerId")
    @Expose
    long jobseekerId;
    @SerializedName("jobId")
    @Expose
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
