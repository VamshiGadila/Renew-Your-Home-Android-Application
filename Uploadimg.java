package com.example.registration;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;






public class Uploadimg extends AppCompatActivity {
    private static final String TAG = "Uploadimg";
    private static final String UPLOAD_URL = "http://your_server/uploadImage";

    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri selectedImageUri;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploadimg);
        Button btnUpload = findViewById(R.id.btnUpload);
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }

        });
}
    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
            selectedImageUri = data.getData();
            new UploadImageTask().execute();
        }
    }
    private class UploadImageTask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... voids) {
            try {
                // Create a JSON object with any additional data you want to send
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("key", "value");
                // Create a URL object pointing to the servlet
                URL url = new URL(UPLOAD_URL);

                // Create a connection to the servlet
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);

                // Write the JSON object to the output stream
                DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
                outputStream.writeBytes(jsonObject.toString());
                outputStream.flush();
                outputStream.close();
                // Upload the image file
                FileInputStream fileInputStream = new FileInputStream(new File(getRealPathFromURI(selectedImageUri)));
                connection.setRequestProperty("Content-Type", "image/jpeg");
                connection.setRequestProperty("Content-Disposition", "attachment; filename=\"" + selectedImageUri.getLastPathSegment() + "\"");
                outputStream = new DataOutputStream(connection.getOutputStream());
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                outputStream.flush();
                outputStream.close();
                fileInputStream.close();

                // Get the response code
                int responseCode = connection.getResponseCode();
                Log.d(TAG, "Response Code: " + responseCode);

                // Check if upload was successful
                return responseCode == HttpURLConnection.HTTP_OK;

            } catch (IOException | JSONException e) {
                e.printStackTrace();
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if (success) {
                Log.d(TAG, "Image uploaded successfully");
                // Handle successful upload
            } else {
                Log.e(TAG, "Error uploading image");
                // Handle upload failure
            }
        }
    }

    private String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = (Cursor) getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) { // Source is Dropbox or other similar local file path
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }
}












