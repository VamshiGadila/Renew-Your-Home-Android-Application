package com.example.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class EngineerRegistration extends AppCompatActivity {
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_engineer_registration);
        loginButton = findViewById(R.id.button5);
        loginButton.setOnClickListener(v -> {
            Intent i = new Intent(EngineerRegistration .this, loginform.class);
            startActivity(i);

            Spinner spinner = findViewById(R.id.spinnerGender);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    AdapterView<Adapter> adapterView = null;
                    String item = adapterView.getItemAtPosition(position).toString();
                    Toast.makeText(EngineerRegistration.this,"Selected Item: " + item,Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.add("Male");
            arrayList.add("Female");
            arrayList.add("others");
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arrayList);
            adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
            spinner.setAdapter(adapter);
        });
    }
}
