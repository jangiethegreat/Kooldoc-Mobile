package com.example.kooldocandroidfinal.Installation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.kooldocandroidfinal.R;

public class startInstallation extends AppCompatActivity {

    Button srtBooking;

    ImageView btnBack;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_installation);

        srtBooking = findViewById(R.id.start_booking);
        btnBack = findViewById(R.id.btn_back);

        srtBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(startInstallation.this, bookInstallation.class);
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