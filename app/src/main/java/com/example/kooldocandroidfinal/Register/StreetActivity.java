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

public class StreetActivity extends AppCompatActivity {

    ImageView btnBack,btnSkip;
    Button btnNext;
    EditText txtStreet;
    String street;
    String getFirstname,getLastname,getEmail,getPassword,getConfirmPass,getPhone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_street);

        btnBack = findViewById(R.id.btn_back);
        btnNext = findViewById(R.id.btnProceed);
        txtStreet = findViewById(R.id.txt_streetAddress);


        Intent intent = getIntent();
        getFirstname = intent.getStringExtra("getFirstname");
        getLastname = intent.getStringExtra("getLastname");
        getEmail = intent.getStringExtra("getEmail");
        getPassword = intent.getStringExtra("getPassword");
        getConfirmPass = intent.getStringExtra("getConfirmPass");
        getPhone = intent.getStringExtra("phoneNumber");
        btnSkip = findViewById(R.id.btnSkip);

        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StreetActivity.this, UserLoginActivity.class);
                startActivity(intent);
            }
        });


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                street = txtStreet.getText().toString();
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

        if(TextUtils.isEmpty(street)){
            txtStreet.setError("Please input your first name");
            txtStreet.requestFocus();
        }else{
            toSend();
        }

    }

    private void toSend() {

        Intent intent = new Intent(StreetActivity.this,BarangayActivity.class);
        intent.putExtra("street",street);

        intent.putExtra("phoneNumber",getPhone);
        intent.putExtra("getConfirmPass",getConfirmPass);
        intent.putExtra("getPassword",getPassword);
        intent.putExtra("getEmail",getEmail);
        intent.putExtra("getFirstname",getFirstname);
        intent.putExtra("getLastname",getLastname);
        startActivity(intent);
    }


}