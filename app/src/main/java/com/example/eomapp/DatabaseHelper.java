package com.example.eomapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="Login.db";
    private static final int DATABASE_VERSION= 5;
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME,  null,  DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String userTable=("CREATE TABLE user (email TEXT PRIMARY KEY, password TEXT)");
        String eventTable=("CREATE TABLE Events(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, title TEXT NOT NULL, " +
                "category text not null, date text not null, time text not null, details text, author text )");
        db.execSQL(userTable);
        db.execSQL(eventTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //if(oldVersion<4) {
        //    db.execSQL("CREATE TABLE Events(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, title TEXT NOT NULL, " +
         //           "category text not null, " +
        //            "date text not null, time text not null, details text, author text )");
       //     db.execSQL("CREATE TABLE user (email TEXT PRIMARY KEY, password TEXT)");
      //  }
        if(oldVersion<5){
            db.execSQL("ALTER TABLE Events ADD endTime Text");
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

    public  boolean insertEvent(String title, String category, String date, String time,
                               String details, String author) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues eventValues = new ContentValues();
        eventValues.put("title", title);
        eventValues.put("category", category);
        eventValues.put("date", date);
        eventValues.put("time", time);
        eventValues.put("details", details);
        eventValues.put("author", author);
        long ins = db.insert("Events", null, eventValues);
        if (ins == -1) return false;
        else return true;
    }

    //checking if email exists
    public Boolean checkemail(String email){
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor cursor= db.rawQuery("Select * from user where email=? ", new String[]{email});
        return cursor.getCount() <= 0;
    }
    //checking to see if email and password match for login
    public boolean loginCheck(String email, String password){
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor cursor= db.rawQuery("select * from user where email=? and password=?", new String[]{email,password});
        if (cursor.getCount()==1) {
            return true;
        }
        else {
            return false;
        }
    }
}
