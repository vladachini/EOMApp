package com.example.eomapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EventInput extends AppCompatActivity {
    private EditText title;
    private EditText category;
    private EditText date;
    private EditText time;
    private EditText details;
    private EditText author;
    private Button post;
    DatabaseHelper db= new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_input);
        post= findViewById(R.id.postBtn);
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title= findViewById(R.id.titleText);
                String eventTitle= title.getText().toString();
                category= findViewById(R.id.categoryText);
                String eventCategory= category.getText().toString();
                date= findViewById(R.id.dateText);
                String eventDate= date.getText().toString();
                time=findViewById(R.id.timeText);
                String eventTime= time.getText().toString();
                details= findViewById(R.id.detailsText);
                String eventDetails= details.getText().toString();
                author= findViewById(R.id.authorText);
                String eventAuthor= author.getText().toString();
                Events e= new Events (eventTitle,eventCategory, eventDate, eventTime, eventDetails);
                    if(eventTitle.equals("")|| eventCategory.equals("")|| eventDate.equals("") || eventTime.equals("") ){
                    Toast.makeText(getApplicationContext(), "Make Sure Fields aren't Empty", Toast.LENGTH_SHORT).show();
                     }
                     else {
                        boolean insertCheck=db.insertEvent(eventTitle,eventCategory,eventDate,eventTime,eventDetails,eventAuthor);
                        if(insertCheck){
                            Toast.makeText(getApplicationContext(), "Successfully Posted", Toast.LENGTH_SHORT).show();
                            Intent startIntent = new Intent(getApplicationContext(), HomeFeed.class);
                            startActivity(startIntent);
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Error did not Post", Toast.LENGTH_SHORT).show();
                        }
                     }
            }
        });


    }
}
