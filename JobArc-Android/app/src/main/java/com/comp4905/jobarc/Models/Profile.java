package com.comp4905.jobarc.Models;

public class Profile {

    private String name;
    private String username;
    private String accountType;
    private long id;

    public Profile(String name, String username, String accountType, long id){
        this.name = name;
        this.username = username;
        this.accountType = accountType;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getAccountType() {
        return accountType;
    }

    public long getId() {
        return id;
    }
}
