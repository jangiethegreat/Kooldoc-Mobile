package com.example.kooldocandroidfinal.Cleaning;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.example.kooldocandroidfinal.Constant;
import com.example.kooldocandroidfinal.Installation.InstallationDT;
import com.example.kooldocandroidfinal.Installation.bookInstallation;
import com.example.kooldocandroidfinal.Installation.installationSchedule;
import com.example.kooldocandroidfinal.R;
import com.itextpdf.text.Font;


import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class bookCleaning extends AppCompatActivity {

    EditText editACtype,editACbrand,editUnitType,editNumberUnit;
    EditText editServiceDate,editServiceTime,editPaymentMethod;
    Button btnBooking;
    ProgressBar progressBar;
    TextView textPrice;
    String ACtype,ACbrand,ACUnitType,ACHorsePower;
    int valueofService;
    int intValue;
    ImageView btnBack;
    Spinner spinnerOne,spinnerTwo,spinnerThree,spinnerFour;
    String[]  acTypeList,acBrandList,unitTypeList,acHPlist;
    ArrayAdapter<String> adapterone,adaptertwo,adapterthree,adapterfour;

    EditText txtDescription;
    String  descriptionValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_cleaning);

        txtDescription = findViewById(R.id.edt_description);
        spinnerOne = findViewById(R.id.spinner_one);
        spinnerTwo = findViewById(R.id.spinner_two);
        spinnerThree = findViewById(R.id.spinner_three);
        spinnerFour = findViewById(R.id.spinner_four);
        btnBooking = findViewById(R.id.btnProceed);
        progressBar = findViewById(R.id.progressBar);
        btnBack = findViewById(R.id.btn_back);

        acHPlist = new String[]{"Select Aircon Horsepower","0.5", "0.75","1.0","1.5","2.0","2.5","I don't know"};

        acBrandList = new String[]{"Select Aircon Brand","Daikin", "Mitsubishi Electric","LG","Carrier","Panasonic","Samsung","Fujitsu","Hitachi","Toshiba","Other"};
        unitTypeList = new String[]{"Select Unit Type","Inverter", "Non-Inverter","I dont know"};
        Font headerFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
        acTypeList = new String[]{"Select Aircon Type","Window", "Split","Tower","Cassette","Suspended","Concealed","U-Shaped Window","Other"};
        adapterone = new ArrayAdapter<>(this, R.layout.spinner_layout, acTypeList);
        spinnerOne.setAdapter(adapterone);
        adaptertwo = new ArrayAdapter<>(this,  R.layout.spinner_layout, acBrandList);
        spinnerTwo.setAdapter(adaptertwo);
        adapterthree = new ArrayAdapter<>(this,  R.layout.spinner_layout, unitTypeList);
        spinnerThree.setAdapter(adapterthree);
        adapterfour = new ArrayAdapter<>(this, R.layout.spinner_layout, acHPlist);
        spinnerFour.setAdapter(adapterfour);


        SharedPreferences sharedPreferences = getSharedPreferences("userProf",MODE_PRIVATE);
        intValue = sharedPreferences.getInt("id", 0);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {





                ACtype = spinnerOne.getSelectedItem().toString();
                acTypeFilter();
                ACbrand = spinnerTwo.getSelectedItem().toString();
                ACUnitType = spinnerThree.getSelectedItem().toString();
                ACHorsePower = spinnerFour.getSelectedItem().toString();
                descriptionValue = txtDescription.getText().toString();

                edtFilters();

            }
        });

    }

    private void edtFilters() {

        if (ACtype.equals("Select Aircon Type")){
            Toast.makeText(this, "Please select a valid aircon type", Toast.LENGTH_SHORT).show();
            btnBooking.requestFocus();
        }else if (ACbrand.equals("Select Aircon Brand")){
            Toast.makeText(this, "Please select a valid aircon brand", Toast.LENGTH_SHORT).show();
            btnBooking.requestFocus();
        }else if (ACUnitType.equals("Select Unit Type")){
            Toast.makeText(this, "Please select a valid unit type", Toast.LENGTH_SHORT).show();
            btnBooking.requestFocus();
        }else if (descriptionValue.isEmpty()){
            Toast.makeText(this, "Aircon description is needed", Toast.LENGTH_SHORT).show();
            txtDescription.requestFocus();
        }else if (ACHorsePower.equals("Select Aircon Horsepower")){
            Toast.makeText(this, "Aircon horsepower is needed", Toast.LENGTH_SHORT).show();
            btnBooking.requestFocus();
        }else{
            toSend();
        }
    }

    private void toSend() {

        Intent intent = new Intent(bookCleaning.this, cleanConditionActivity.class);
        intent.putExtra("ACbrand",ACbrand);
        intent.putExtra("ACtype",ACtype);
        intent.putExtra("ACUnitType",ACUnitType);
        intent.putExtra("valueofService",valueofService);
        intent.putExtra("descriptionValue",descriptionValue);
        intent.putExtra("ACHorsePower",ACHorsePower);
        startActivity(intent);
    }

    private void acTypeFilter() {

        if (ACtype.equals("Window")){
            valueofService = 666;

        }else if (ACtype.equals("Split")){
            valueofService =888;

        }else if (ACtype.equals("Tower")){
            valueofService = 999;

        }else if (ACtype.equals("Cassette")){
            valueofService = 1111;

        }else if (ACtype.equals("Suspended")){
            valueofService = 1299;

        }else if (ACtype.equals("Concealed")){
            valueofService = 1499;

        }else if (ACtype.equals("U-Shaped Window")){
            valueofService = 1699;
        }

    }

