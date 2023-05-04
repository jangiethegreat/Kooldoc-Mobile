package com.example.kooldocandroidfinal.Maintenance;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.TextViewOnReceiveContentListener;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.example.kooldocandroidfinal.Cleaning.CleaningDT;
import com.example.kooldocandroidfinal.R;
import com.example.kooldocandroidfinal.UserProfile.GcashActivity;

import java.util.Calendar;

public class maintenanceDT extends AppCompatActivity {

    Button gotoReceipt;
    Button gotoGcash;

    EditText editTextDate;
    EditText editNumberUnit;
    DatePickerDialog picker;
    String Datevalue,Timevalue,NumberUnitvalue,Pricevalue;
    boolean firstButtonClicked = false;
    RadioGroup rdGroupTime;
    RadioButton rdButtonTimeselect;
    RadioButton rdButton1;
    RadioButton rdButton2;
    RadioButton rdButton3;
    RadioButton rdButton4;
    int valueofService;
    Button checkPrice;
    ImageView btnBack;
    String passACbrand,passACtype,passACUnitType,passselectedDate,passselectedTime,descriptionValue;
    TextView textPrice;
    String cooling,mechanical,electric,ACHorsePower;
    String textViewPrice,editTextNumberUnit,edtDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance_dt);



        Intent intent = getIntent();
        valueofService = intent.getIntExtra("valueofService",0);
        passACbrand = intent.getStringExtra("ACbrand");
        passACtype = intent.getStringExtra("ACtype");
        passACUnitType = intent.getStringExtra("ACUnitType");
        passselectedDate = intent.getStringExtra("selectedDate");
        passselectedTime = intent.getStringExtra("selectedTime");
        descriptionValue = intent.getStringExtra("descriptionValue");
        ACHorsePower = intent.getStringExtra("ACHorsePower");

        cooling = intent.getStringExtra("cooling");
        mechanical = intent.getStringExtra("mechanical");
        electric = intent.getStringExtra("electric");


//        passNumberUnit = intent.getStringExtra("");
        btnBack = findViewById(R.id.btn_back);
        editNumberUnit = findViewById(R.id.edt_addNumberUnit);

        gotoReceipt = findViewById(R.id.btnSubmit);
        gotoGcash = findViewById(R.id.btnGcash);
        checkPrice = findViewById(R.id.btn_checkPrice);


        gotoReceipt.setEnabled(false);
        gotoGcash.setEnabled(false);


        textPrice =findViewById(R.id.text_Price);
        textPrice.setText(String.valueOf(valueofService));
//        editTextTime = findViewById(R.id.edt_addTime);




        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        checkPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                textViewPrice = textPrice.getText().toString();
                editTextNumberUnit = editNumberUnit.getText().toString();
                int numUnit  = Integer.parseInt(editTextNumberUnit);

                if (numUnit > 11){
                    Toast.makeText(maintenanceDT.this, "The number of units can be no more than 30", Toast.LENGTH_LONG).show();
                    editNumberUnit.setText("");
                }


                try {
                    int total =0;
                    int value1 = Integer.valueOf(textViewPrice);
                    int value2 = Integer.parseInt(editTextNumberUnit);

                    int result = valueofService * Integer.parseInt(editNumberUnit.getText().toString());
                    total += result;
                    textPrice.setText(String.valueOf(total));


                }catch (NumberFormatException e){
                    Log.e(TAG, "Error: Invalid input string: " + e.getMessage());
                }
                filterData();
            }
        });






        gotoReceipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Pricevalue = textPrice.getText().toString();

                NumberUnitvalue = editNumberUnit.getText().toString();


                if (NumberUnitvalue.isEmpty()){
                    Toast.makeText(maintenanceDT.this, "The number of units cannot be empty", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(com.example.kooldocandroidfinal.Maintenance.maintenanceDT.this, maintenanceReceipt.class);
                    intent.putExtra("Datevalue", Datevalue);
                    intent.putExtra("Timevalue", Timevalue);
                    intent.putExtra("NumberUnitvalue", NumberUnitvalue);
                    intent.putExtra("Pricevalue", Pricevalue);

                    //foreign activity
                    intent.putExtra("ACbrand", passACbrand);
                    intent.putExtra("ACtype", passACtype);
                    intent.putExtra("ACUnitType", passACUnitType);
                    intent.putExtra("selectedDate", passselectedDate);
                    intent.putExtra("selectedTime", passselectedTime);
                    intent.putExtra("descriptionValue", descriptionValue);
                    intent.putExtra("ACHorsePower",ACHorsePower);
                    intent.putExtra("cooling",cooling);
                    intent.putExtra("electric",electric);
                    intent.putExtra("mechanical",mechanical);

                    intent.putExtra("PaymentMethod", "Cash on Service");
                    intent.putExtra("ServiceType", "Installation");

                    startActivity(intent);
                }
            }
        });

        gotoGcash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Pricevalue = textPrice.getText().toString();

                NumberUnitvalue = editNumberUnit.getText().toString();

                    if (NumberUnitvalue.isEmpty()){
                        Toast.makeText(maintenanceDT.this, "The number of units cannot be empty", Toast.LENGTH_SHORT).show();
                    }else {
                        Intent intent = new Intent(maintenanceDT.this, GcashActivity.class);
                        intent.putExtra("NumberUnitvalue", NumberUnitvalue);
                        intent.putExtra("Pricevalue", Pricevalue);

                        //foreign activity
                        intent.putExtra("ACbrand", passACbrand);
                        intent.putExtra("ACtype", passACtype);
                        intent.putExtra("ACUnitType", passACUnitType);
                        intent.putExtra("valueofService", valueofService);
                        intent.putExtra("selectedDate", passselectedDate);
                        intent.putExtra("selectedTime", passselectedTime);
                        intent.putExtra("descriptionValue", descriptionValue);
                        intent.putExtra("ACHorsePower",ACHorsePower);
                        intent.putExtra("cooling",cooling);
                        intent.putExtra("electric",electric);
                        intent.putExtra("mechanical",mechanical);

                        intent.putExtra("PaymentMethod", "Cash on Service");
                        intent.putExtra("ServiceType", "Installation");

                        startActivity(intent);
                    }

            }
        });

    }

    private void filterData() {


       if (TextUtils.isEmpty(editTextNumberUnit)){
            editNumberUnit.setError("Place the number of aircon units");
            editNumberUnit.requestFocus();
        }else{
            firstButtonClicked = true;
            gotoReceipt.setEnabled(true);
            gotoGcash.setEnabled(true);
        }
    }
}
