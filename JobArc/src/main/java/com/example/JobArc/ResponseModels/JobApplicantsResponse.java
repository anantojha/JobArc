package com.example.JobArc.ResponseModels;


import com.example.JobArc.Entity.Resume;

import java.util.Collection;

public class JobApplicantsResponse {
    private Collection<Resume> resumeList;

    public JobApplicantsResponse(Collection<Resume> resumeList){
        this.resumeList = resumeList;
    }

    public Collection<Resume> getResumeList() { return resumeList; }
}
