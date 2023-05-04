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

public class NameActivity extends AppCompatActivity {

    ImageView btnBack,btnSkip;
    Button btnNext;
    EditText txtfirstName;
    String firstname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        btnBack = findViewById(R.id.btn_back);
        btnNext = findViewById(R.id.btnProceed);
        txtfirstName = findViewById(R.id.txt_firstName);

        btnSkip = findViewById(R.id.btnSkip);

        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NameActivity.this, UserLoginActivity.class);
                startActivity(intent);
            }
        });


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstname = txtfirstName.getText().toString();

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

        if(TextUtils.isEmpty(firstname)){
            txtfirstName.setError("Please input your first name");
            txtfirstName.requestFocus();
        }else{
            toSend();
        }

    }

    private void toSend() {
        Intent intent = new Intent(NameActivity.this,LastnameActivity.class);
        intent.putExtra("firstname",firstname);
        startActivity(intent);
    }
}