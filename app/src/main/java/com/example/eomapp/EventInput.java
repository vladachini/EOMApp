package com.example.eomapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class EventInput extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private EditText title;
    private Spinner category;
    String c;
    private TextView datePicker;
    private String date;
    private DatePickerDialog.OnDateSetListener dateListener;
    private TextView time;
    private String stringTime;
    private EditText details;
    private EditText author;
    private TextView endTime;
    private Button post;
    int year, month, day, hour1, minute1, hour2, minute2;
    private TimePickerDialog eTimePicker;
    private TimePickerDialog sTimePicker;
    private TimePickerDialog.OnTimeSetListener sListener;
    private TimePickerDialog.OnTimeSetListener eListener;
    long seconds;


    DatabaseHelper db = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_input);
        datePicker = findViewById(R.id.datePicker);
        title = findViewById(R.id.titleText);
        category = findViewById(R.id.category);
        time = findViewById(R.id.timeText);
        details = findViewById(R.id.detailsText);
        author = findViewById(R.id.authorText);
        endTime = findViewById(R.id.endTime);

        String[] categories = new String[]{"Sports", "Arts", "Club", "School Spirit", "Other"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category.setAdapter(adapter);
        category.setOnItemSelectedListener(this);

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                hour1 = c.get(Calendar.HOUR_OF_DAY);
                minute1 = c.get(Calendar.MINUTE);
                sListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        int h = hourOfDay;
                        int m = minute;
                        stringTime = h + ":" + m + ":00";
                        time.setText(stringTime);
                    }
                };
                sTimePicker = new TimePickerDialog(EventInput.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth, sListener,
                        hour1, minute1, false);
                sTimePicker.show();
            }
        });

        endTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                hour2 = c.get(Calendar.HOUR_OF_DAY);
                minute2 = c.get(Calendar.MINUTE);
                eListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        int h = hourOfDay;
                        int m = minute;
                        endTime.setText(h + ":" + m + ":00");
                    }
                };
                eTimePicker = new TimePickerDialog(EventInput.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth, eListener,
                        hour2, minute2, false);
                eTimePicker.show();
            }
        });

        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                year = cal.get(Calendar.YEAR);
                month = cal.get(Calendar.MONTH);
                day = cal.get(Calendar.DAY_OF_MONTH);
                dateListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        date = month + "/" + dayOfMonth + "/" + year;
                        System.out.println(date);
                        datePicker.setText(date);
                    }
                };
                DatePickerDialog dialog = new DatePickerDialog(EventInput.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        dateListener, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });


        post = findViewById(R.id.postBtn);
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eventTitle = title.getText().toString();
                String eventCategory = c;
                String eventTime = time.getText().toString();
                String eTime = endTime.getText().toString();
                String eventDetails = details.getText().toString();
                String eventAuthor = author.getText().toString();

                String dateTime = date + " " + stringTime;
                DateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
                try {
                    Date dateSeconds = sdf.parse(dateTime);
                    seconds = dateSeconds.getTime() / 1000;
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Events e = new Events(eventTitle, eventCategory, date, eventTime, eTime, eventDetails);
                if (eventTitle.equals("") || eventCategory.equals("") || date.equals("") || eventTime.equals("")) {
                    Toast.makeText(getApplicationContext(), "Make Sure Fields aren't Empty", Toast.LENGTH_SHORT).show();
                } else {
                    boolean insertCheck = db.insertEvent(eventTitle, eventCategory, date, eventTime, eventDetails, eventAuthor, eTime, seconds);
                    if (insertCheck) {
                        Toast.makeText(getApplicationContext(), "Successfully Posted", Toast.LENGTH_SHORT).show();
                        Intent startIntent = new Intent(getApplicationContext(), HomeFeed.class);
                        startActivity(startIntent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Error did not Post", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });


    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        c = (String) parent.getItemAtPosition(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        c = "Other";
    }
}
