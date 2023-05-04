package com.example.kooldocandroidfinal.EmployeeHistory;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.kooldocandroidfinal.AppLogin.UserLoginActivity;
import com.example.kooldocandroidfinal.Constant;
import com.example.kooldocandroidfinal.CustomerHistory.HistoryActivity;
import com.example.kooldocandroidfinal.Register.PropertytypeActivity;
import com.squareup.picasso.Picasso;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kooldocandroidfinal.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;


public class ServiceUpdate extends AppCompatActivity {

    private Button addImages,btnSubmit;
    private ImageView showImage;
    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri mImageUri;
    int idValue;
    private Uri uri;
    String ApiURL;
    Spinner spinnerOne;
    TextView status;
    String tableID;

    ArrayAdapter<String> adapterone;
    int position;
    private ActivityResultLauncher<Intent> imagePickLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_update);

        addImages = findViewById(R.id.btn_addImages);
        showImage = findViewById(R.id.show_img);
        btnSubmit = findViewById(R.id.btn_submit);
        status = findViewById(R.id.txt_status);


        Intent intent = getIntent();
        tableID = intent.getExtras().getString("tableID");


        ApiURL = Constant.SERVICEREPORT + tableID;
        imagePickLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            if (data != null) {
                                // Get the URI of the selected image
                                uri = data.getData();

                                // Do something with the URI, such as load the image into an ImageView
                                ImageView imageView = findViewById(R.id.show_img);
                                imageView.setImageURI(uri);
                            }
                        }
                    }
                });



        addImages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                // Launch the image pick activity
                imagePickLauncher.launch(intent);

            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    uploadImage(mImageUri,idValue);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                uploadStatus();


            }
        });


    }

    private void uploadStatus() {
        String servStatus = status.getText().toString();
        StringRequest request = new StringRequest(Request.Method.POST, ApiURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    Toast.makeText(ServiceUpdate.this, "Service Updated", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ServiceUpdate.this, ServHistoryActivity.class);
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ServiceUpdate.this, "Service Updated", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ServiceUpdate.this, ServHistoryActivity.class);
                startActivity(intent);
            }
        }) {


            @androidx.annotation.Nullable
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> map = new HashMap<>();

                map.put("service_status", servStatus);

                return map;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(ServiceUpdate.this);
        requestQueue.add(request);
    }

    private void uploadImage(Uri mImageUri, int idValue)throws JSONException {

        // Get the image data as a byte array
        byte[] imageData = getImageData(uri);

        // Convert the image data to a Base64-encoded string
        String imageDataString = Base64.encodeToString(imageData, Base64.NO_WRAP);
        Log.d("tag", imageDataString);
//        Toast.makeText(getContext(), imageDataString, Toast.LENGTH_SHORT).show();


        JSONObject jsonRequest = new JSONObject();
        jsonRequest.put("image", imageDataString);

        JsonObjectRequest imageRequest = new JsonObjectRequest(Request.Method.POST, ApiURL, jsonRequest,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {


                        Toast.makeText(ServiceUpdate.this, "Success", Toast.LENGTH_SHORT).show();
                        // handle success response here
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // handle error response here
                Toast.makeText(ServiceUpdate.this, "Failed", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        }){

            @Nullable
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> map = new HashMap<>();

                map.put("service_report", imageDataString);

                return map;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(ServiceUpdate.this);
        requestQueue.add(imageRequest);

    }

    private void openFileChooser() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        imagePickLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
//                new ActivityResultCallback<ActivityResult>() {
//                    @Override
//                    public void onActivityResult(ActivityResult result) {
//                        if (result.getResultCode() == Activity.RESULT_OK) {
//                            Intent data = result.getData();
//                            if (data != null) {
//                                // Get the URI of the selected image
//                                uri = data.getData();
//
//                                // Do something with the URI, such as load the image into an ImageView
//                                ImageView imageView = findViewById(R.id.show_img);
//                                imageView.setImageURI(uri);
//
//
//                            }
//                        }
//                    }
//                });
//    }

    private byte[] getImageData(Uri uri) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            InputStream inputStream = getContentResolver().openInputStream(uri);
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputStream.toByteArray();
    }
}