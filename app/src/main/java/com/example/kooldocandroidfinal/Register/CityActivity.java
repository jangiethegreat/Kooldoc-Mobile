package com.example.kooldocandroidfinal.Register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.kooldocandroidfinal.AppLogin.UserLoginActivity;
import com.example.kooldocandroidfinal.R;

public class CityActivity extends AppCompatActivity {


    ImageView btnBack,btnSkip;
    Button btnNext;
    EditText txtCity;
    String city;

    String getFirstname,getLastname,getEmail,getPassword,getConfirmPass,getPhone;
    String street,barangay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);

        btnBack = findViewById(R.id.btn_back);
        btnNext = findViewById(R.id.btnProceed);
        txtCity = findViewById(R.id.txt_city);

        btnSkip = findViewById(R.id.btnSkip);

        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CityActivity.this, UserLoginActivity.class);
                startActivity(intent);
            }
        });
        Intent intent = getIntent();
        getFirstname = intent.getStringExtra("getFirstname");
        getLastname = intent.getStringExtra("getLastname");
        getEmail = intent.getStringExtra("getEmail");
        getPassword = intent.getStringExtra("getPassword");
        getConfirmPass = intent.getStringExtra("getConfirmPass");
        getPhone = intent.getStringExtra("phoneNumber");

        street = intent.getStringExtra("street");
        barangay = intent.getStringExtra("barangay");

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                city = txtCity.getText().toString();
                edtFilter();

            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
    private void edtFilter() {

        if(TextUtils.isEmpty(city)){
            txtCity.setError("Please input your first name");
            txtCity.requestFocus();
        }else{
            toSend();
        }

    }

    private void toSend() {
        Intent intent = new Intent(CityActivity.this,ProvinceActivity.class);

        intent.putExtra("street",street);
        intent.putExtra("barangay",barangay);
        intent.putExtra("city",city);

        intent.putExtra("phoneNumber",getPhone);
        intent.putExtra("getConfirmPass",getConfirmPass);
        intent.putExtra("getPassword",getPassword);
        intent.putExtra("getEmail",getEmail);
        intent.putExtra("getFirstname",getFirstname);
        intent.putExtra("getLastname",getLastname);
        startActivity(intent);

    }
}