package com.example.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class PersonalInformationContractor extends AppCompatActivity {
    private Button editprofilebutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalinformationcontractor);
        editprofilebutton = findViewById(R.id.button12);
        editprofilebutton.setOnClickListener(v -> {
            Intent i = new Intent( PersonalInformationContractor.this, Editprofile.class);
            startActivity(i);
    });
    }

    }
