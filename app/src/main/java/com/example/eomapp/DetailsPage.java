package com.example.eomapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailsPage extends AppCompatActivity {
    TextView detailsPage;
   String eventDetails="No Details";
   TextView startTime;
   TextView endTime;

    DatabaseHelper db = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_page);
        detailsPage = findViewById(R.id.detailsView);
        endTime=findViewById(R.id.endTimeView);
        startTime= findViewById(R.id.startTimeView);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        eventDetails= extras.getString("details");
        String sTime=extras.getString("startTime");
        String eventEndTime=extras.getString("endTime");
        startTime.setText("Event Start: "+ sTime);
        endTime.setText("Event End: "+ eventEndTime);
        detailsPage.setText(eventDetails);
    }

}
