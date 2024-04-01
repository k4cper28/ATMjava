package com.KS.model;

import java.math.BigDecimal;

public class ModleUser {

    private int userId;
    private String name;
    private String lastname;
    private String email;
    private String password;
    private int pin;
    private BigDecimal balance;
    private String accountNumber;

    public ModleUser(int userId, String name, String lastname, String email, String password, int pin, BigDecimal balance, String accountNumber) {
        this.userId = userId;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.pin = pin;
        this.balance = balance;
        this.accountNumber = accountNumber;
    }

    public ModleUser(){
        
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public int getPin() {
        return pin;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
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
