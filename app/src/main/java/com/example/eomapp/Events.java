package com.example.eomapp;

public class Events {
    String title;
    String details;
    String date;
    String startTime; //might need to change from int into string later
    String endTime;
    String category;
    boolean saved = false;

    public Events(String t, String c, String dt, String sTime, String eTime, String d){
        title=t;
        details=d;
        date=dt;
        startTime=sTime;
        endTime= eTime;
        category=c;

    }
    public Events(String t, String c, String dt, String sTime, String eTime){
        title=t;
        date=dt;
        startTime=sTime;
        endTime= eTime;
        category=c;

    }

    public String getCategory() {
        return category;
    }

    public String getDate() {
        return date;
    }

    public String getDetails() {
        return details;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime(){
        return endTime;
    }

    public String getTitle() {
        return title;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setStartTime(String time) {
        this.startTime = time;
    }
    public void setEndTime(String time){
        this.endTime =time;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
