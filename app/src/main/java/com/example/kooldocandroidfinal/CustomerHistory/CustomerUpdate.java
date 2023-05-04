package com.example.kooldocandroidfinal.CustomerHistory;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.kooldocandroidfinal.Cleaning.ConfirmActivity;
import com.example.kooldocandroidfinal.Constant;
import com.example.kooldocandroidfinal.Installation.installReceipt;
import com.example.kooldocandroidfinal.R;
import com.example.kooldocandroidfinal.Register.PropertytypeActivity;
import com.example.kooldocandroidfinal.Register.RegistrationSuccess;
import com.example.kooldocandroidfinal.UserProfile.UserFragment;
import com.google.android.gms.common.api.Api;

import java.util.HashMap;
import java.util.Map;

public class CustomerUpdate extends AppCompatActivity {

    ImageView btnSkip,btnBack;

    EditText Ustreet,Ubarangay,Ucity,Uprovince,Uzipcode;
    Spinner spinnerOne;
    String[] spinnerlist;
    ArrayAdapter<String> adapterone;
    String ApiURL;
    Button btnProceed;
    int intValue;
    String street,barangay,city,province,zipcode,property;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_update);

        btnSkip= findViewById(R.id.btnSkip);
        btnBack = findViewById(R.id.btn_back);
        spinnerOne = findViewById(R.id.spinner_one);

        Ustreet = findViewById(R.id.street_update);
        Ubarangay = findViewById(R.id.barangay_update);
        Ucity = findViewById(R.id.city_update);
        Uprovince = findViewById(R.id.province_update);
        Uzipcode = findViewById(R.id.zipcode_update);

        btnProceed = findViewById(R.id.btnProceed);
        SharedPreferences sharedPreferences = getSharedPreferences("userProf",MODE_PRIVATE);
        intValue = sharedPreferences.getInt("id", 0);
        ApiURL = Constant.CUSTOMERUPDATE + intValue;


        spinnerlist = new String[]{"Select property type","Condo", "Apartment","House | Townhouse","Small business | Store","Office Building","Warehouse"};
        adapterone = new ArrayAdapter<>(this, R.layout.spinner_layout, spinnerlist);
        spinnerOne.setAdapter(adapterone);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerUpdate.this, UserFragment.class);
                startActivity(intent);
            }
        });
        btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                street = Ustreet.getText().toString().trim();
                barangay = Ubarangay.getText().toString().trim();
                city = Ucity.getText().toString().trim();
                province = Uprovince.getText().toString().trim();
                zipcode = Uzipcode.getText().toString().trim();
                property = spinnerOne.getSelectedItem().toString().trim();

                toSend();
            }
        });
    }

    private void toSend() {

        String sendStreet = street;
        String sendBarangay = barangay;
        String sendCity = city;
        String sendProvince = province;
        String sendZipcode = zipcode;
        String sendProperty = property;

        StringRequest request = new StringRequest(Request.Method.PUT,ApiURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    Intent intent = new Intent(CustomerUpdate.this, UserFragment.class);
                    startActivity(intent);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CustomerUpdate.this, "Success", Toast.LENGTH_SHORT).show();
            }
        }){


            @Nullable
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> map = new HashMap<>();

                map.put("address",sendStreet);
                map.put("barangay",sendBarangay);
                map.put("city",sendCity);
                map.put("province",sendProvince);
                map.put("zip_code",sendZipcode);
                map.put("property_type",sendProperty);

                return map;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);

    }
}