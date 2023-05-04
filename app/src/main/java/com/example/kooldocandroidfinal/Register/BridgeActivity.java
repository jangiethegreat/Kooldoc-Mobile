package com.example.kooldocandroidfinal.Register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.kooldocandroidfinal.AppLogin.UserLoginActivity;
import com.example.kooldocandroidfinal.R;

public class BridgeActivity extends AppCompatActivity {
    ImageView btnBack,btnSkip;
    Button btnNext;


    String getFirstname,getLastname,getEmail,getPassword,getConfirmPass,getPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bridge);

        btnBack = findViewById(R.id.btn_back);
        btnNext = findViewById(R.id.btnProceed);

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
                Intent intent = new Intent(BridgeActivity.this, UserLoginActivity.class);
                startActivity(intent);
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BridgeActivity.this,StreetActivity.class);
                intent.putExtra("phoneNumber",getPhone);
                intent.putExtra("getConfirmPass",getConfirmPass);
                intent.putExtra("getPassword",getPassword);
                intent.putExtra("getEmail",getEmail);
                intent.putExtra("getFirstname",getFirstname);
                intent.putExtra("getLastname",getLastname);
                startActivity(intent);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}