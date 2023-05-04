package com.example.kooldocandroidfinal.Register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kooldocandroidfinal.AppLogin.UserLoginActivity;
import com.example.kooldocandroidfinal.R;

public class ConfirmPassActivity extends AppCompatActivity {
    ImageView btnBack,btnSkip;
    Button btnNext;

    TextView txt1;
    EditText txtconfirmPass;
    String confirmPass;

    String getLastname, getFirstname, getEmail,getPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_pass);


        btnBack = findViewById(R.id.btn_back);
        btnNext = findViewById(R.id.btnProceed);
        txtconfirmPass = findViewById(R.id.txt_confirmPass);



        btnSkip = findViewById(R.id.btnSkip);

        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfirmPassActivity.this, UserLoginActivity.class);
                startActivity(intent);
            }
        });
        Intent intent = getIntent();
        getFirstname = intent.getStringExtra("getFirstname");
        getLastname = intent.getStringExtra("getLastname");
        getEmail = intent.getStringExtra("getEmail");
        getPassword = intent.getStringExtra("password");


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmPass = txtconfirmPass.getText().toString().trim();
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

        if(TextUtils.isEmpty(confirmPass)){
            txtconfirmPass.setError("Please input your first name");
            txtconfirmPass.requestFocus();
        }else{
            toSend();
        }

    }

    private void toSend() {
        Intent intent = new Intent(ConfirmPassActivity.this,PhoneActivity.class);
        intent.putExtra("confirmPass",confirmPass);
        intent.putExtra("getPassword",getPassword);
        intent.putExtra("getEmail",getEmail);
        intent.putExtra("getFirstname",getFirstname);
        intent.putExtra("getLastname",getLastname);
        startActivity(intent);

    }

}