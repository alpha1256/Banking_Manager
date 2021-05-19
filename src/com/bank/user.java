package com.bank;

public class user {
    private String name;
    private String userName;
    private String password;
    private account checkings;
    private account savings;

    //Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setCheckings(account checkings) {
        this.checkings = checkings;
    }

    public void setSavings(account savings) {
        this.savings = savings;
    }

    //Getters
    public account getCheckings() {
        return checkings;
    }

    public account getSavings() {
        return savings;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public user (String name, String username, String password)
    {
        this.name = name;
        this.userName = username;
        this.password = password;
        checkings = new account("Checkings");
        savings = new account("Savings");
        System.out.print("Welcome " + this.name + " your username: " + this.userName);
    }

    public user ()
    {

    }


    public boolean checkUseAndPassword(String userName, String password)
    {
        if (userName.equals(this.userName) && password.equals(this.password))
            return true;
        else
            return false;
    }
}
