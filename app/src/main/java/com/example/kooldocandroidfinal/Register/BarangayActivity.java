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

public class BarangayActivity extends AppCompatActivity {

    ImageView btnBack,btnSkip;
    Button btnNext;
    EditText txtBarangay;
    String barangay;

    String getFirstname,getLastname,getEmail,getPassword,getConfirmPass,getPhone;
    String street;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barangay);


        btnBack = findViewById(R.id.btn_back);
        btnNext = findViewById(R.id.btnProceed);
        txtBarangay= findViewById(R.id.txt_barangayAddress);
        btnSkip = findViewById(R.id.btnSkip);

        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BarangayActivity.this, UserLoginActivity.class);
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

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                barangay = txtBarangay.getText().toString();
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

        if(TextUtils.isEmpty(barangay)){
            txtBarangay.setError("Please input your first name");
            txtBarangay.requestFocus();
        }else{
            toSend();
        }

    }

    private void toSend() {

        Intent intent = new Intent(BarangayActivity.this,CityActivity.class);

        intent.putExtra("street",street);
        intent.putExtra("barangay",barangay);

        intent.putExtra("phoneNumber",getPhone);
        intent.putExtra("getConfirmPass",getConfirmPass);
        intent.putExtra("getPassword",getPassword);
        intent.putExtra("getEmail",getEmail);
        intent.putExtra("getFirstname",getFirstname);
        intent.putExtra("getLastname",getLastname);
        startActivity(intent);
    }

}