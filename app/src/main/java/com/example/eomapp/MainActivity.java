package com.example.eomapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.graphics.Paint.UNDERLINE_TEXT_FLAG;

public class MainActivity extends AppCompatActivity {
    EditText e1, p1;
    String email, password;
    private CheckBox saveLoginCheckBox;
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    private Boolean saveLogin;
    TextView signUp;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1 = findViewById(R.id.emailText);
        p1 = findViewById(R.id.passwordText);
        saveLoginCheckBox = findViewById(R.id.rememberCheckBox);
        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();
        saveLogin = loginPreferences.getBoolean("saveLogin", false);
        if (saveLogin) {
            e1.setText(loginPreferences.getString("username", ""));
            p1.setText(loginPreferences.getString("password", ""));
            saveLoginCheckBox.setChecked(true);
        }
        signUp = findViewById(R.id.signUp);
        //signUp.setPaintFlags(signUp.getPaintFlags() | UNDERLINE_TEXT_FLAG);
        Button login = findViewById(R.id.loginButton);
        db = new DatabaseHelper(this);
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
                email = e1.getText().toString();
                password = p1.getText().toString();
                if (saveLoginCheckBox.isChecked()) {
                    loginPrefsEditor.putBoolean("saveLogin", true);
                    loginPrefsEditor.putString("username", email);
                    loginPrefsEditor.putString("password", password);
                    loginPrefsEditor.commit();
                } else {
                    loginPrefsEditor.clear();
                    loginPrefsEditor.commit();
                }
                Boolean checkLogin = db.loginCheck(email, password);
                if (checkLogin) {
                    Intent startIntent2 = new Intent(getApplicationContext(), HomeFeed.class);
                    startActivity(startIntent2);
                } else {
                    Toast.makeText(getApplicationContext(), "Incorrect Email or Password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
