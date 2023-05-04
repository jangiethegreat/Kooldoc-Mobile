package com.example.kooldocandroidfinal.UserProfile;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
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
import com.example.kooldocandroidfinal.CustomerHistory.CustomerUpdate;
import com.example.kooldocandroidfinal.CustomerHistory.HistoryActivity;
import com.example.kooldocandroidfinal.EmployeeHistory.ServHistoryActivity;

import com.example.kooldocandroidfinal.Feedback.FeedbackActivity;
import com.example.kooldocandroidfinal.ImageViewLargeActivity;
import com.example.kooldocandroidfinal.R;
import com.example.kooldocandroidfinal.Register.PropertytypeActivity;
import com.example.kooldocandroidfinal.Register.RegistrationSuccess;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class UserFragment extends Fragment {

    TextView txtShowEmail,txtShowPhone,txtShowAddress,txtShowBarangay,txtShowCity,txtShowProvince,txtShowZipcode,txtShowPropertyType,txtShowFullname;
    TextView viewBookHistory,viewCompleted;
    Button btnLogout;
    ImageView uploadPhoto,displayPhoto;

    String id,fullname,email,address,barangay,city,province,zip_code,property_type,image,phone_number;

    String newFullname,newEmail;
    TextView updateNow;


    String ApiURL;
    int idValue;
    private static final int REQUEST_IMAGE_PICK=1;
    private ActivityResultLauncher<Intent> imagePickLauncher;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
                                ImageView imageView = getView().findViewById(R.id.img_picture);
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
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user, container, false);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("userProf",MODE_PRIVATE);
        idValue = sharedPreferences.getInt("id", 0);

        ApiURL = Constant.GETCUSTOMERINFO +idValue;
        updateNow = view.findViewById(R.id.update_now);
        viewBookHistory = view.findViewById(R.id.view_bookingHistory);
        txtShowEmail = view.findViewById(R.id.txt_showEmail);
        txtShowPhone = view.findViewById(R.id.txt_showPhone);
        txtShowAddress = view.findViewById(R.id.txt_showAddress);
        txtShowBarangay = view.findViewById(R.id.txt_showBarangay);
        txtShowCity = view.findViewById(R.id.txt_showCity);
        txtShowProvince = view.findViewById(R.id.txt_showProvince);
        txtShowZipcode = view.findViewById(R.id.txt_showZipcode);
        txtShowPropertyType = view.findViewById(R.id.txt_showPropertyType);
        txtShowFullname = view.findViewById(R.id.txt_fullName);
        btnLogout = view.findViewById(R.id.btn_logout);
        displayPhoto = view.findViewById(R.id.img_picture);
        viewCompleted = view.findViewById(R.id.view_completedBook);
        uploadPhoto = view.findViewById(R.id.upload_img);

        uploadPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);



            }
        });
        viewCompleted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(),FeedbackActivity.class);
                startActivity(intent);
            }
        });

        updateNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(getActivity(), CustomerUpdate.class);
                startActivity(intent);
            }
        });


        SharedPreferences logoutPreference = getActivity().getSharedPreferences("LoginFile",MODE_PRIVATE);
        SharedPreferences.Editor logoutEditor = logoutPreference.edit();

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutEditor.putString("isLoggedIn","false");
                logoutEditor.commit();

                startActivity(new Intent(getActivity(), UserLoginActivity.class));
            }
        });
        viewBookHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HistoryActivity.class);
                startActivity(intent);

            }
        });
        displayPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ImageViewLargeActivity.class);
                intent.putExtra("image_url", "https://kooldocbusiness.com/"+ image);
                startActivity(intent);
            }
        });


        getUserInfo();


