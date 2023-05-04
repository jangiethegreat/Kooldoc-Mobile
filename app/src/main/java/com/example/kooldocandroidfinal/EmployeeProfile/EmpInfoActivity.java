package com.example.kooldocandroidfinal.EmployeeProfile;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.kooldocandroidfinal.AppLogin.UserLoginActivity;
import com.example.kooldocandroidfinal.Constant;
import com.example.kooldocandroidfinal.ImageViewLargeActivity;
import com.example.kooldocandroidfinal.R;
import com.google.android.gms.common.api.Api;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class EmpInfoActivity extends AppCompatActivity {

    TextView txtEmail,txtPhone,txtAge,txtExpe,txtSpecial,txtFullname;

    String ApiURL,image;
    int idValue;
    ImageView btnBack;
    TextView btnLogout;

    ImageView uploadPhoto,displayPhoto;
    private ActivityResultLauncher<Intent> imagePickLauncher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_info);



        SharedPreferences sharedPreferences = getSharedPreferences("userProf",MODE_PRIVATE);
        idValue = sharedPreferences.getInt("id", 0);

        ApiURL = Constant.GETTECHNICIANINFO + idValue;
        txtEmail = findViewById(R.id.txt_showEmail);
        txtPhone = findViewById(R.id.txt_showPhone);
        txtAge = findViewById(R.id.txt_showAge);
        txtExpe = findViewById(R.id.txt_showExperience);
        txtSpecial = findViewById(R.id.txt_showSpecialties);
        txtFullname = findViewById(R.id.txt_fullName);
        btnLogout = findViewById(R.id.btn_logout);
        btnBack = findViewById(R.id.btn_goback);
        displayPhoto = findViewById(R.id.img_picture);
        uploadPhoto = findViewById(R.id.upload_img);

        getEmpInfo();

        // Initialize the image pick launcher
        imagePickLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            if (data != null) {
                                // Get the URI of the selected image
                                Uri uri = data.getData();

                                // Do something with the URI, such as load the image into an ImageView
                                ImageView imageView = findViewById(R.id.img_picture);
                                imageView.setImageURI(uri);

                                // Make an API request to upload the image
                                try {
                                    uploadImage(uri,idValue);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        }
                    }
                });

        uploadPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                // Launch the image pick activity
                imagePickLauncher.launch(intent);
            }
        });

        displayPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmpInfoActivity.this, ImageViewLargeActivity.class);
                intent.putExtra("image_url", "https://kooldocbusiness.com/"+ image);
                startActivity(intent);
            }
        });
        SharedPreferences logoutPreference = getSharedPreferences("LoginFile",MODE_PRIVATE);
        SharedPreferences.Editor logoutEditor = logoutPreference.edit();
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutEditor.putString("isLoggedIn","false");
                logoutEditor.commit();

                startActivity(new Intent(EmpInfoActivity.this, UserLoginActivity.class));
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });



    }

    private void getEmpInfo() {

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        StringRequest request = new StringRequest(Request.Method.GET, ApiURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject object = jsonObject.getJSONObject("technician_data");


                            String id = String.valueOf(object.getInt("id"));
                            String fullname = object.getString("full_name");
                            String email = object.getString("email");
                            String phone_number = object.getString("phone_number");
                            String age = object.getString("age");
                            String experience = object.getString("experience");
                            String specialties = object.getString("specialties");


                            image = object.getString("image");
                            if (image=="null"){

                                Glide.with(EmpInfoActivity.this)
                                        .load(R.drawable.ic_person)
                                        .into(displayPhoto);} else{
                                Glide.with(EmpInfoActivity.this)
                                        .load("https://kooldocbusiness.com/"+ image)
                                        .apply(RequestOptions.circleCropTransform())
                                        .into(displayPhoto);
                            }

                            txtFullname.setText(fullname.substring(0,1).toUpperCase()+ fullname.substring(1));
                            txtEmail.setText(email.substring(0,1).toUpperCase()+ email.substring(1));
                            txtPhone.setText("Phone Number:  " +phone_number.substring(0,1).toUpperCase()+ phone_number.substring(1));
                            txtAge.setText("Age:  " +age.substring(0,1).toUpperCase()+ age.substring(1));
                            txtExpe.setText("Experience:  " +experience.substring(0,1).toUpperCase()+ experience.substring(1));
                            txtSpecial.setText("Specialties:  " +specialties.substring(0,1).toUpperCase()+ specialties.substring(1));


                            progressDialog.dismiss();
                        }catch (JSONException e){
                            e.printStackTrace();
                        }



                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(EmpInfoActivity.this, "Errorr", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);

    }

    private void uploadImage(Uri uri, int idValue) throws JSONException {

        // Get the image data as a byte array
        byte[] imageData = getImageData(uri);

        // Convert the image data to a Base64-encoded string
        String imageDataString = Base64.encodeToString(imageData, Base64.NO_WRAP);
        Log.d("tag", imageDataString);
//        Toast.makeText(getContext(), imageDataString, Toast.LENGTH_SHORT).show();
        String url = "https://kooldocbusiness.com/api/technician-image/" + idValue;

        JSONObject jsonRequest = new JSONObject();
        jsonRequest.put("image", imageDataString);

        JsonObjectRequest imageRequest = new JsonObjectRequest(Request.Method.POST, url, jsonRequest,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {


                        Toast.makeText(EmpInfoActivity.this, "Success", Toast.LENGTH_SHORT).show();
                        // handle success response here
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // handle error response here
                Toast.makeText(EmpInfoActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        }){

            @Nullable
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> map = new HashMap<>();

                map.put("image", imageDataString);
                return map;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(EmpInfoActivity.this);
        requestQueue.add(imageRequest);

    }

    private byte[] getImageData(Uri uri) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            InputStream inputStream = EmpInfoActivity.this.getContentResolver().openInputStream(uri);
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