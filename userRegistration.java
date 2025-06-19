package com.example.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class userRegistration extends AppCompatActivity {
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);
        loginButton = findViewById(R.id.button5);
        loginButton.setOnClickListener(v -> {
            Intent i = new Intent(userRegistration.this, loginform.class);
            startActivity(i);
        });
    }
}