//        if (!image.isEmpty()) {
//            byte[] bytes = Base64.decode(image, Base64.DEFAULT);
//            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
//            Drawable drawable = new BitmapDrawable(getResources(), bitmap);
//
//            ImageView imageView = view.findViewById(R.id.img_picture);
//            imageView.setImageDrawable(drawable);
//        }
        return view;
    }

    private void getUserInfo() {
        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        StringRequest request = new StringRequest(Request.Method.GET, ApiURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject object = jsonObject.getJSONObject("customer_data");
                            id = String.valueOf(object.getInt("id"));
                            fullname = object.getString("full_name");
                            phone_number = object.getString("phone_number");
                            email = object.getString("email");
                            address = object.getString("address");
                            barangay = object.getString("barangay");
                            city = object.getString("city");
                            province = object.getString("province");
                            zip_code = object.getString("zip_code");
                            property_type = object.getString("property_type");
                            image = object.getString("image");
                            if (image=="null"){

                                Glide.with(getContext())
                                        .load(R.drawable.image1)
                                        .into(displayPhoto);} else{
                                Glide.with(getContext())
                                        .load("https://kooldocbusiness.com/"+ image)
                                        .apply(RequestOptions.circleCropTransform())
                                        .into(displayPhoto);
                            }


//                            SharedPreferences currentUser = getActivity().getSharedPreferences("currentUserInfo", MODE_PRIVATE);
//                            SharedPreferences.Editor userEditor = currentUser.edit();
//
//                            userEditor.putString("full_name", jsonObject.getString("full_name"));
//                            userEditor.putString("phone_number", jsonObject.getString("phone_number"));
//                            userEditor.putString("email", jsonObject.getString("email"));
//                            userEditor.putString("address", jsonObject.getString("address"));
//                            userEditor.putString("barangay", jsonObject.getString("barangay"));
//                            userEditor.putString("city", jsonObject.getString("city"));
//                            userEditor.putString("province", jsonObject.getString("province"));
//                            userEditor.putString("zip_code", jsonObject.getString("zip_code"));
//                            userEditor.putString("property_type", jsonObject.getString("property_type"));
//                            userEditor.putString("image", jsonObject.getString("image"));
//                            userEditor.apply();


                            txtShowFullname.setText(fullname.substring(0,1).toUpperCase()+ fullname.substring(1));
                            txtShowEmail.setText( email);
                            txtShowPhone.setText("Phone:  " + phone_number);
                            txtShowAddress.setText("Street:   " + address.substring(0,1).toUpperCase()+ address.substring(1));
                            txtShowBarangay.setText("Barangay:  " + barangay.substring(0,1).toUpperCase()+ barangay.substring(1));
                            txtShowCity.setText("City:   " + city.substring(0,1).toUpperCase()+ city.substring(1));
                            txtShowProvince.setText("Province:   " + province.substring(0,1).toUpperCase()+ province.substring(1));
                            txtShowZipcode.setText("Zip Code:   " + zip_code);
                            txtShowPropertyType.setText("Property Type:  " + property_type.substring(0,1).toUpperCase()+ property_type.substring(1));



                            progressDialog.dismiss();
                        }catch (JSONException e){
                            e.printStackTrace();
                        }



                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Errorr", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(request);

    }



    private void chooseFile(){

    }



    private void uploadImage(Uri uri, int idValue) throws JSONException {

        // Get the image data as a byte array
        byte[] imageData = getImageData(uri);

        // Convert the image data to a Base64-encoded string
        String imageDataString = Base64.encodeToString(imageData, Base64.NO_WRAP);
        Log.d("tag", imageDataString);
//        Toast.makeText(getContext(), imageDataString, Toast.LENGTH_SHORT).show();
        String url = "https://kooldocbusiness.com/api/custimage/" + idValue;

        JSONObject jsonRequest = new JSONObject();
        jsonRequest.put("image", imageDataString);

        JsonObjectRequest imageRequest = new JsonObjectRequest(Request.Method.POST, url, jsonRequest,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {


                        Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                        // handle success response here
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // handle error response here
                Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
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

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(imageRequest);

    }

    private byte[] getImageData(Uri uri) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            InputStream inputStream = getContext().getContentResolver().openInputStream(uri);
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


