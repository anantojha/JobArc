package com.example.JobArc.Services;

import com.example.JobArc.Entity.JobApplication;
import com.example.JobArc.Enums.Status;
import com.example.JobArc.Repository.JobApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JobApplicationService implements IJobApplicationService {

    @Autowired
    JobApplicationRepository jobApplicationRepository;

    @Override
    public Status createNewJobApplication(long jobseekerId, long jobId){

        List<Long> found = jobApplicationRepository.ckeckIfApplicationExists(jobseekerId, jobId);

        if(found.isEmpty()){
            jobApplicationRepository.save(new JobApplication(jobseekerId, jobId));
            return Status.SUCCESS;
        } else {
            return Status.FAILURE;
        }
    }

    @Override
    public List<Long> getApplicantIds(long id){
        return jobApplicationRepository.findAllApplicants(id);
    }

    @Override
    public List<Long> getApplicantJobIds(long id){
        return jobApplicationRepository.findAllApplicantJobs(id);
    }


}
