package com.example.kooldocandroidfinal.Installation;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.kooldocandroidfinal.Cleaning.ConfirmActivity;
import com.example.kooldocandroidfinal.Constant;
import com.example.kooldocandroidfinal.CustomerHistory.HistoryActivity;
import com.example.kooldocandroidfinal.R;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class installReceipt extends AppCompatActivity {


    String passACbrand,passACtype,passACUnitType,passNumberUnit,passselectedDate,passselectedTime,descriptionValue;
    int passValueofService;
    String Datevalue,Timevalue,Pricevalue,ServiceType,PaymentMethod;

    TextView showBrand,showType,showUnitType,showNumberUnit;
    TextView showDate,showPrice,showTime,showPaymentMethod;
    ProgressBar progressBar;
    String cooling,mechanical,electric,ACHorsePower;
    int intValue;
    TextView trial;
    Button btnBook,btnPDF;
    ImageView goBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_install_receipt);



        goBack = findViewById(R.id.btn_back);
        progressBar= findViewById(R.id.progressBar);
        SharedPreferences sharedPreferences = getSharedPreferences("userProf",MODE_PRIVATE);
        intValue = sharedPreferences.getInt("id", 0);


        Intent intent = getIntent();
        passACtype = intent.getStringExtra("ACtype");
        passACbrand = intent.getStringExtra("ACbrand");
        passACUnitType = intent.getStringExtra("ACUnitType");
        passValueofService = intent.getIntExtra("valueofService",0);
//-----------------------------------------------------------------------------//
        passselectedDate= intent.getStringExtra("selectedDate");
        passselectedTime= intent.getStringExtra("selectedTime");
        Pricevalue = intent.getStringExtra("Pricevalue");
//-----------------------------------------------------------------------------//
        descriptionValue = intent.getStringExtra("descriptionValue");
        ServiceType = intent.getStringExtra("ServiceType");
        PaymentMethod = intent.getStringExtra("PaymentMethod");

        passNumberUnit = intent.getStringExtra("NumberUnitvalue");

        ACHorsePower = intent.getStringExtra("ACHorsePower");
        cooling = intent.getStringExtra("cooling");
        mechanical = intent.getStringExtra("mechanical");
        electric = intent.getStringExtra("electric");

        //initialization
        showBrand = findViewById(R.id.show_AirconBrand);
        showType = findViewById(R.id.show_AirconType);
        showUnitType = findViewById(R.id.show_AirconUtype);
        showNumberUnit = findViewById(R.id.show_AirconNumUnit);

        showDate = findViewById(R.id.show_date);
        showPrice = findViewById(R.id.show_time);

        showTime = findViewById(R.id.show_price);
        showPaymentMethod = findViewById(R.id.show_paymentMethod);

        btnBook = findViewById(R.id.btnBook);

        showBrand.setText(passNumberUnit);
        showType.setText(ServiceType);
        showUnitType.setText(passACUnitType);
        showNumberUnit.setText(passNumberUnit);
        showDate.setText(passselectedDate.substring(0,1).toUpperCase()+ passselectedDate.substring(1));
        showTime.setText(passselectedTime.substring(0,1).toUpperCase()+ passselectedTime.substring(1));

        showPrice.setText(Pricevalue.substring(0,1).toUpperCase()+ Pricevalue.substring(1));
        showPaymentMethod.setText(PaymentMethod);

        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
            }
        });


        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });



    }



    private void insertData() {

        String serviceID = String.valueOf(intValue);

        String service_type = "Installation";
        String sendactype = passACtype;
        String sendBrand = passACbrand;
        String sendUnitType = passACUnitType;
        String sendNumberUnit = passNumberUnit;

        String sendCooling = cooling;
        String sendMechanic = mechanical;
        String sendElectric = electric;
        String sendHp = ACHorsePower;

        String sendDate = showDate.getText().toString();
        String sendTime = showTime.getText().toString();
        String sendDescription = descriptionValue;
        String sendPrice = showPrice.getText().toString();
        String sendMethod = showPaymentMethod.getText().toString();


        StringRequest request = new StringRequest(Request.Method.POST, Constant.SERVICES, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    Intent intent = new Intent(installReceipt.this, ConfirmActivity.class);
                    startActivity(intent);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Intent intent = new Intent(installReceipt.this, ConfirmActivity.class);
                startActivity(intent);
            }
        }){


            @Nullable
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> map = new HashMap<>();

                map.put("customer_id",serviceID);
                map.put("service_type",service_type);
                map.put("ac_type",sendactype);
                map.put("ac_brand",sendBrand);

                map.put("unit_type",sendUnitType);
                map.put("no_unit",sendNumberUnit);
                map.put("ac_hp",sendHp);
                map.put("description",sendDescription);

                map.put("cooling",sendCooling);
                map.put("mechanical_noise",sendMechanic);
                map.put("electric_connectivity",sendElectric);

                map.put("service_date",sendDate);
                map.put("service_time",sendTime);
                map.put("service_price",sendPrice);
                map.put("payment_method",sendMethod);
                map.put("amount",sendPrice);

                return map;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}