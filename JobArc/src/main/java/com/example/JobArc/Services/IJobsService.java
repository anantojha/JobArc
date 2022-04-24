package com.example.JobArc.Services;

import com.example.JobArc.Entity.Job;
import com.example.JobArc.Entity.User;
import com.example.JobArc.Enums.Status;
import com.example.JobArc.Repository.JobMappingRepository;
import com.example.JobArc.Repository.JobRepository;
import com.example.JobArc.RequestModels.JobRequest;
import com.example.JobArc.ResponseModels.ApplicantJobsResponse;
import com.example.JobArc.ResponseModels.DashboardResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IJobsService {

    DashboardResponse getEmployerDashboard(User user);

    DashboardResponse getJobseekerDashboard();

    Status createNewJob(User employer, JobRequest newJob);

    Job getJobById(long id);

    ApplicantJobsResponse getJobsByIds(List<Long> jobIds);

    ApplicantJobsResponse searchJobs(String keyword);
}
