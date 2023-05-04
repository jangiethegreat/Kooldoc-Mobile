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

public class EmailActivity extends AppCompatActivity {
    ImageView btnBack,btnSkip;
    Button btnNext;

    EditText txtemailAddress;
    String emailAddress;
    String getFirstname,getLastname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);


        btnBack = findViewById(R.id.btn_back);
        btnNext = findViewById(R.id.btnProceed);
        txtemailAddress = findViewById(R.id.txt_emailAddress);

        btnSkip = findViewById(R.id.btnSkip);

        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmailActivity.this, UserLoginActivity.class);
                startActivity(intent);
            }
        });


        Intent intent = getIntent();
        getFirstname = intent.getStringExtra("getFirstname");
        getLastname = intent.getStringExtra("lastname");

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailAddress = txtemailAddress.getText().toString();

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

        if(TextUtils.isEmpty(emailAddress)){
            txtemailAddress.setError("Please input your email address");
            txtemailAddress.requestFocus();
        }else{
            toSend();
        }

    }

    private void toSend() {
        Intent intent = new Intent(EmailActivity.this,PasswordActivity.class);
        intent.putExtra("emailAddress",emailAddress);
        intent.putExtra("getFirstname",getFirstname);
        intent.putExtra("getLastname",getLastname);

        startActivity(intent);
    }
}