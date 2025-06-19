package com.example.registration;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;


import com.example.registration.R;

public class Statusuploading extends AppCompatActivity {

    private final int CAMERA_REQ_CODE = 100;
    private ActivityResultLauncher<Intent> mStartForResult;
    ImageView imgCamera;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statusuploading);

        imgCamera=findViewById(R.id.imgCamera);
        Button btnCamera=findViewById(R.id.btnCamera);




        mStartForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK) {
                Intent data = result.getData();
                if (data != null) {
                    Bitmap img = (Bitmap) data.getExtras().get("data");
                    imgCamera.setImageBitmap(img);
                }
            }
        });

        btnCamera.setOnClickListener(v -> {
            Intent iCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            mStartForResult.launch(iCamera);
        });
    }
}
