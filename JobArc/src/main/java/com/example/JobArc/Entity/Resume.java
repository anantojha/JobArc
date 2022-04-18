package com.example.JobArc.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "resumes")
public class Resume {

    private @Id @GeneratedValue @Column(name="id") long id;
    private @NotBlank @Column(name="create_date") Timestamp createDate;
    private @NotBlank @Column(name="jobseeker_name") String jobseeker_name;
    private @NotBlank @Column(name="description") String description;
    private @NotBlank @Column(name="education_1") String education_one;
    private @Column(name="education_2") String education_two;
    private @Column(name="education_3") String education_three;
    private @NotBlank @Column(name="work_1") String work_one;
    private @Column(name="work_2") String work_two;
    private @Column(name="work_3") String work_three;
    private @NotBlank @Column(name="skills") String skills;
    private @Column(name="certifications") String certifications;

    public Resume() { }

    public Resume(@NotBlank String jobseeker_name, @NotBlank String description, @NotBlank String education_one, String education_two, String education_three,
                  @NotBlank String work_one, String work_two, String work_three, @NotBlank String skills, String certifications) {
        this.jobseeker_name = jobseeker_name;
        this.createDate = new Timestamp(System.currentTimeMillis());
        this.description = description;
        this.education_one = education_one;
        this.education_two = education_two;
        this.education_three = education_three;
        this.work_one = work_one;
        this.work_two = work_two;
        this.work_three = work_three;
        this.skills = skills;
        this.certifications = certifications;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Resume resume)) return false;
        return Objects.equals(jobseeker_name, resume.jobseeker_name) &&
                Objects.equals(description, resume.description) &&
                Objects.equals(work_one, resume.work_one) &&
                Objects.equals(education_one, resume.education_one) &&
                Objects.equals(skills, resume.skills);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createDate, description);
    }

    public long getId() {
        return id;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEducation_one() {
        return education_one;
    }

    public void setEducation_one(String education_one) {
        this.education_one = education_one;
    }

    public String getEducation_two() {
        return education_two;
    }

    public void setEducation_two(String education_two) {
        this.education_two = education_two;
    }

    public String getEducation_three() {
        return education_three;
    }

    public void setEducation_three(String education_three) {
        this.education_three = education_three;
    }

    public String getWork_one() {
        return work_one;
    }

    public void setWork_one(String work_one) {
        this.work_one = work_one;
    }

    public String getWork_two() {
        return work_two;
    }

    public void setWork_two(String work_two) {
        this.work_two = work_two;
    }

    public String getWork_three() {
        return work_three;
    }

    public void setCreateDate(Timestamp ts){
        createDate = ts;
    }

    public void setWork_three(String work_three) {
        this.work_three = work_three;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getCertifications() {
        return certifications;
    }

    public void setCertifications(String certifications) {
        this.certifications = certifications;
    }

    @Override
    public String toString() {
        return "Resume{" +
                "id=" + id +
                ", createDate=" + createDate +
                ", description='" + description + '\'' +
                ", education_one='" + education_one + '\'' +
                ", education_two='" + education_two + '\'' +
                ", education_three='" + education_three + '\'' +
                ", work_one='" + work_one + '\'' +
                ", work_two='" + work_two + '\'' +
                ", work_three='" + work_three + '\'' +
                ", skills='" + skills + '\'' +
                ", certifications='" + certifications + '\'' +
                '}';
    }
}
