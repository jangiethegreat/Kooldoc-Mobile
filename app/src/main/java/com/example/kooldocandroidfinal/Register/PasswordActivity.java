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

public class PasswordActivity extends AppCompatActivity {
    ImageView btnBack,btnSkip;
    Button btnNext;

    EditText txtPassword;
    String password;
    String getFirstname,getLastname,getEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);


        btnBack = findViewById(R.id.btn_back);
        btnNext = findViewById(R.id.btnProceed);
        txtPassword = findViewById(R.id.txt_password);

        btnSkip = findViewById(R.id.btnSkip);

        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PasswordActivity.this, UserLoginActivity.class);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        getFirstname = intent.getStringExtra("getFirstname");
        getLastname = intent.getStringExtra("getLastname");
        getEmail = intent.getStringExtra("emailAddress");

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password = txtPassword.getText().toString().trim();
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

        if(TextUtils.isEmpty(password)){
            txtPassword.setError("Please input your first name");
            txtPassword.requestFocus();
        }else{
            toSend();
        }

    }

    private void toSend() {
        Intent intent = new Intent(PasswordActivity.this,ConfirmPassActivity.class);
        intent.putExtra("password",password);
        intent.putExtra("getEmail",getEmail);
        intent.putExtra("getFirstname",getFirstname);
        intent.putExtra("getLastname",getLastname);

        startActivity(intent);
    }

}