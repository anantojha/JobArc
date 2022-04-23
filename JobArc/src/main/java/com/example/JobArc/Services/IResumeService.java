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

    Resume getResumeByUserId(ResumeRepository resumeRepository, ResumeMappingRepository resumeMappingRepository, long id);

    Status createNewResume(ResumeRepository resumeRepository, ResumeMappingRepository resumeMappingRepository, User jobseeker, ResumeRequest newResume);

    Status updateResume(ResumeRepository resumeRepository, ResumeMappingRepository resumeMappingRepository, Resume newResume);

    JobApplicantsResponse getResumesFromUserIds(ResumeRepository resumeRepository, ResumeMappingRepository resumeMappingRepository, List<Long> jobseekerIds);
}
