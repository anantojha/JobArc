package com.comp4905.jobarc.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;

public class Resume {

    @SerializedName("id")
    @Expose
    private long resumeId;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("education_one")
    @Expose
    private String educationOne;
    @SerializedName("education_two")
    @Expose
    private String educationTwo;
    @SerializedName("education_three")
    @Expose
    private String educationThree;
    @SerializedName("work_one")
    @Expose
    private String workOne;
    @SerializedName("work_two")
    @Expose
    private String workTwo;
    @SerializedName("work_three")
    @Expose
    private String workThree;
    @SerializedName("skills")
    @Expose
    private String skills;
    @SerializedName("certifications")
    @Expose
    private String certifications;
    @SerializedName("createDate")
    @Expose
    private Timestamp createDate;
    @SerializedName("jobseeker_name")
    @Expose
    private String jobseekerName;

    public Resume(long resumeId, String description, String educationOne, String educationTwo, String educationThree,
                  String workOne, String workTwo, String workThree, String skills, String certifications, Timestamp createDate, String jobseekerName) {
        this.resumeId = resumeId;
        this.description = description;
        this.educationOne = educationOne;
        this.educationTwo = educationTwo;
        this.educationThree = educationThree;
        this.workOne = workOne;
        this.workTwo = workTwo;
        this.workThree = workThree;
        this.skills = skills;
        this.certifications = certifications;
        this.createDate = createDate;
        this.jobseekerName = jobseekerName;
    }

    public long getResumeId() {
        return resumeId;
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


    public void setDescription(String description) {
        this.description = description;
    }

    public void setEducationOne(String educationOne) {
        this.educationOne = educationOne;
    }

    public void setEducationTwo(String educationTwo) {
        this.educationTwo = educationTwo;
    }

    public void setEducationThree(String educationThree) {
        this.educationThree = educationThree;
    }

    public void setWorkOne(String workOne) {
        this.workOne = workOne;
    }

    public void setWorkTwo(String workTwo) {
        this.workTwo = workTwo;
    }

    public void setWorkThree(String workThree) {
        this.workThree = workThree;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public void setCertifications(String certifications) {
        this.certifications = certifications;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public String getSkills() {
        return skills;
    }

    public String getCertifications() {
        return certifications;
    }

    public String getJobseekerName() {
        return jobseekerName;
    }

    public void setJobseekerName(String jobseekerName) {
        this.jobseekerName = jobseekerName;
    }
}
