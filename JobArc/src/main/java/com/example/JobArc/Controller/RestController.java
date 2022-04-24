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
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    IUserService userService;
    @Autowired
    IJobsService jobsService;
    @Autowired
    IResumeService resumeService;
    @Autowired
    IJobApplicationService jobApplicationService;

    @CrossOrigin()
    @PostMapping("/jobarc/register")
    public RegistrationResponse registerUser(@Valid @RequestBody User newUser) {
        System.out.println("handle POST /register");
        return userService.registerUser(newUser);
    }


    @CrossOrigin()
    @PostMapping("/jobarc/login")
    public LoginResponse loginUser(@Valid @RequestBody LoginRequest request) {
        System.out.println("handle POST /login");
        return userService.loginUser(request);
    }


    @CrossOrigin()
    @GetMapping("/jobarc/dashboard")
    public DashboardResponse dashboardDetails(@RequestParam long id) {
        System.out.println("handle GET /dashboard");

        User user = userService.getUserById(id);

        if (user.getAccountType().equals(AccountType.employer.toString())) {
            return jobsService.getEmployerDashboard(user);
        } else {
            return jobsService.getJobseekerDashboard();
        }
    }


    @CrossOrigin()
    @GetMapping("/jobarc/profile")
    public ProfileResponse profileDetails(@RequestParam long id) {
        System.out.println("handle GET /profile");
        return userService.getUserProfile(id);
    }


    @CrossOrigin()
    @PostMapping("/jobarc/post_job")
    public Status postJob(@Valid @RequestBody JobRequest newJob) {
        System.out.println("handle POST /post_job");

        User user = userService.getUserById(newJob.getEmployerId());

        if(user == null) {
            return Status.FAILURE;
        } else {
            return jobsService.createNewJob(user, newJob);
        }
    }


    @CrossOrigin()
    @GetMapping("/jobarc/job")
    public Job jobDetails(@RequestParam long id) {
        System.out.println("handle GET /job");
        return jobsService.getJobById(id);
    }


    @CrossOrigin()
    @GetMapping("/jobarc/resume")
    public  Resume resumeDetails(@RequestParam long id) {
        System.out.println("handle GET /resume");

        User user = userService.getUserById(id);

        if(user == null){
            return null;
        } else {
            return resumeService.getResumeByUserId(user.getId());
        }
    }


    @CrossOrigin()
    @PostMapping("/jobarc/post_resume")
    public Status postResume(@Valid @RequestBody ResumeRequest newResume) {
        System.out.println("handle POST /post_resume");

        User user = userService.getUserById(newResume.getJobseekerId());

        if(user == null){
            return Status.FAILURE;
        } else {
            return resumeService.createNewResume(user, newResume);
        }
    }


    @CrossOrigin()
    @PostMapping("/jobarc/update_resume")
    public Status updateResume(@Valid @RequestBody Resume newResume) {
        System.out.println("handle POST /update_resume");
        return resumeService.updateResume(newResume);
    }


    @CrossOrigin()
    @PostMapping("/jobarc/post_application")
    public Status postApplication(@Valid @RequestBody JobApplicationRequest applicationRequest) {
        System.out.println("handle POST /post_application");

        User user = userService.getUserById(applicationRequest.getJobseekerId());
        Job job = jobsService.getJobById(applicationRequest.getJobId());

        if(user != null && job != null){
            return jobApplicationService.createNewJobApplication(user.getId(), job.getId());
        } else {
            return Status.FAILURE;
        }
    }


    @CrossOrigin()
    @GetMapping("/jobarc/applicants")
    public JobApplicantsResponse applicantsDetails(@RequestParam long id) {
        System.out.println("handle GET /applicants");

        Job job = jobsService.getJobById(id);

        if(job == null){
            return null;
        } else {
            return resumeService.getResumesFromUserIds(jobApplicationService.getApplicantIds(job.getId()));
        }
    }


    @CrossOrigin()
    @GetMapping("/jobarc/applicant_jobs")
    public ApplicantJobsResponse applicantJobDetails(@RequestParam long id) {
        System.out.println("handle GET /applicant_jobs");

        User user = userService.getUserById(id);

        if(user == null){
            return null;
        } else {
            return jobsService.getJobsByIds( jobApplicationService.getApplicantJobIds(user.getId()));
        }
    }


    @CrossOrigin()
    @GetMapping("/jobarc/search_jobs")
    public ApplicantJobsResponse searchJobDetails(@RequestParam String keyword) {
        System.out.println("handle GET /search_jobs");

        return jobsService.searchJobs(keyword);
    }

}