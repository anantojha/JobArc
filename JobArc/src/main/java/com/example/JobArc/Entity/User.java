package com.example.JobArc.Entity;

import com.example.JobArc.Enums.AccountType;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {
    private @Id @GeneratedValue @Column(name="id") long id;
    private @NotBlank @Column(name="name") String name;
    private @NotBlank @Column(name="username") String username;
    private @NotNull @NotBlank @Column(name="password") String password;
    private @NotBlank @Column(name="account_type") String accountType;

    public User() { }

    public User(long id, String name, String username, String password, String accountType) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.accountType = accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public User(@NotBlank String username, @NotBlank String password) {
        this.name = "";
        this.username = username;
        this.password = password;
        this.accountType = null;
    }

    public long getId() {
        return id;
    }

    public String getName() { return name; }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getAccountType() {
        return this.accountType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, username, password, accountType);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", accountType=" + accountType +
                '}';
    }
}
