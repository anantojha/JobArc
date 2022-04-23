package com.example.JobArc.Services;

import com.example.JobArc.Entity.JobApplication;
import com.example.JobArc.Enums.Status;
import com.example.JobArc.Repository.JobApplicationRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JobApplicationService implements IJobApplicationService{

    @Override
    public Status createNewJobApplication(JobApplicationRepository jobApplicationRepository, long jobseekerId, long jobId){

        List<Long> found = jobApplicationRepository.ckeckIfApplicationExists(jobseekerId, jobId);

        if(found.isEmpty()){
            jobApplicationRepository.save(new JobApplication(jobseekerId, jobId));
            return Status.SUCCESS;
        } else {
            return Status.FAILURE;
        }
    }

    @Override
    public List<Long> getApplicantIds(JobApplicationRepository jobApplicationRepository, long id){
        return jobApplicationRepository.findAllApplicants(id);
    }

    @Override
    public List<Long> getApplicantJobIds(JobApplicationRepository jobApplicationRepository, long id){
        return jobApplicationRepository.findAllApplicantJobs(id);
    }


}
