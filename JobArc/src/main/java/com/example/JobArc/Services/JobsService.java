package com.example.JobArc.Services;

import com.example.JobArc.Entity.Job;
import com.example.JobArc.Entity.JobMapping;
import com.example.JobArc.Entity.User;
import com.example.JobArc.Enums.Status;
import com.example.JobArc.Repository.JobApplicationRepository;
import com.example.JobArc.Repository.JobMappingRepository;
import com.example.JobArc.Repository.JobRepository;
import com.example.JobArc.RequestModels.JobRequest;
import com.example.JobArc.ResponseModels.ApplicantJobsResponse;
import com.example.JobArc.ResponseModels.DashboardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class JobsService implements IJobsService {

    @Autowired
    JobRepository jobRepository;

    @Autowired
    JobMappingRepository jobMappingRepository;

    @Override
    public DashboardResponse getEmployerDashboard(User user){
        List<Long> jobIds =  jobMappingRepository.findAllEmployerJobs(user.getId());
        List<Job> jobs = jobRepository.findAllEmployerJobs(jobIds);
        return new DashboardResponse(jobs);
    }

    @Override
    public DashboardResponse getJobseekerDashboard(){
        List<Job> jobs = jobRepository.findRecentJobs();
        return new DashboardResponse(jobs);
    }

    @Override
    public Status createNewJob(User employer, JobRequest newJob){
        Job created = jobRepository.save(new Job(employer.getName(), newJob.getTitle(), newJob.getDescription(), newJob.getLocation(), newJob.getJobType()));
        JobMapping jobMapping = new JobMapping(created.getId(), newJob.getEmployerId());
        jobMappingRepository.save(jobMapping);
        return Status.SUCCESS;
    }

    @Override
    public Job getJobById(long id){
        Optional<Job> optionalJob = jobRepository.findById(id);

        if (optionalJob.isPresent()){
            Job job = optionalJob.get();
            return job;
        }
        return null;
    }

    @Override
    public ApplicantJobsResponse getJobsByIds(List<Long> jobIds){
        List<Job> jobs = jobRepository.findAllById(jobIds);
        return new ApplicantJobsResponse(jobs);
    }

    @Override
    public ApplicantJobsResponse searchJobs(String keyword){
        List<Long> jobIds = jobRepository.searchForKeywordInJobs(keyword);

        if (!jobIds.isEmpty()) {
            List<Job> jobs = jobRepository.findAllById(jobIds);
            return new ApplicantJobsResponse(jobs);
        }
        return new ApplicantJobsResponse(new ArrayList<>());
    }
}
