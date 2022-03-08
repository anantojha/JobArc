package com.example.JobArc.ResponseModels;

import com.example.JobArc.Enums.AccountType;

public class LoginResponse {
    private String status;
    private String username;
    private long id;
    private String accountType;

    public LoginResponse(String status, String username, long id, String accountType){
        this.status = status;
        this.username = username;
        this.id = id;
        this.accountType = accountType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
}
