package com.KS.model;

public class ModleUser {

    private int userId;
    private String name;
    private String lastname;
    private String email;
    private String password;

    public ModleUser(int userId, String name, String lastname, String email, String password) {
        this.userId = userId;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
    }

    public ModleUser(){
        
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}
