package com.example.eomapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminCode extends AppCompatActivity {
    private EditText adminText;
    private Button go;
    private String admin;
    private String adminKey;
    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_code);
        adminText=findViewById(R.id.adminText);
        go= findViewById(R.id.enterButton);
        back=findViewById(R.id.backButton);
        admin= adminText.getText().toString();
        adminKey="a1b2c3";
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(admin.equals(adminKey)){
                    Intent startIntent = new Intent(getApplicationContext(), EventInput.class);
                    startActivity(startIntent);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Code is Not Correct", Toast.LENGTH_SHORT).show();
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), HomeFeed.class);
                startActivity(startIntent);
            }
        });
    }
}
