package com.example.JobArc.RequestModels;

import javax.validation.constraints.NotBlank;

public class JobRequest {

    private @NotBlank String title;
    private @NotBlank String description;
    private @NotBlank long employerId;

    public JobRequest(String title, String description, long employerId) {
        this.title = title;
        this.description = description;
        this.employerId = employerId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public long getEmployerId() {
        return employerId;
    }
}
