package com.example.eomapp;

public class Events {
    String title;
    String details;
    String date;
    String time; //might need to change from int into string later
    String category;
    boolean saved = false;

    public Events(String t, String c, String dt, String tm, String d){
        title=t;
        details=d;
        date=dt;
        time=tm;
        category=c;

    }
    public Events(String t, String c, String dt, String tm){
        title=t;
        date=dt;
        time=tm;
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

    public String getTime() {
        return time;
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

    public void setTime(String time) {
        this.time = time;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
