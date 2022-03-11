package com.comp4905.jobarc.Models;

public class User {
    private String name;
    private String username;
    private String password;
    private String accountType;

    public User(String name, String username, String password, String accountType) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.accountType = accountType;
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
