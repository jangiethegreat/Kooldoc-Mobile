package com.example.kooldocandroidfinal.UserProfile;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
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
import com.example.kooldocandroidfinal.Installation.installReceipt;
import com.example.kooldocandroidfinal.R;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class GcashActivity extends AppCompatActivity {

    ImageView goBack;
    String passACbrand,passACtype,passACUnitType,passNumberUnit,passselectedDate,passselectedTime,descriptionValue;
    String Pricevalue,ServiceType,PaymentMethod;
    int passValueofService;
    int intValue;
    String cooling,mechanical,electric,ACHorsePower;
    Button btnBook,btnPDF;
    ProgressBar progressBar;
    TextView showBrand,showType,showUnitType,showNumberUnit;
    TextView showDate,showPrice,showTime,showPaymentMethod;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gcash);
        goBack = findViewById(R.id.btn_back);
        SharedPreferences sharedPreferences = getSharedPreferences("userProf",MODE_PRIVATE);
        intValue = sharedPreferences.getInt("id", 0);


        Intent intent = getIntent();
        passACbrand = intent.getStringExtra("ACbrand");
        passACtype = intent.getStringExtra("ACtype");
        passACUnitType = intent.getStringExtra("ACUnitType");
        passValueofService = intent.getIntExtra("valueofService",0);
        descriptionValue = intent.getStringExtra("descriptionValue");
//-----------------------------------------------------------------------------//
        passselectedDate= intent.getStringExtra("selectedDate");
        passselectedTime= intent.getStringExtra("selectedTime");
//-----------------------------------------------------------------------------//
        passNumberUnit = intent.getStringExtra("NumberUnitvalue");
        Pricevalue = intent.getStringExtra("Pricevalue");
//-----------------------------------------------------------------------------//
        ServiceType = intent.getStringExtra("ServiceType");
        PaymentMethod = intent.getStringExtra("PaymentMethod");
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
//        btnPDF = findViewById(R.id.downloadPDF);

        showBrand.setText(passACbrand.substring(0,1).toUpperCase()+ passACbrand.substring(1));
        showType.setText(passACtype.substring(0,1).toUpperCase()+ passACtype.substring(1));
        showUnitType.setText(passACUnitType.substring(0,1).toUpperCase()+ passACUnitType.substring(1));
        showNumberUnit.setText(passNumberUnit.substring(0,1).toUpperCase()+ passNumberUnit.substring(1));

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
        String service_type = ServiceType;
        String sendactype = showType.getText().toString();
        String sendBrand = showBrand.getText().toString();
        String sendUnitType = showUnitType.getText().toString();
        String sendNumberUnit = showNumberUnit.getText().toString();
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

                    JSONObject theobject = new JSONObject(response);
                    JSONObject jsonObject = theobject.getJSONObject("service");
//                    for (int i = 0; i < jsonObject.length(); i++) {
//                        JSONObject object = JSONObject.getJSONObject(i);
//                    SharedPreferences sharedPreferences = getSharedPreferences("Service", MODE_PRIVATE);
//                    SharedPreferences.Editor editor = sharedPreferences.edit();
//
//                    editor.putInt("id", jsonObject.getInt("id"));
//
//
//                    editor.putString("customer_id", jsonObject.getString("customer_id"));
//                    editor.putString("service_type", jsonObject.getString("service_type"));
//                    editor.putString("ac_type", jsonObject.getString("ac_type"));
//                    editor.putString("ac_brand", jsonObject.getString("ac_brand"));
//                    editor.putString("unit_type", jsonObject.getString("unit_type"));
//                    editor.putString("no_unit", jsonObject.getString("no_unit"));
//                    editor.putString("service_date", jsonObject.getString("service_date"));
//                    editor.putString("service_time", jsonObject.getString("service_time"));
//                    editor.putString("service_price", jsonObject.getString("service_price"));
//
//                    editor.putString("image", jsonObject.getString("image"));
//                    editor.apply();

                    Intent intent = new Intent(GcashActivity.this, ConfirmActivity.class);
                    startActivity(intent);
                    finish();

//                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(GcashActivity.this, "An error occurred", Toast.LENGTH_SHORT).show();
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

                map.put("cooling",sendCooling);
                map.put("electric_connectivity",sendElectric);
                map.put("mechanical_noise",sendMechanic);
                map.put("ac_hp",sendHp);

                map.put("unit_type",sendUnitType);
                map.put("no_unit",sendNumberUnit);
                map.put("service_date",sendDate);
                map.put("service_time",sendTime);

                map.put("service_price",sendPrice);
                map.put("payment_method",sendMethod);
                map.put("amount",sendPrice);
                map.put("description",sendDescription);
                return map;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}