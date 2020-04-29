package com.example.eomapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class EventInput extends AppCompatActivity {
    private EditText title;
    private EditText category;
    private EditText date;
    private EditText time;
    private EditText details;
    private EditText author;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_input);
        title= findViewById(R.id.titleText);
        String eventT= title.getText().toString();
        category= findViewById(R.id.categoryText);
        String eventC= category.getText().toString();
        date= findViewById(R.id.dateText);




    }
}
