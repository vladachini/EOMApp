package com.example.eomapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Date;


public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Login.db";
    private static final int DATABASE_VERSION = 11;
    private String details=("No Details");

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String userTable = ("CREATE TABLE user (email TEXT PRIMARY KEY, password TEXT)");
        String eventTable = ("CREATE TABLE Events(_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, title TEXT NOT NULL, " +
                "category text not null, date text not null, time text not null, details text, author text )");
        db.execSQL(userTable);
        db.execSQL(eventTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if (oldVersion < 7) {
            db.execSQL("ALTER TABLE Events ADD endTime Text");
        }
        if (oldVersion < 8) {
            db.execSQL("ALTER TABLE Events ADD dateTime int");
        }
        if (oldVersion < 9) {
            db.execSQL("ALTER TABLE Events ADD dt text");
        }
        if (oldVersion < 10) {
            db.execSQL("DROP TABLE Events ");
            db.execSQL("Create table Events(_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, title TEXT NOT NULL, " +
                    "category text not null, date text not null, time text not null, " +
                    "details text, author text,endTime text, dateTime long )");
        }
        if (oldVersion <11){
            db.execSQL("ALTER TABLE Events ADD location Text");
        }



    }

    //Inserting in database
    public boolean insertLogin(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        Log.d("Log ", "inserting  " + email + " " + password);
        long ins = db.insert("user", null, contentValues);
        //Database returns -1 if not inserted correctly
        if (ins == -1) return false;
        else return true;
    }

    public boolean insertEvent(String title, String category, String date, String time,
                               String details, String author, String endTime, long dateTime, String place) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues eventValues = new ContentValues();
        eventValues.put("title", title);
        eventValues.put("category", category);
        eventValues.put("date", date);
        eventValues.put("time", time);
        eventValues.put("details", details);
        eventValues.put("author", author);
        eventValues.put("endTime", endTime);
        eventValues.put("dateTime", dateTime);
        eventValues.put("location", place);
        long ins = db.insert("Events", null, eventValues);
        if (ins == -1) return false;
        else return true;
    }

    public Cursor getEvent() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = ("SELECT * from Events where dateTime >= strftime('%s','now') order by datetime asc");
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }
    public Cursor getSports() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = ("SELECT * from Events where dateTime >= strftime('%s','now') and category = ('Sports') order by datetime asc");
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }
    public Cursor getArts() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = ("SELECT * from Events where dateTime >= strftime('%s','now') and category = 'Arts' order by datetime asc");
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }
    public Cursor getClub() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = ("SELECT * from Events where dateTime >= strftime('%s','now') and category = 'Club' order by datetime asc");
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }


    //checking if email exists
    public Boolean checkemail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where email=? ", new String[]{email});
        return cursor.getCount() <= 0;
    }

    //checking to see if email and password match for login
    public boolean loginCheck(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user where email=? and password=?", new String[]{email, password});
        if (cursor.getCount() == 1) {
            return true;
        } else {
            return false;
        }
    }
    public String getDetails (Cursor cursor, int position){
        cursor.moveToPosition(position);
        details = cursor.getString(cursor.getColumnIndexOrThrow("details"));
        return details;
    }
    public String getStartTime (Cursor cursor, int position) {
        cursor.moveToPosition(position);
        String sTime = cursor.getString(cursor.getColumnIndexOrThrow("time"));
        return sTime;
    }
    public String getEndTime (Cursor cursor, int position) {
        cursor.moveToPosition(position);
        String eTime = cursor.getString(cursor.getColumnIndexOrThrow("endTime"));
        return eTime;
    }
    public String getLocation (Cursor cursor, int position) {
        cursor.moveToPosition(position);
        String location = cursor.getString(cursor.getColumnIndexOrThrow("location"));
        return location;
    }
    public int getId (Cursor cursor, int position) {
        cursor.moveToPosition(position);
        int id = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
        return id;
    }
    public void deleteEvent(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("Delete from Events where _id=?", new String[]{String.valueOf(id)});
    }
}
