package com.example.JobArc.controller;

import com.example.JobArc.Entity.*;
import com.example.JobArc.Enums.AccountType;
import com.example.JobArc.Repository.*;
import com.example.JobArc.RequestModels.JobRequest;
import com.example.JobArc.RequestModels.LoginRequest;
import com.example.JobArc.RequestModels.ResumeRequest;
import com.example.JobArc.RequestModels.ResumeUpdateRequest;
import com.example.JobArc.ResponseModels.DashboardResponse;
import com.example.JobArc.ResponseModels.LoginResponse;
import com.example.JobArc.ResponseModels.ProfileResponse;
import com.example.JobArc.Enums.Status;
import com.example.JobArc.ResponseModels.RegistrationResponse;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Component
@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    JobRepository jobRepository;
    @Autowired
    JobMappingRepository jobMappingRepository;
    @Autowired
    ResumeRepository resumeRepository;
    @Autowired
    ResumeMappingRepository resumeMappingRepository;

    @CrossOrigin()
    @PostMapping("/users/register")
    public RegistrationResponse registerUser(@Valid @RequestBody User newUser) {
        System.out.println("handle POST /register");
        List<User> users = userRepository.findAll();

        for (User user : users) {
            if (user.getUsername().equals(newUser.getUsername())) {
                System.out.println("User Already exists!");
                return new RegistrationResponse(Status.USER_ALREADY_EXISTS.toString(), -1, null);
            }
        }

        newUser.setPassword(Base64.encodeBase64String(newUser.getPassword().getBytes()));
        User user = userRepository.save(newUser);
        return new RegistrationResponse(Status.SUCCESS.toString(), user.getId(), user.getName());
    }

    @CrossOrigin()
    @PostMapping("/users/login")
    public LoginResponse loginUser(@Valid @RequestBody LoginRequest request) {
        System.out.println("handle POST /login");
        List<User> users = userRepository.findAll();

        for (User user : users) {
            if (user.getUsername().equals(request.getUsername())) {
                // userRepository.save(other);
                String decodedPassword = new String(Base64.decodeBase64(user.getPassword().getBytes()));
                if(request.getPassword().equals(decodedPassword)){
                    System.out.println("AUTHENTICATED USER" + user.getName());
                    return new LoginResponse(user.getName(), user.getUsername(), user.getId(), user.getAccountType());
                }
            }
        }
        return new LoginResponse("FAILURE", null, -1, null);
    }

    @CrossOrigin()
    @GetMapping("/users/dashboard")
    public DashboardResponse dashboardDetails(@RequestParam long id) {
        System.out.println("handle GET /dashboard");
        Optional<User> optionalUser = userRepository.findById(id);
        
        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            if (user.getAccountType().equals(AccountType.employer.toString())) {
                List<Long> jobIds =  jobMappingRepository.findAllEmployerJobs(id);
                List<Job> jobs = jobRepository.findAllEmployerJobs(jobIds);
                return new DashboardResponse(jobs);
            } else {
                List<Job> jobs = jobRepository.findRecentJobs();
                return new DashboardResponse(jobs);
            }
        }

        return null;
    }

    @CrossOrigin()
    @GetMapping("/users/profile")
    public ProfileResponse profileDetails(@RequestParam long id) {
        System.out.println("handle GET /profile");
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            return new ProfileResponse(user.getName(), user.getUsername(), user.getAccountType(), user.getId());
        }
        return null;
    }

    @CrossOrigin()
    @PostMapping("/users/post_job")
    public Status postJob(@Valid @RequestBody JobRequest newJob) {
        System.out.println("handle POST /post_job");
        Optional<User> optionalUser = userRepository.findById(newJob.getEmployerId());

        if (optionalUser.isPresent()) {
            User employer = optionalUser.get();
            Job created = jobRepository.save(new Job(employer.getName(), newJob.getTitle(), newJob.getDescription(), newJob.getLocation(), newJob.getJobType()));
            JobMapping jobMapping = new JobMapping(created.getId(), newJob.getEmployerId());
            jobMappingRepository.save(jobMapping);
            return Status.SUCCESS;
        } else {
            return Status.FAILURE;
        }
    }

    @CrossOrigin()
    @GetMapping("/users/job")
    public Job jobDetails(@RequestParam long id) {
        System.out.println("handle GET /job");
        Optional<Job> optionalJob = jobRepository.findById(id);

        if (optionalJob.isPresent()){
            Job job = optionalJob.get();
            return job;
        }
        return null;
    }

    @CrossOrigin()
    @GetMapping("/users/resume")
    public  Resume resumeDetails(@RequestParam long id) {
        System.out.println("handle GET /resume");
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()){
            User user = optionalUser.get();

            Long resumeId =  resumeMappingRepository.findJobseekerResume(user.getId());
            Resume resume = resumeRepository.getResumeFromId(resumeId);

            return resume;
        }
        return null;
    }

    @CrossOrigin()
    @PostMapping("/users/post_resume")
    public Status postResume(@Valid @RequestBody ResumeRequest newResume) {
        System.out.println("handle POST /post_resume");
        Optional<User> optionalUser = userRepository.findById(newResume.getJobseekerId());

        if (optionalUser.isPresent()) {
            User jobseeker = optionalUser.get();
            Resume created = resumeRepository.save(new Resume(jobseeker.getName(),
                    newResume.getDescription(), newResume.getEducationOne(), newResume.getEducationTwo(), newResume.getEducationThree(),
                    newResume.getWorkOne(), newResume.getWorkTwo(), newResume.getWorkThree(), newResume.getSkills(), newResume.getCertifications()));
            ResumeMapping resumeMapping = new ResumeMapping(created.getId(), newResume.getJobseekerId());
            resumeMappingRepository.save(resumeMapping);
            return Status.SUCCESS;
        } else {
            return Status.FAILURE;
        }
    }

    @CrossOrigin()
    @PostMapping("/users/update_resume")
    public Status updateResume(@Valid @RequestBody Resume newResume) {
        System.out.println("handle POST /update_resume");
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

    /*
        @CrossOrigin()
        @DeleteMapping("/users/all")
        public Status deleteUsers() {
            userRepository.deleteAll();
            return Status.SUCCESS;
        }
    */
}