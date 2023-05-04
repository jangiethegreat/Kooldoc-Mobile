package com.example.kooldocandroidfinal.EmployeeHistory;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.kooldocandroidfinal.Cleaning.ConfirmActivity;
import com.example.kooldocandroidfinal.Constant;
import com.example.kooldocandroidfinal.CustomerHistory.HistoryActivity;
import com.example.kooldocandroidfinal.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;


public class ServiceStatus extends Fragment {

    private Button addImages,btnSubmit;
    private ImageView showImage;
    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri uri;

    String ApiURL;
    int idValue;
    private static final int REQUEST_IMAGE_PICK=1;

    private ActivityResultLauncher<Intent> imagePickLauncher;

    Spinner spinnerOne;
    String[]  serviceStatus;
    ArrayAdapter<String> adapterone;

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
                                uri = data.getData();

                                // Do something with the URI, such as load the image into an ImageView
                                ImageView imageView = getView().findViewById(R.id.show_img);
                                imageView.setImageURI(uri);


                            }
                        }
                    }
                });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_service_status, container, false);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("userProf",MODE_PRIVATE);
//        idValue = HistoryActivity.CostumerArrayList.get(position).getId());

        Bundle bundle = getArguments();
        if (bundle != null) {
            int idValue = bundle.getInt("position");
            // Do something with the ID value
        }

        spinnerOne = view.findViewById(R.id.spinner_status);
        addImages = view.findViewById(R.id.btn_addImages);
        btnSubmit = view.findViewById(R.id.btn_submit);
        ApiURL = Constant.SERVICEREPORT + 28;
        serviceStatus = new String[]{"Finished"};
        adapterone = new ArrayAdapter<>(getActivity(), R.layout.spinner_layout, serviceStatus);
        spinnerOne.setAdapter(adapterone);

        addImages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                // Launch the image pick activity
                imagePickLauncher.launch(intent);

//                uploadStatus();


            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                // Make an API request to upload the image
                try {
                    uploadImage(uri,idValue);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                uploadStatus();


            }
        });

        return view;
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

        JsonObjectRequest imageRequest = new JsonObjectRequest(Request.Method.POST, ApiURL, jsonRequest,
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

                map.put("service_report", imageDataString);
               
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

    private void uploadStatus() {

        String servStatus = spinnerOne.getSelectedItem().toString();
        StringRequest request = new StringRequest(Request.Method.POST, ApiURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    Toast.makeText(getActivity(), "Service Updated", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "An error occurred", Toast.LENGTH_SHORT).show();
            }
        }) {


            @Nullable
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> map = new HashMap<>();

                map.put("service_status", servStatus);

                return map;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(request);
    }


}