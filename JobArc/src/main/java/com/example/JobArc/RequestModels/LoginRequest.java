package com.example.JobArc.RequestModels;

import javax.validation.constraints.NotBlank;

public class LoginRequest {


    private @NotBlank String username;
    private @NotBlank String password;


    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
