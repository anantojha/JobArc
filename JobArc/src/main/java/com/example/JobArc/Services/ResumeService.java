package com.example.JobArc.Services;

import com.example.JobArc.Entity.Resume;
import com.example.JobArc.Entity.ResumeMapping;
import com.example.JobArc.Entity.User;
import com.example.JobArc.Enums.Status;
import com.example.JobArc.Repository.ResumeMappingRepository;
import com.example.JobArc.Repository.ResumeRepository;
import com.example.JobArc.RequestModels.ResumeRequest;
import com.example.JobArc.ResponseModels.JobApplicantsResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ResumeService implements IResumeService{

    @Override
    public Resume getResumeByUserId(ResumeRepository resumeRepository, ResumeMappingRepository resumeMappingRepository, long id) {
        Long resumeId =  resumeMappingRepository.findJobseekerResume(id);
        return resumeRepository.getResumeFromId(resumeId);
    }

    @Override
    public Status updateResume(ResumeRepository resumeRepository, ResumeMappingRepository resumeMappingRepository, Resume newResume) {
        Optional<Resume> optionalResume = resumeRepository.findById(newResume.getId());

        if (optionalResume.isPresent()) {
            Resume resume = optionalResume.get();
            resume.setDescription(newResume.getDescription());
            resume.setSkills(newResume.getSkills());
            resume.setCertifications(newResume.getCertifications());
            resume.setEducation_one(newResume.getEducation_one());
            resume.setEducation_two(newResume.getEducation_two());
            resume.setEducation_three(newResume.getEducation_three());
            resume.setWork_one(newResume.getWork_one());
            resume.setWork_two(newResume.getWork_two());
            resume.setWork_three(newResume.getWork_three());
            resumeRepository.save(resume);
            return Status.SUCCESS;
        } else {
            return Status.FAILURE;
        }
    }

    @Override
    public Status createNewResume(ResumeRepository resumeRepository, ResumeMappingRepository resumeMappingRepository, User jobseeker, ResumeRequest newResume){
        Resume created = resumeRepository.save(new Resume(jobseeker.getName(),
                newResume.getDescription(), newResume.getEducationOne(), newResume.getEducationTwo(), newResume.getEducationThree(),
                newResume.getWorkOne(), newResume.getWorkTwo(), newResume.getWorkThree(), newResume.getSkills(), newResume.getCertifications()));
        ResumeMapping resumeMapping = new ResumeMapping(created.getId(), newResume.getJobseekerId());
        resumeMappingRepository.save(resumeMapping);
        return Status.SUCCESS;
    }

    @Override
    public JobApplicantsResponse getResumesFromUserIds(ResumeRepository resumeRepository, ResumeMappingRepository resumeMappingRepository, List<Long> jobseekerIds){
        List<Long> resumeIds = resumeMappingRepository.findAllResumes(jobseekerIds);
        List<Resume> resumes = resumeRepository.findAllById(resumeIds);
        return new JobApplicantsResponse(resumes);
    }
}
