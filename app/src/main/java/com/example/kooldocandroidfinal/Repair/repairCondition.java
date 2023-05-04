package com.example.kooldocandroidfinal.Repair;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.kooldocandroidfinal.R;

public class repairCondition extends AppCompatActivity {


    Spinner spinnerOne,spinnerTwo,spinnerThree;
    String[] conditionlist;
    ArrayAdapter<String> adapter;
    int valueofService;
    String passACbrand,passACtype,passACUnitType,descriptionValue,ACHorsePower;
    Button btnProceed;
    String cooling,mechanical,electric;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repair_condition);

        spinnerOne = findViewById(R.id.spinner_one);
        spinnerTwo = findViewById(R.id.spinner_two);
        spinnerThree = findViewById(R.id.spinner_three);
        btnProceed = findViewById(R.id.btnProceed);

        Intent intent = getIntent();
        valueofService = intent.getIntExtra("valueofService",0);
        passACbrand = intent.getStringExtra("ACbrand");
        passACtype = intent.getStringExtra("ACtype");
        passACUnitType = intent.getStringExtra("ACUnitType");
        descriptionValue = intent.getStringExtra("descriptionValue");
        ACHorsePower = intent.getStringExtra("ACHorsePower");


        conditionlist = new String[]{"Choose Condition","Yes", "No"};
        adapter = new ArrayAdapter<>(this, R.layout.spinner_layout, conditionlist);
        spinnerOne.setAdapter(adapter);
        adapter = new ArrayAdapter<>(this, R.layout.spinner_layout, conditionlist);
        spinnerTwo.setAdapter(adapter);
        adapter = new ArrayAdapter<>(this, R.layout.spinner_layout, conditionlist);
        spinnerThree.setAdapter(adapter);

        btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cooling=spinnerOne.getSelectedItem().toString();
                electric=spinnerThree.getSelectedItem().toString();
                mechanical=spinnerTwo.getSelectedItem().toString();


                edtFilters();



            }
        });
    }

    private void edtFilters() {

        if (cooling.equals("Choose Condition")){
            Toast.makeText(this, "Please select between yes or no", Toast.LENGTH_SHORT).show();
            btnProceed.requestFocus();
        }else if (mechanical.equals("Choose Condition")){
            Toast.makeText(this, "Please select between yes or no", Toast.LENGTH_SHORT).show();
            btnProceed.requestFocus();
        }else if (electric.equals("Choose Condition")){
            Toast.makeText(this, "Please select between yes or no", Toast.LENGTH_SHORT).show();
            btnProceed.requestFocus();
        }else{
            toSend();
        }
    }

    private void toSend() {
        Intent intent = new Intent(repairCondition.this, repairSchedule.class);
        intent.putExtra("ACbrand",passACbrand);
        intent.putExtra("ACtype",passACtype);
        intent.putExtra("ACUnitType",passACUnitType);
        intent.putExtra("valueofService",valueofService);
        intent.putExtra("descriptionValue",descriptionValue);
        intent.putExtra("ACHorsePower",ACHorsePower);
        intent.putExtra("cooling",cooling);
        intent.putExtra("electric",electric);
        intent.putExtra("mechanical",mechanical);

        startActivity(intent);

    }
}