//    private void insertData() {
//
//
//        TextView textServiceType = findViewById(R.id.txt_serviceType);
//        String service_type = "Installation";
//        String service_price = textPrice.getText().toString();
//        String ac_type = editACtype.getText().toString();
//        String ac_brand = editACbrand.getText().toString();
//        String unit_type = editUnitType.getText().toString();
//        String no_unit = editNumberUnit.getText().toString();
//        String service_date = editServiceDate.getText().toString();
//        String service_time = editServiceTime.getText().toString();
//        String payment_method = editPaymentMethod.getText().toString();
//        String serviceID = String.valueOf(intValue);
//
//        StringRequest request = new StringRequest(Request.Method.POST, Constant.SERVICES, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//
//                try {
//
//                    JSONObject theobject = new JSONObject(response);
//                    JSONObject jsonObject = theobject.getJSONObject("service");
////                    for (int i = 0; i < jsonObject.length(); i++) {
////                        JSONObject object = JSONObject.getJSONObject(i);
//                    SharedPreferences sharedPreferences = getSharedPreferences("Service", MODE_PRIVATE);
//                    SharedPreferences.Editor editor = sharedPreferences.edit();
//
//                    editor.putInt("id", jsonObject.getInt("id"));
//                    editor.putInt("admin_id", jsonObject.getInt("admin_id"));
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
//                    editor.putString("service_report", jsonObject.getString("service_report"));
//                    editor.apply();
//                    progressBar.setVisibility(View.GONE);
//                    Intent intent = new Intent(bookCleaning.this, ConfirmActivity.class);
//                    startActivity(intent);
//                    finish();
//
////                    }
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//                Toast.makeText(bookCleaning.this, "An error occurred", Toast.LENGTH_SHORT).show();
//            }
//        }){
//            @Nullable
//            @Override
//            protected Map<String, String> getParams() {
//                Map<String,String> map = new HashMap<>();
//
//                map.put("customer_id",serviceID);
//                map.put("service_type",service_type);
//                map.put("ac_type",ac_type);
//                map.put("ac_brand",ac_brand);
//                map.put("unit_type",unit_type);
//                map.put("no_unit",no_unit);
//                map.put("service_date",service_date);
//                map.put("service_time",service_time);
//                map.put("service_price",service_price);
//                map.put("payment_method",payment_method);
//                map.put("amount",service_price);
//                return map;
//            }
//        };
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        requestQueue.add(request);
//    }
}




