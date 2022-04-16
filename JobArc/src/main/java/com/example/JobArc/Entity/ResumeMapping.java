package com.example.JobArc.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
@Table(name = "resumes_mapping")
public class ResumeMapping {
    private @Id @NotBlank long resumeId;
    private @NotBlank long jobseekerId;

    public ResumeMapping() { }

    public ResumeMapping(@NotBlank long resumeId, @NotBlank long jobseekerId) {
        this.resumeId = resumeId;
        this.jobseekerId = jobseekerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResumeMapping resumeMapping)) return false;
        return Objects.equals(resumeId, resumeMapping.resumeId) &&
                Objects.equals(jobseekerId, resumeMapping.jobseekerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resumeId, jobseekerId);
    }

    @Override
    public String toString() {
        return "ResumeMapping{" +
                "resumeId=" + resumeId +
                ", jobseekerId=" + jobseekerId +
                '}';
    }
}
