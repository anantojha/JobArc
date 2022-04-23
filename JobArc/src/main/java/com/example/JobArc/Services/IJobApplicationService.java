package com.example.JobArc.Services;

import com.example.JobArc.Enums.Status;
import com.example.JobArc.Repository.JobApplicationRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface IJobApplicationService {

    Status createNewJobApplication(JobApplicationRepository jobApplicationRepository, long jobseekerId, long jobId);

    List<Long> getApplicantIds(JobApplicationRepository jobApplicationRepository, long id);

    List<Long> getApplicantJobIds(JobApplicationRepository jobApplicationRepository, long id);
}
