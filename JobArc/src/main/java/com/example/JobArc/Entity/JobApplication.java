package com.example.JobArc.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "job_applications")
public class JobApplication {

    private @Id @GeneratedValue @Column(name="id") long id;
    private @NotBlank @Column(name="jobseeker_id") long jobseekerId;
    private @NotBlank @Column(name="job_id") long jobId;

    public JobApplication(){}

    public JobApplication(long jobseekerId, long jobId){
        this.jobseekerId = jobseekerId;
        this.jobId = jobId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
