package com.example.kooldocandroidfinal.EmployeeHistory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.kooldocandroidfinal.CanceledServices.XservicesActivity;
import com.example.kooldocandroidfinal.EmployeeProfile.EmpInfoActivity;
import com.example.kooldocandroidfinal.Followup.FollowListActivity;
import com.example.kooldocandroidfinal.R;
import com.example.kooldocandroidfinal.UserProfile.GcashActivity;

public class EmployeeActivity extends AppCompatActivity {

    CardView card1,card2,card3,card4,card5,card6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);

        card1 = findViewById(R.id.emp_Services);
        card2 = findViewById(R.id.emp_followUp);
        card3 = findViewById(R.id.emp_serviceReport);
        card4 = findViewById(R.id.emp_qrCode);


        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmployeeActivity.this,ServHistoryActivity.class);
                startActivity(intent);

            }
        });

        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmployeeActivity.this, EmpInfoActivity.class);
                startActivity(intent);
            }
        });
        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmployeeActivity.this, FollowListActivity.class);
                startActivity(intent);
            }
        });
        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmployeeActivity.this, gCashActivity.class);
                startActivity(intent);
            }
        });



    }
}