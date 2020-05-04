package com.example.eomapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class HomeFeed extends AppCompatActivity {
    private Button post;
    private ListView lv;
    DatabaseHelper db= new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_feed);
        //type code here
        post= findViewById(R.id.createBtn);
        lv= findViewById(R.id.myListView);

        viewData();

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), EventInput.class);
                startActivity(startIntent);
            }
        });

    }

    public void viewData(){
        Cursor cursor = db.getEvent();

        if(cursor.getCount()==0){
            Toast.makeText(this, "No Events Happening", Toast.LENGTH_SHORT).show();
        }
        else{
            //while(cursor.moveToNext()){

           // }
            Adapter cursorAdapter = new Adapter(this, cursor);
            lv.setAdapter(cursorAdapter);
        }

        // Switch to new cursor and update contents of ListView
        //todoAdapter.changeCursor(todoCursor);
    }
}
