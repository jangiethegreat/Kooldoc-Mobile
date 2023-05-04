package com.example.kooldocandroidfinal.Maintenance;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.kooldocandroidfinal.R;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.sql.Time;
import java.util.HashMap;
import java.util.Map;

public class maintenanceReceipt extends AppCompatActivity {


    String passACbrand,passACtype,passACUnitType,passNumberUnit,passselectedDate,passselectedTime;
    String Datevalue,Timevalue,Pricevalue,ServiceType,PaymentMethod,descriptionValue;
    int passValueofService;
    TextView showBrand,showType,showUnitType,showNumberUnit;
    TextView showDate,showPrice,showTime,showPaymentMethod;
    ProgressBar progressBar;
    String cooling,mechanical,electric,ACHorsePower;
    int intValue;
    TextView trial;
    Button btnBook;
    ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_maintenance_receipt);



            progressBar= findViewById(R.id.progressBar);
            SharedPreferences sharedPreferences = getSharedPreferences("userProf",MODE_PRIVATE);
            intValue = sharedPreferences.getInt("id", 0);
            btnBack = findViewById(R.id.btn_back);
            Intent intent = getIntent();
            passACbrand = intent.getStringExtra("ACbrand");
            passACtype = intent.getStringExtra("ACtype");
            passACUnitType = intent.getStringExtra("ACUnitType");
            passValueofService = intent.getIntExtra("valueofService",0);
            //-----------------------------------------------------------------------------//
            passselectedDate= intent.getStringExtra("selectedDate");
            passselectedTime= intent.getStringExtra("selectedTime");
    //-----------------------------------------------------------------------------//

            passNumberUnit = intent.getStringExtra("NumberUnitvalue");
            Datevalue = intent.getStringExtra("Datevalue");
            Timevalue = intent.getStringExtra("Timevalue");
            Pricevalue = intent.getStringExtra("Pricevalue");
            descriptionValue = intent.getStringExtra("descriptionValue");
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


            showBrand.setText(passACbrand);
            showType.setText(passACtype);
            showUnitType.setText(passACUnitType);
            showNumberUnit.setText(passNumberUnit);

            showDate.setText(passselectedDate.substring(0,1).toUpperCase()+ passselectedDate.substring(1));
            showTime.setText(passselectedTime.substring(0,1).toUpperCase()+ passselectedTime.substring(1));

            showPrice.setText(Pricevalue);
            showPaymentMethod.setText(PaymentMethod);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
            btnBook.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    insertData();
                }
            });

    }





    private void insertData() {

        String serviceID = String.valueOf(intValue);
        String service_type = "Maintenance";
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

                    JSONObject theobject = new JSONObject(response);
                    JSONObject jsonObject = theobject.getJSONObject("service");
//                    for (int i = 0; i < jsonObject.length(); i++) {
//                        JSONObject object = JSONObject.getJSONObject(i);
                    SharedPreferences sharedPreferences = getSharedPreferences("Service", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putInt("id", jsonObject.getInt("id"));
                    editor.putInt("admin_id", jsonObject.getInt("admin_id"));

                    editor.putString("customer_id", jsonObject.getString("customer_id"));
                    editor.putString("service_type", jsonObject.getString("service_type"));
                    editor.putString("ac_type", jsonObject.getString("ac_type"));
                    editor.putString("ac_brand", jsonObject.getString("ac_brand"));
                    editor.putString("unit_type", jsonObject.getString("unit_type"));
                    editor.putString("no_unit", jsonObject.getString("no_unit"));
                    editor.putString("service_date", jsonObject.getString("service_date"));
                    editor.putString("service_time", jsonObject.getString("service_time"));
                    editor.putString("service_price", jsonObject.getString("service_price"));

                    editor.putString("image", jsonObject.getString("image"));

                    editor.apply();
                    progressBar.setVisibility(View.GONE);
                    Intent intent = new Intent(com.example.kooldocandroidfinal.Maintenance.maintenanceReceipt.this, ConfirmActivity.class);
                    intent.putExtra("selectedDate",passselectedDate);
                    intent.putExtra("selectedTime",passselectedTime);
                    intent.putExtra("descriptionValue",descriptionValue);
                    intent.putExtra("PaymentMethod",PaymentMethod);
                    intent.putExtra("Pricevalue",Pricevalue);
//--------------------------------------------------------------------------------------------//
                    intent.putExtra("ServiceType",ServiceType);
                    intent.putExtra("ACtype",passACtype);
                    intent.putExtra("ACbrand",passACbrand);
                    intent.putExtra("ACUnitType",passACUnitType);
                    intent.putExtra("NumberUnitvalue",passNumberUnit);
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
                Intent intent = new Intent(maintenanceReceipt.this, ConfirmActivity.class);
                startActivity(intent);
                finish();
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