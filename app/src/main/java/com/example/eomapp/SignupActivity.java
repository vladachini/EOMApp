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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.view.View.*;

public class SignupActivity extends AppCompatActivity {

    private EditText userEmail;
    private EditText userPassword;
    private EditText cpass;
    
    Button b1;
    DatabaseHelper db = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);



        cpass= findViewById(R.id.userConfirmText);
        userEmail= findViewById(R.id.usereText);
        userPassword= findViewById(R.id.userpassText);
        b1=findViewById(R.id.button);

        b1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String e1= userEmail.getText().toString();
                String p1= userPassword.getText().toString();
                String p2= cpass.getText().toString();
                boolean vEmail=false;
                vEmail=isEmailValid(e1); //checks to see if email is valid
                boolean vPassword= false;
                vPassword=isValidPassword(p1);
                if(!vPassword) {
                    Toast.makeText(getApplicationContext(), "Password must contain 1 Upper, 1 lower, and 8-20 characters", Toast.LENGTH_SHORT).show();

                }

                if(e1.equals("")|| p1.equals("")|| p2.equals("") ){
                    Toast.makeText(getApplicationContext(), "Fields are Empty", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(p1.equals(p2)){
                        Boolean checkEmail = db.checkemail(e1);
                        if(checkEmail==true && vEmail==true && vPassword==true){
                            Boolean insert = db.insertLogin(e1,p1);
                            if(insert==true ){
                                Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_SHORT).show();
                                Intent startIntent = new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(startIntent);
                            }
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Email already exists", Toast.LENGTH_SHORT).show();
                        }

                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();
                    }


                }
            }
        });

    }
        //checks to see that email is valid
        public boolean isEmailValid(String e){
        String emailPattern= "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"; //This is the pattern the email must follow
            if (e.matches(emailPattern))
            {
                return true;
            }
            else
            {
                Toast.makeText(getApplicationContext(),"Invalid email address", Toast.LENGTH_SHORT).show();
                return false;
            }

    }
    public static boolean isValidPassword(String password) {
        Matcher matcher = Pattern.compile("((?=.*[a-z])(?=.*[A-Z]).{8,20})").matcher(password);
        return matcher.matches();
    }
}
