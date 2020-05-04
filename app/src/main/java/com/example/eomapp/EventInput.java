package com.example.eomapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class EventInput extends AppCompatActivity {
    private EditText title;
    private EditText category;
    private TextView datePicker;
    private String date;
    private DatePickerDialog.OnDateSetListener dateListener;
    private EditText time;
    private EditText details;
    private EditText author;
    private EditText endTime;
    private Button post;
    DatabaseHelper db= new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_input);
        datePicker= (TextView)findViewById(R.id.datePicker);
        title= findViewById(R.id.titleText);
        category= findViewById(R.id.categoryText);
        time=findViewById(R.id.timeText);
        details= findViewById(R.id.detailsText);
        author= findViewById(R.id.authorText);
        endTime= findViewById(R.id.endTime);
        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(EventInput.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        dateListener, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

                dateListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month=month+1;
                        date= year + "-" + month + "-" + dayOfMonth;
                        datePicker.setText(date);
                    }
                };
            }
        });

        post= findViewById(R.id.postBtn);
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eventTitle= title.getText().toString();
                String eventCategory= category.getText().toString();
                String eventTime= time.getText().toString();
                String eTime=endTime.getText().toString();
                String eventDetails= details.getText().toString();
                String eventAuthor= author.getText().toString();
                Events e= new Events (eventTitle,eventCategory, date, eventTime, eTime, eventDetails);
                    if(eventTitle.equals("")|| eventCategory.equals("")|| date.equals("") || eventTime.equals("") ){
                    Toast.makeText(getApplicationContext(), "Make Sure Fields aren't Empty", Toast.LENGTH_SHORT).show();
                     }
                     else {
                        boolean insertCheck=db.insertEvent(eventTitle,eventCategory,date,eventTime,eventDetails,eventAuthor, eTime);
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
