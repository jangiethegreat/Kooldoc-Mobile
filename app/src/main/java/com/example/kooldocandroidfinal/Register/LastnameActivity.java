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

public class LastnameActivity extends AppCompatActivity {

    ImageView btnBack,btnSkip;
    Button btnNext;

    TextView txt1;


    EditText txtlastName;
    String lastname;
    String getFirstname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lastname);

        btnBack = findViewById(R.id.btn_back);
        btnNext = findViewById(R.id.btnProceed);
        txtlastName = findViewById(R.id.txt_lastName);


        btnSkip = findViewById(R.id.btnSkip);

        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LastnameActivity.this, UserLoginActivity.class);
                startActivity(intent);
            }
        });


        Intent intent = getIntent();
        getFirstname = intent.getStringExtra("firstname");



        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastname= txtlastName.getText().toString();
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

        if(TextUtils.isEmpty(lastname)){
            txtlastName.setError("Please input your last name");
            txtlastName.requestFocus();
        }else{
            toSend();
        }

    }

    private void toSend() {
        Intent intent = new Intent(LastnameActivity.this,EmailActivity.class);
        intent.putExtra("lastname",lastname);
        intent.putExtra("getFirstname",getFirstname);
        startActivity(intent);
    }
}