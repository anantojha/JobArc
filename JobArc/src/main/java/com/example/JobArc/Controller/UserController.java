package com.example.JobArc.Controller;

import com.example.JobArc.Entity.*;
import com.example.JobArc.Enums.AccountType;
import com.example.JobArc.Repository.*;
import com.example.JobArc.RequestModels.*;
import com.example.JobArc.ResponseModels.*;
import com.example.JobArc.Enums.Status;
import com.example.JobArc.Services.*;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RestController
public class UserController {

    @Autowired
    JobRepository jobRepository;

    @Autowired
    JobMappingRepository jobMappingRepository;

    @Autowired
    JobApplicationRepository jobApplicationRepository;

    @Autowired
    ResumeRepository resumeRepository;

    @Autowired
    ResumeMappingRepository resumeMappingRepository;

    @Autowired
    UserRepository userRepository;

    IUserService userService = new UserService();
    IJobsService jobsService = new JobsService();
    IResumeService resumeService = new ResumeService();
    IJobApplicationService jobApplicationService = new JobApplicationService();

    @CrossOrigin()
    @PostMapping("/users/register")
    public RegistrationResponse registerUser(@Valid @RequestBody User newUser) {
        System.out.println("handle POST /register");
        return userService.registerUser(userRepository, newUser);
    }


    @CrossOrigin()
    @PostMapping("/users/login")
    public LoginResponse loginUser(@Valid @RequestBody LoginRequest request) {
        System.out.println("handle POST /login");
        return userService.loginUser(userRepository, request);
    }


    @CrossOrigin()
    @GetMapping("/users/dashboard")
    public DashboardResponse dashboardDetails(@RequestParam long id) {
        System.out.println("handle GET /dashboard");

        User user = userService.getUserById(userRepository, id);

        if (user.getAccountType().equals(AccountType.employer.toString())) {
            return jobsService.getEmployerDashboard(jobRepository, jobMappingRepository, user);
        } else {
            return jobsService.getJobseekerDashboard(jobRepository, jobMappingRepository);
        }
    }


    @CrossOrigin()
    @GetMapping("/users/profile")
    public ProfileResponse profileDetails(@RequestParam long id) {
        System.out.println("handle GET /profile");
        return userService.getUserProfile(userRepository, id);
    }


    @CrossOrigin()
    @PostMapping("/users/post_job")
    public Status postJob(@Valid @RequestBody JobRequest newJob) {
        System.out.println("handle POST /post_job");

        User user = userService.getUserById(userRepository, newJob.getEmployerId());

        if(user == null) {
            return Status.FAILURE;
        } else {
            return jobsService.createNewJob(jobRepository, jobMappingRepository, user, newJob);
        }
    }


    @CrossOrigin()
    @GetMapping("/users/job")
    public Job jobDetails(@RequestParam long id) {
        System.out.println("handle GET /job");
        return jobsService.getJobById(jobRepository, jobMappingRepository, id);
    }


    @CrossOrigin()
    @GetMapping("/users/resume")
    public  Resume resumeDetails(@RequestParam long id) {
        System.out.println("handle GET /resume");

        User user = userService.getUserById(userRepository, id);

        if(user == null){
            return null;
        } else {
            return resumeService.getResumeByUserId(resumeRepository, resumeMappingRepository, user.getId());
        }
    }


    @CrossOrigin()
    @PostMapping("/users/post_resume")
    public Status postResume(@Valid @RequestBody ResumeRequest newResume) {
        System.out.println("handle POST /post_resume");

        User user = userService.getUserById(userRepository, newResume.getJobseekerId());

        if(user == null){
            return Status.FAILURE;
        } else {
            return resumeService.createNewResume(resumeRepository, resumeMappingRepository, user, newResume);
        }
    }


    @CrossOrigin()
    @PostMapping("/users/update_resume")
    public Status updateResume(@Valid @RequestBody Resume newResume) {
        System.out.println("handle POST /update_resume");
        return resumeService.updateResume(resumeRepository, resumeMappingRepository, newResume);
    }


    @CrossOrigin()
    @PostMapping("/users/post_application")
    public Status postApplication(@Valid @RequestBody JobApplicationRequest applicationRequest) {
        System.out.println("handle POST /post_application");

        User user = userService.getUserById(userRepository, applicationRequest.getJobseekerId());
        Job job = jobsService.getJobById(jobRepository, jobMappingRepository, applicationRequest.getJobId());

        if(user != null && job != null){
            return jobApplicationService.createNewJobApplication(jobApplicationRepository, user.getId(), job.getId());
        } else {
            return Status.FAILURE;
        }
    }


    @CrossOrigin()
    @GetMapping("/users/applicants")
    public JobApplicantsResponse applicantsDetails(@RequestParam long id) {
        System.out.println("handle GET /applicants");

        Job job = jobsService.getJobById(jobRepository, jobMappingRepository, id);

        if(job == null){
            return null;
        } else {
            return resumeService.getResumesFromUserIds(resumeRepository, resumeMappingRepository, jobApplicationService.getApplicantIds(jobApplicationRepository, job.getId()));
        }
    }


    @CrossOrigin()
    @GetMapping("/users/applicant_jobs")
    public ApplicantJobsResponse applicantJobDetails(@RequestParam long id) {
        System.out.println("handle GET /applicant_jobs");

        User user = userService.getUserById(userRepository, id);

        if(user == null){
            return null;
        } else {
            return jobsService.getJobsByIds(jobRepository, jobMappingRepository, jobApplicationService.getApplicantJobIds(jobApplicationRepository, user.getId()));
        }
    }


    @CrossOrigin()
    @GetMapping("/users/search_jobs")
    public ApplicantJobsResponse searchJobDetails(@RequestParam String keyword) {
        System.out.println("handle GET /search_jobs");

        return jobsService.searchJobs(jobRepository, jobMappingRepository, keyword);
    }

}