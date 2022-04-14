package com.example.JobArc.controller;

import com.example.JobArc.Entity.Job;
import com.example.JobArc.Entity.JobMapping;
import com.example.JobArc.Entity.User;
import com.example.JobArc.Enums.AccountType;
import com.example.JobArc.Repository.JobMappingRepository;
import com.example.JobArc.Repository.JobRepository;
import com.example.JobArc.Repository.UserRepository;
import com.example.JobArc.RequestModels.JobRequest;
import com.example.JobArc.RequestModels.LoginRequest;
import com.example.JobArc.ResponseModels.DashboardResponse;
import com.example.JobArc.ResponseModels.LoginResponse;
import com.example.JobArc.ResponseModels.ProfileResponse;
import com.example.JobArc.ResponseModels.Status;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.Collections;
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

    @CrossOrigin()
    @PostMapping("/users/register")
    public Status registerUser(@Valid @RequestBody User newUser) {
        System.out.println("handle POST /register");
        List<User> users = userRepository.findAll();

        for (User user : users) {
            if (user.equals(newUser)) {
                System.out.println("User Already exists!");
                return Status.USER_ALREADY_EXISTS;
            }
        }

        newUser.setPassword(Base64.encodeBase64String(newUser.getPassword().getBytes()));
        userRepository.save(newUser);
        return Status.SUCCESS;
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
    @DeleteMapping("/users/all")
    public Status deleteUsers() {
        userRepository.deleteAll();
        return Status.SUCCESS;
    }
}