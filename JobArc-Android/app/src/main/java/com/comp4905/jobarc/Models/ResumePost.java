package com.comp4905.jobarc.Models;

public class ResumePost {

    private long jobseekerId;
    private String description;
    private String educationOne;
    private String educationTwo;
    private String educationThree;
    private String workOne;
    private String workTwo;
    private String workThree;
    private String skills;
    private String certifications;

    public ResumePost(long jobseekerId, String description, String educationOne, String educationTwo, String educationThree,
                         String workOne, String workTwo, String workThree, String skills, String certifications) {
        this.jobseekerId = jobseekerId;
        this.description = description;
        this.educationOne = educationOne;
        this.educationTwo = educationTwo;
        this.educationThree = educationThree;
        this.workOne = workOne;
        this.workTwo = workTwo;
        this.workThree = workThree;
        this.skills = skills;
        this.certifications = certifications;
    }

    public long getJobseekerId() {
        return jobseekerId;
    }

    public String getDescription() {
        return description;
    }

    public String getEducationOne() {
        return educationOne;
    }

    public String getEducationTwo() {
        return educationTwo;
    }

    public String getEducationThree() {
        return educationThree;
    }

    public String getWorkOne() {
        return workOne;
    }

    public String getWorkTwo() {
        return workTwo;
    }

    public String getWorkThree() {
        return workThree;
    }

    public String getSkills() {
        return skills;
    }

    public String getCertifications() {
        return certifications;
    }
}
