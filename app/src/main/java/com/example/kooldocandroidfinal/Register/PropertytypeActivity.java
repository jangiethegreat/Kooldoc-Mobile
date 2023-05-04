package com.example.kooldocandroidfinal.Register;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.kooldocandroidfinal.AppLogin.UserLoginActivity;
import com.example.kooldocandroidfinal.AppLogin.UserRegisterActivity;
import com.example.kooldocandroidfinal.Constant;
import com.example.kooldocandroidfinal.R;

import java.util.HashMap;
import java.util.Map;

public class PropertytypeActivity extends AppCompatActivity {


    ImageView btnBack,btnSkip;
    Button btnNext;

    EditText txtpropertyType;
    String propertytype;

    String getFirstname,getLastname,getEmail,getPassword,getConfirmPass,getPhone;
    String street,barangay,city,province,zipcode;

    Spinner spinnerOne;
    String[]  propertyType;
    ArrayAdapter<String> adapterone;

    TextView text1,text2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_propertytype);




        Intent intent = getIntent();
        getFirstname = intent.getStringExtra("getFirstname");
        getLastname = intent.getStringExtra("getLastname");
        getEmail = intent.getStringExtra("getEmail");
        getPassword = intent.getStringExtra("getPassword");
        getConfirmPass = intent.getStringExtra("getConfirmPass");
        getPhone = intent.getStringExtra("phoneNumber");

        street = intent.getStringExtra("street");
        barangay = intent.getStringExtra("barangay");
        city = intent.getStringExtra("city");
        province = intent.getStringExtra("province");
        zipcode = intent.getStringExtra("zipcode");





        btnBack = findViewById(R.id.btn_back);
        btnNext = findViewById(R.id.btnProceed);
        spinnerOne = findViewById(R.id.spinner_one);
//        txtpropertyType = findViewById(R.id.txtproper)

        propertyType = new String[]{"Select property type","Condo", "Apartment","House | Townhouse","Small business | Store","Office Building","Warehouse"};
        adapterone = new ArrayAdapter<>(this, R.layout.spinner_layout, propertyType);
        spinnerOne.setAdapter(adapterone);

        //------------------------------------------------------------//


        btnSkip = findViewById(R.id.btnSkip);

        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PropertytypeActivity.this, UserLoginActivity.class);
                startActivity(intent);
            }
        });


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SendData();
            }
        });


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    private void SendData() {

        propertytype =spinnerOne.getSelectedItem().toString();

        String sendProperty = propertytype;
        String sendZipcode = zipcode;
        String sendProvince = province;
        String sendCity = city;
        String sendBarangay = barangay;
        String sendStreet = street;

        String sendPhone = getPhone;
        String sendConfirmPass = getConfirmPass;
        String sendPassword = getPassword;
        String sendEmail = getEmail;
        String sendLastname = getLastname;
        String sendFirstname = getFirstname;



//        ProgressDialog progressDialog = new ProgressDialog(this);
//        progressDialog.setMessage("Loading...");
//
//        progressDialog.show();

        StringRequest request = new StringRequest(Request.Method.POST, Constant.REGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(PropertytypeActivity.this, "Registration Success", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(PropertytypeActivity.this, RegistrationSuccess.class);
                            startActivity(intent);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(PropertytypeActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();


            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {


                Map<String,String> params = new HashMap<String,String>();

                params.put("first_name", sendFirstname);
                params.put("last_name", sendLastname);

                params.put("email", sendEmail);
                params.put("password", sendPassword);
                params.put("password_confirmation", sendConfirmPass);
                params.put("phone_number", sendPhone);
                params.put("address", sendStreet);
                params.put("barangay", sendBarangay);
                params.put("city", sendCity);
                params.put("province", sendProvince);
                params.put("zip_code", sendZipcode);
                params.put("property_type", sendProperty);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(PropertytypeActivity.this);
        requestQueue.add(request);


    }
}