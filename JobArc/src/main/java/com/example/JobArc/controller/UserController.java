package com.example.JobArc.controller;

import com.example.JobArc.Entity.Job;
import com.example.JobArc.Entity.JobMapping;
import com.example.JobArc.Entity.User;
import com.example.JobArc.Enums.AccountType;
import com.example.JobArc.Repository.JobMappingRepository;
import com.example.JobArc.Repository.JobRepository;
import com.example.JobArc.Repository.UserRepository;
import com.example.JobArc.RequestModels.JobRequest;
import com.example.JobArc.ResponseModels.DashboardResponse;
import com.example.JobArc.ResponseModels.LoginResponse;
import com.example.JobArc.ResponseModels.Status;
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
        List<User> users = userRepository.findAll();

        for (User user : users) {
            if (user.equals(newUser)) {
                System.out.println("User Already exists!");
                return Status.USER_ALREADY_EXISTS;
            }
        }

        userRepository.save(newUser);
        return Status.SUCCESS;
    }

    @CrossOrigin()
    @PostMapping("/users/login")
    public LoginResponse loginUser(@Valid @RequestBody User user) {
        List<User> users = userRepository.findAll();

        for (User other : users) {
            if (other.equals(user)) {
                userRepository.save(other);
                return new LoginResponse(Status.SUCCESS, other.getUsername(), other.getId(), other.getAccountType());
            }
        }

        return new LoginResponse(Status.FAILURE, null, -1, null);
    }

    @CrossOrigin()
    @GetMapping("/users/dashboard")
    public DashboardResponse dashboardDetails(@RequestParam long id) {
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
    @PostMapping("/users/post_job")
    public Status postJob(@Valid @RequestBody JobRequest newJob) {
        Job created = jobRepository.save(new Job(newJob.getTitle(), newJob.getDescription()));
        JobMapping jobMapping = new JobMapping(created.getId(), newJob.getEmployerId());
        jobMappingRepository.save(jobMapping);
        return Status.SUCCESS;
    }

    @CrossOrigin()
    @DeleteMapping("/users/all")
    public Status deleteUsers() {
        userRepository.deleteAll();
        return Status.SUCCESS;
    }
}