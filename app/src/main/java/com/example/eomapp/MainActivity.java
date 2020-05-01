package com.example.eomapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText e1,p1;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         e1= findViewById(R.id.emailText);
         p1= findViewById(R.id.passwordText);


         TextView signUp = findViewById(R.id.textView5);
         signUp.setPaintFlags(signUp.getPaintFlags()|Paint.UNDERLINE_TEXT_FLAG);
        Button login = findViewById(R.id.loginButton);
        db= new DatabaseHelper(this);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //changing to second screen
                Intent startIntent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(startIntent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = e1.getText().toString();
                String password= p1.getText().toString();
                Boolean checkLogin= db.loginCheck(email,password);
                if(checkLogin){
                    Intent startIntent2 = new Intent(getApplicationContext(), HomeFeed.class);
                    startActivity(startIntent2);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Incorrect Email or Password", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    //comment to check github



}
