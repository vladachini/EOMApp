package com.example.eomapp;

public class User {
    String email;
    String password;
    int grade;
    UserType User;

    public User(String e, String p){
        email=e;
        password=p;
        grade=0;
        User=UserType.USER;

    }
    public User(String e, String p, int g){
        email=e;
        password=p;
        grade=g;
        User=UserType.USER;
    }
    public User (String e, String p, int g, UserType t){
        email=e;
        password=p;
        grade=g;
        User=t;

    }
    public User (String e, String p, UserType t){
        email=e;
        password=p;
        grade=0;
        User=t;

    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUser() {
        return User;
    }

    public void setUser(UserType user) {
        User = user;
    }
}
