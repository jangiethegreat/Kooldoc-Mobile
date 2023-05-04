package com.example.kooldocandroidfinal.Installation;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.kooldocandroidfinal.Cleaning.ConfirmActivity;
import com.example.kooldocandroidfinal.Cleaning.cleanConditionActivity;
import com.example.kooldocandroidfinal.Constant;
import com.example.kooldocandroidfinal.Fragments.ServiceFragment;
import com.example.kooldocandroidfinal.R;
import com.itextpdf.text.Font;

import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class bookInstallation extends AppCompatActivity {

    EditText editACtype,editACbrand,editUnitType,editNumberUnit;
    EditText editServiceDate,editServiceTime,editPaymentMethod;
    Button btnBooking;
    ProgressBar progressBar;
    TextView textPrice;
    String ACtype,ACbrand,ACUnitType,ACHorsePower;
    int valueofService;
    int intValue;
    ImageView goBack;

    Spinner spinnerOne,spinnerTwo,spinnerThree,spinnerFour;
    String[]  acTypeList,acBrandList,unitTypeList,acHPlist;
    ArrayAdapter<String> adapterone,adaptertwo,adapterthree,adapterfour;

    EditText txtDescription;
    String  descriptionValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_installation);


        txtDescription = findViewById(R.id.edt_description);
        btnBooking = findViewById(R.id.btnProceed);
        spinnerOne = findViewById(R.id.spinner_one);
        spinnerTwo = findViewById(R.id.spinner_two);
        spinnerThree = findViewById(R.id.spinner_three);
        spinnerFour = findViewById(R.id.spinner_four);

        acHPlist = new String[]{"Select Aircon Horsepower","0.5", "0.75","1.0","1.5","2.0","2.5","I don't know"};

        acTypeList = new String[]{"Select Aircon Type","Window", "Split","Tower","Cassette","Suspended","Concealed","U-Shaped Window","Other"};
        acBrandList = new String[]{"Select Aircon Brand","Daikin", "Mitsubishi Electric","LG","Carrier","Panasonic","Samsung","Fujitsu","Hitachi","Toshiba","Other"};
        unitTypeList = new String[]{"Select Unit Type","Inverter", "Non-Inverter","I dont know"};
        Font headerFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
        adapterone = new ArrayAdapter<>(this, R.layout.spinner_layout, acTypeList);
        spinnerOne.setAdapter(adapterone);
        adaptertwo = new ArrayAdapter<>(this,  R.layout.spinner_layout, acBrandList);
        spinnerTwo.setAdapter(adaptertwo);
        adapterthree = new ArrayAdapter<>(this,  R.layout.spinner_layout, unitTypeList);
        spinnerThree.setAdapter(adapterthree);
        adapterfour = new ArrayAdapter<>(this, R.layout.spinner_layout, acHPlist);
        spinnerFour.setAdapter(adapterfour);


        goBack = findViewById(R.id.btn_back);
        progressBar = findViewById(R.id.progressBar);
        SharedPreferences sharedPreferences = getSharedPreferences("userProf",MODE_PRIVATE);
        intValue = sharedPreferences.getInt("id", 0);


        btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ACtype = spinnerOne.getSelectedItem().toString();
                acTypeFilter();
                ACbrand = spinnerTwo.getSelectedItem().toString();
                ACUnitType = spinnerThree.getSelectedItem().toString();
                descriptionValue = txtDescription.getText().toString();
                ACHorsePower = spinnerFour.getSelectedItem().toString();
                edtFilters();

            }
        });

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
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
            Toast.makeText(this, "Aircon description is needede", Toast.LENGTH_SHORT).show();
            txtDescription.requestFocus();
        }else if (ACHorsePower.equals("Select Aircon Horsepower")){
            Toast.makeText(this, "Aircon horsepower is needed", Toast.LENGTH_SHORT).show();
            btnBooking.requestFocus();
        }else{
            toSend();
        }
    }

    private void toSend() {

        Intent intent = new Intent(bookInstallation.this, installConditionActivity.class);
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
            valueofService = 999;

        }else if (ACtype.equals("Split")){
            valueofService =1299;

        }else if (ACtype.equals("Tower")){
            valueofService = 1499;

        }else if (ACtype.equals("Cassette")){
            valueofService = 1699;

        }else if (ACtype.equals("Suspended")){
            valueofService = 1899;

        }else if (ACtype.equals("Concealed")){
            valueofService = 2099;

        }else if (ACtype.equals("U-Shaped Window")){
            valueofService = 2299;
        }
    }



}