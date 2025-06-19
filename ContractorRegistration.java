package com.example.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class ContractorRegistration extends AppCompatActivity {
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contractor_registration);

            loginButton = findViewById(R.id.button5);
            loginButton.setOnClickListener(v -> {
                Intent i = new Intent(ContractorRegistration.this, loginform.class);
                startActivity(i);
            });
    }
}

