package com.example.eomapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context) {
        super(context, "Login.db",  null,  1);
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
        long ins= db.insert("user",null,contentValues );
        return ins != -1;
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
        return cursor.getCount() > 0;
    }
}
