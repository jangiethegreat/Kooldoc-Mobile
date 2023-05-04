package com.example.kooldocandroidfinal.Register;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.kooldocandroidfinal.AppLogin.UserLoginActivity;
import com.example.kooldocandroidfinal.Cleaning.ConfirmActivity;
import com.example.kooldocandroidfinal.R;

public class PhoneActivity extends AppCompatActivity {
    ImageView btnBack,btnSkip;
    Button btnNext;

    EditText txtphoneNumber;
    String phoneNumber;

    String getFirstname,getLastname,getEmail,getPassword,getConfirmPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);

        btnBack = findViewById(R.id.btn_back);
        btnNext = findViewById(R.id.btnProceed);
        txtphoneNumber = findViewById(R.id.txt_phoneNumber);
        btnSkip = findViewById(R.id.btnSkip);

        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PhoneActivity.this, UserLoginActivity.class);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        getFirstname = intent.getStringExtra("getFirstname");
        getLastname = intent.getStringExtra("getLastname");
        getEmail = intent.getStringExtra("getEmail");
        getPassword = intent.getStringExtra("getPassword");
        getConfirmPass = intent.getStringExtra("confirmPass");




        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneNumber = txtphoneNumber.getText().toString();
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

        if(TextUtils.isEmpty(phoneNumber)){
            txtphoneNumber.setError("Please input your first name");
            txtphoneNumber.requestFocus();
        }else{
            toSend();
        }

    }

    private void toSend() {

        Intent intent = new Intent(PhoneActivity.this, BridgeActivity.class);
        intent.putExtra("phoneNumber",phoneNumber);
        intent.putExtra("getConfirmPass",getConfirmPass);
        intent.putExtra("getPassword",getPassword);
        intent.putExtra("getEmail",getEmail);
        intent.putExtra("getFirstname",getFirstname);
        intent.putExtra("getLastname",getLastname);
        startActivity(intent);

    }


    private void sendData() {


    }
}