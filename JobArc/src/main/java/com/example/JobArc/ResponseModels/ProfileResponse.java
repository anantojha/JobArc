package com.example.JobArc.ResponseModels;

public class ProfileResponse {
    private String name;
    private String username;
    private String accountType;
    private long id;

    public ProfileResponse(String name, String username, String accountType, long id){
        this.name = name;
        this.username = username;
        this.id = id;
        this.accountType = accountType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
