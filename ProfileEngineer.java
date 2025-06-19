package com.example.registration;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class ProfileEngineer extends AppCompatActivity {
    private Button PersonalInfoButton;
    private final int GALLERY_REQ_CODE = 1000;
    ImageView imageView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_engineer);

        PersonalInfoButton = findViewById(R.id.button12);
        PersonalInfoButton.setOnClickListener(v -> {
            Intent i = new Intent(ProfileEngineer.this, PersonalInformationEngineer.class);
            startActivity(i);
        });

        imageView1 = findViewById(R.id.imageView2);
        ImageButton imageButton3 = findViewById(R.id.imageButton3);

        // Initialize the ActivityResultLauncher
        ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        // Handle the result here
                        Intent data = result.getData();
                        if (data != null) {
                            // FOR GALLERY
                            imageView1.setImageURI(data.getData());
                        }
                    }
                });

        // Set click listener for imageButton3
        imageButton3.setOnClickListener(v -> {
            // Create intent for gallery
            Intent iGallery = new Intent(Intent.ACTION_PICK);
            iGallery.setType("image/*"); // Set the type to images only
            // Launch activity for result using ActivityResultLauncher
            someActivityResultLauncher.launch(iGallery);
        });
    }
}
