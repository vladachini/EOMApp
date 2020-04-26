package com.example.eomapp;

public class Events {
    String title;
    String details;
    int date;
    int idCounter=0;
    String iD;
    int time; //might need to change from int into string later
    String category;
    boolean saved = false;

    public Events(String t, String d, int dt, int tm, String c){
        title=t;
        details=d;
        date=dt;
        time=tm;
        category=c;
        idCounter++;
        iD="e" + idCounter;
    }
}
