package com.algonquin.cookbook.entity;

public class User {

    private String email;
    private String username;
    private String password;
    private Boolean isAccountVerified;

    public User() {
    }

    public User(String email, String username, String password, Boolean isAccountVerified) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.isAccountVerified = isAccountVerified;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAccountVerified() {
        return isAccountVerified;
    }

    public void setAccountVerified(Boolean accountVerified) {
        isAccountVerified = accountVerified;
    }
}
