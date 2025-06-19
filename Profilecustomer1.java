package com.example.registration;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import static android.app.Activity.RESULT_OK;

public class Profilecustomer1 extends AppCompatActivity {
    private Button Persoanlinformationbutton;
    private final int GALLERY_REQ_CODE = 1000;
    ImageView imageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilecustomer1);

        Persoanlinformationbutton = findViewById(R.id.button3);
        Persoanlinformationbutton.setOnClickListener(v -> {
            Intent i = new Intent(Profilecustomer1.this, personalInformationCustomer.class);
            startActivity(i);
        });

        imageView2 = findViewById(R.id.imageView2);
        ImageButton imageButton2 = findViewById(R.id.imageButton2);

        // Initialize the ActivityResultLauncher
        ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        // Handle the result here
                        Intent data = result.getData();
                        if (data != null) {
                            // FOR GALLERY
                            imageView2.setImageURI(data.getData());
                        }
                    }
                });

        // Set click listener for imageButton2
        imageButton2.setOnClickListener(v -> {
            // Create intent for gallery
            Intent iGallery = new Intent(Intent.ACTION_PICK);
            iGallery.setType("image/*"); // Set the type to images only
            // Launch activity for result using ActivityResultLauncher
            someActivityResultLauncher.launch(iGallery); // Use launch() instead of ()
        });

    }
}
