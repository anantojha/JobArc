package com.comp4905.jobarc.Models;

public class User {
    private String name;
    private String username;
    private String password;
    private String accountType;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User setAccountType(String accountType) {
        this.accountType = accountType;
        return this;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getAccountType() { return this.accountType; }

    public String getName() { return this.name; }
}
