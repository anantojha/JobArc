package com.example.JobArc.Services;

import com.example.JobArc.Entity.Resume;
import com.example.JobArc.Entity.User;
import com.example.JobArc.Enums.Status;
import com.example.JobArc.Repository.ResumeMappingRepository;
import com.example.JobArc.Repository.ResumeRepository;
import com.example.JobArc.RequestModels.ResumeRequest;
import com.example.JobArc.ResponseModels.JobApplicantsResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IResumeService {

    Resume getResumeByUserId(long id);

    Status createNewResume(User jobseeker, ResumeRequest newResume);

    Status updateResume(Resume newResume);

    JobApplicantsResponse getResumesFromUserIds(List<Long> jobseekerIds);
}
