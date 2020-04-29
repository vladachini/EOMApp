package com.example.eomapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;




public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="Login.db";
    private static final int DATABASE_VERSION= 1;

    public DatabaseHelper(Context context) {

        super(context, DATABASE_NAME,  null,  DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create Table user(email text primary key, password text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists user");
    }

    //Inserting in database
    public boolean insert(String email, String password){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("email",email);
        contentValues.put("password",password);
        Log.d("Log ","inserting  "+ email + " " + password);
        long ins= db.insert("user",null,contentValues );
<<<<<<< HEAD
    //Database returns -1 if not inserted correctly
        if(ins==-1) return false;
        else return true;
=======
        return ins != -1;
    }
    public  void insertEvent(String title, String category, String Date, String Time,
                               String details, String author){
        SQLiteDatabase db= this.getWritableDatabase();
        String ins;
        ins="INSERT INTO Events (Title, Category, Date, Time, Details, Author) " +
                "VALUES (" + title + ", " + category + ", " + Date + ", " +
                Time + ", " + details + "," + author + ")";
          db.execSQL(ins);

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
<<<<<<< HEAD
        if (cursor.getCount()==1) return true;
        else return false;
=======
        return cursor.getCount() > 0;
>>>>>>> FrankGui
    }
}
