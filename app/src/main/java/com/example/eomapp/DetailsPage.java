package com.example.eomapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailsPage extends AppCompatActivity {
    TextView detailsPage;
   String eventDetails="No Details";
   TextView startTime;
   TextView endTime;
   TextView location;
   int id;
   Button delete;
   Bundle extras2 =new Bundle();
    DatabaseHelper db = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_page);
        detailsPage = findViewById(R.id.detailsView);
        endTime=findViewById(R.id.endTimeView);
        startTime= findViewById(R.id.startTimeView);
        location= findViewById(R.id.placeView);
        delete = findViewById(R.id.deleteBtn);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        eventDetails= extras.getString("details");
        String sTime=extras.getString("startTime");
        String eventEndTime=extras.getString("endTime");
        String place= extras.getString("location");
        id=extras.getInt("id");
        startTime.setText("Event Start: "+ sTime);
        endTime.setText("Event End: "+ eventEndTime);
        detailsPage.setText(eventDetails);
        location.setText(place);
        extras2.putInt("id",id);
        extras2.putString ("activity", "DetailsPage");
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), AdminCode.class);
                startIntent.putExtras(extras2);
                startActivity(startIntent);
            }
        });
    }

}
