package com.example.eomapp;

public class User {
    String email;
    String password;
    int grade;
    boolean admin;

    public User(String e, String p){
        email=e;
        password=p;
        grade=0;
        admin=false;

    }
    public User(String e, String p, int g){
        email=e;
        password=p;
        grade=g;
        admin=false;
    }
    public User (String e, String p, int g, boolean a){
        email=e;
        password=p;
        grade=g;
        admin=true;
    }


}
