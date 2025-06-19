package com.example.registration;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class ProfileContractor extends AppCompatActivity {
    private Button PersonalButton;
    private final int GALLERY_REQ_CODE = 1000;
    ImageView imageView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_contractor);

        PersonalButton = findViewById(R.id.button9);
        PersonalButton.setOnClickListener(v -> {
            Intent i = new Intent(ProfileContractor.this, PersonalInformationContractor.class);
            startActivity(i);
        });

        imageView3 = findViewById(R.id.imageView); // Corrected imageView ID
        ImageButton imageButton2 = findViewById(R.id.imageButton4);

        // Initialize the ActivityResultLauncher
        ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        // Handle the result here
                        Intent data = result.getData();
                        if (data != null) {
                            // FOR GALLERY
                            imageView3.setImageURI(data.getData());
                        }
                    }
                });

        // Set click listener for imageButton2
        imageButton2.setOnClickListener(v -> {
            // Create intent for gallery
            Intent iGallery = new Intent(Intent.ACTION_PICK);
            iGallery.setType("image/*"); // Set the type to images only
            // Launch activity for result using ActivityResultLauncher
            someActivityResultLauncher.launch(iGallery);
        });

    }
}
