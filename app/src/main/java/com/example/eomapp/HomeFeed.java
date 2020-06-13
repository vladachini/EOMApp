package com.example.eomapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class HomeFeed extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private Button post;
    private ListView lv;
    private Button settings;
    private TextView eventId;
    Spinner filter;
    String f;
    String chosenFilter="None";
    DatabaseHelper db = new DatabaseHelper(this);
    Cursor cursor;
    String eDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_feed);
        //type code here
        post = findViewById(R.id.createBtn);
        lv = findViewById(R.id.myListView);
        settings = findViewById(R.id.settingBtn);
        filter = findViewById(R.id.filters);
        eventId= findViewById(R.id.idView);
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), AdminCode.class);
                startIntent.putExtra("activity","first");
                startActivity(startIntent);
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), Settings.class);
                startActivity(startIntent);
            }
        });

        String[] filters = new String[]{"Filter", "Arts", "Club", "Sports"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, filters);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        filter.setAdapter(adapter);
        filter.setOnItemSelectedListener(this);
        getFilter();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle extras =new Bundle();
                int p=position;
                eDetails=db.getDetails(cursor, p);
                String eventStartTime= db.getStartTime(cursor,p);
                String eventEndTime=db.getEndTime(cursor,p);
                String place=db.getLocation(cursor,p);
                String title = db.getTitle (cursor, p);
                int eventId= db.getId(cursor,p);
                extras.putString("details",eDetails);
                extras.putString ("startTime", eventStartTime);
                extras.putString("endTime", eventEndTime);
                extras.putString("location", place);
                extras.putString("title", title);
                extras.putInt("id",eventId);
                Intent startIntent = new Intent(getApplicationContext(), DetailsPage.class);
                startIntent.putExtras(extras);
                startActivity(startIntent);
            }
        });

    }
    public void getFilter(){

        if(chosenFilter.equals("Filter")){
            viewData();
        }
        else if (chosenFilter.equals("Sports")){
            viewSports();
        }
        else if (chosenFilter.equals("Arts")){
            viewArts();
        }
        else if (chosenFilter.equals("Club")){
            viewClub();
        }
        else{
            viewData();
        }

    }

    public void viewData() {
        cursor = db.getEvent();

        if (cursor.getCount() == 0) {
            Adapter cursorAdapter = new Adapter(this, cursor);
            lv.setAdapter(cursorAdapter);
            Toast.makeText(this, "No Events Happening", Toast.LENGTH_SHORT).show();
        } else {

            Adapter cursorAdapter = new Adapter(this, cursor);
            lv.setAdapter(cursorAdapter);
        }

    }
    public void viewSports() {
        cursor = db.getSports();
        if (cursor.getCount() == 0) {
            Adapter cursorAdapter = new Adapter(this, cursor);
            lv.setAdapter(cursorAdapter);
            Toast.makeText(this, "No Events Happening", Toast.LENGTH_SHORT).show();
        } else {
            Adapter cursorAdapter = new Adapter(this, cursor);
            lv.setAdapter(cursorAdapter);
        }
    }
    public void viewArts() {
         cursor = db.getArts();

        if (cursor.getCount() == 0) {
            Adapter cursorAdapter = new Adapter(this, cursor);
            lv.setAdapter(cursorAdapter);
            Toast.makeText(this, "No Events Happening", Toast.LENGTH_SHORT).show();
        } else {

            Adapter cursorAdapter = new Adapter(this, cursor);
            lv.setAdapter(cursorAdapter);
        }
    }
    public void viewClub() {
        cursor = db.getClub();

        if (cursor.getCount() == 0) {
            Adapter cursorAdapter = new Adapter(this, cursor);
            lv.setAdapter(cursorAdapter);
            Toast.makeText(this, "No Events Happening", Toast.LENGTH_SHORT).show();
        } else {

            Adapter cursorAdapter = new Adapter(this, cursor);
            lv.setAdapter(cursorAdapter);
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        f = (String) parent.getItemAtPosition(position);
        chosenFilter = f;
        getFilter();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        f="None";
    }

}
