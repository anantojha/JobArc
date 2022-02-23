package com.example.JobArc.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "jobs_mapping")
public class JobMapping {
    private @Id @NotBlank long jobId;
    private @NotBlank long employerId;

    public JobMapping() { }

    public JobMapping(@NotBlank long jobId, @NotBlank long employerId) {
        this.jobId = jobId;
        this.employerId = employerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JobMapping jobMapping)) return false;
        return Objects.equals(jobId, jobMapping.jobId) &&
                Objects.equals(employerId, jobMapping.employerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jobId, employerId);
    }

    @Override
    public String toString() {
        return "User{" +
                ", jobId='" + jobId + '\'' +
                ", employerId='" + employerId +
                '}';
    }
}
