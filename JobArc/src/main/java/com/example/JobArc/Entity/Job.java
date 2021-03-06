package com.example.JobArc.Entity;

import org.springframework.context.annotation.Bean;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "jobs")
public class Job {

    private @Id @GeneratedValue @Column(name="id") long id;
    private @NotBlank @Column(name="create_date") Timestamp createDate;
    private @NotBlank @Column(name="description") String description;
    private @NotBlank @Column(name="title") String title;
    private @NotBlank @Column(name="employer_name") String employerName;
    private @NotBlank @Column(name="location") String location;
    private @NotBlank @Column(name="job_type") String jobType;

    public Job() { }

    public Job(@NotBlank String employerName, @NotBlank String title, @NotBlank String description, @NotBlank String location, @NotBlank String jobType) {
        this.title = title;
        this.description = description;
        this.createDate = new Timestamp(System.currentTimeMillis());
        this.employerName = employerName;
        this.location = location;
        this.jobType = jobType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Job job)) return false;
        return Objects.equals(title, job.title) &&
                Objects.equals(description, job.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, createDate);
    }


    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public String getEmployerName() { return employerName; }

    public String getLocation() { return location; }

    public String getJobType() { return jobType; }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", createDate=" + createDate.toString() +
                ", employer='" + employerName + '\'' +
                ", location='" + location + '\'' +
                ", type='" + jobType + '\'' +
                '}';
    }
}
