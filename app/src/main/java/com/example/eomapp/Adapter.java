package com.example.eomapp;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Adapter extends CursorAdapter {

    public Adapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.event_layout, parent, false);
    }


    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView eventTitle = (TextView) view.findViewById(R.id.eventTitle);
        TextView dateView = (TextView) view.findViewById(R.id.startTimeView);
        TextView startTime = (TextView) view.findViewById(R.id.endTimeView);
        TextView idView= view.findViewById(R.id.idView);

        String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
        String sTime = cursor.getString(cursor.getColumnIndexOrThrow("time"));
        String eTIme = cursor.getString(cursor.getColumnIndexOrThrow("endTime"));
        String date = cursor.getString(cursor.getColumnIndexOrThrow("date"));
        eventTitle.setText(title);
        dateView.setText(date);
        startTime.setText("Event Start: " + sTime);


    }


    }


