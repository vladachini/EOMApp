package com.example.eomapp;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

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
        TextView startTime = (TextView) view.findViewById(R.id.startTimeView);
        TextView endTime= (TextView) view.findViewById(R.id.endTimeView);


        String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
        String sTime = cursor.getString(cursor.getColumnIndexOrThrow("time"));
        String eTIme= cursor.getString(cursor.getColumnIndexOrThrow("endTime"));

        eventTitle.setText(title);
        startTime.setText(sTime);
        endTime.setText(eTIme);
    }

}
