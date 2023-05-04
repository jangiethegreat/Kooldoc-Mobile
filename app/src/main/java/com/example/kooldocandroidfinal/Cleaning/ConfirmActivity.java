package com.example.kooldocandroidfinal.Cleaning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.kooldocandroidfinal.Fragments.ServiceFragment;
import com.example.kooldocandroidfinal.HomeActivity;
import com.example.kooldocandroidfinal.R;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConfirmActivity extends AppCompatActivity {

    Button btnConfirm,btnPDF;
    String passACbrand,passACtype,passACUnitType,passNumberUnit,passselectedDate,passselectedTime,descriptionValue;
    String Pricevalue,ServiceType,PaymentMethod,ACHorsePower;
    int passValueofService;
    int intValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);



        btnConfirm = findViewById(R.id.btnConfirm);

        SharedPreferences sharedPreferences = getSharedPreferences("userProf",MODE_PRIVATE);
        intValue = sharedPreferences.getInt("id", 0);
        Intent intent = getIntent();
        passValueofService = intent.getIntExtra("valueofService",0);
//-----------------------------------------------------------------------------//
        passselectedDate= intent.getStringExtra("selectedDate");
        passselectedTime= intent.getStringExtra("selectedTime");
        descriptionValue = intent.getStringExtra("descriptionValue");
        PaymentMethod = intent.getStringExtra("PaymentMethod");
        Pricevalue = intent.getStringExtra("Pricevalue");
//-----------------------------------------------------------------------------//
        ServiceType = intent.getStringExtra("ServiceType");
        passACtype = intent.getStringExtra("ACtype");
        passACbrand = intent.getStringExtra("ACbrand");
        passACUnitType = intent.getStringExtra("ACUnitType");
        passNumberUnit = intent.getStringExtra("NumberUnitvalue");
        ACHorsePower = intent.getStringExtra("ACHorsePower");



        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ConfirmActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });



    }


}