package com.example.eomapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    private EditText userEmail;
    private EditText userPassword;
    private EditText cpass;
    Button b1;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        cpass=(EditText)findViewById(R.id.userConfirmText);
        userEmail= (EditText)findViewById(R.id.usereText);
        userPassword= (EditText)findViewById(R.id.userpassText);
        b1=(Button)findViewById(R.id.signUp2Btn);
        db= new DatabaseHelper(this);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String e1= userEmail.getText().toString();
                String p1= userPassword.getText().toString();
                String p2= cpass.getText().toString();
                if(e1.equals("")|| p1.equals("")|| p2.equals("") ){
                    Toast.makeText(getApplicationContext(), "Fields are Empty", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(p1.equals(p2)){
                        Boolean checkemail = db.checkemail(e1);
                        if(checkemail==true){
                            Boolean insert = db.insert(e1,p1);
                            if(insert==true){
                                Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_SHORT).show();
                                Intent startIntent = new Intent(getApplicationContext(), SignUpSuccess.class);
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
}
