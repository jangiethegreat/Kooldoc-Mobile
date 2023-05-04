package com.example.kooldocandroidfinal.EmployeeHistory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kooldocandroidfinal.R;

public class ServiceDataActivity extends AppCompatActivity {

    TextView id,bookType,serviceType,acType,acBrand,unitType,noUnit,serviceDate,serviceTime,servicePrice,adminLastname,techLastname,customerFullname,fullAddress,fullContact;
    ImageView goBack;
    String tableID;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_data);


        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");
        id = findViewById(R.id.serv_ID);
        bookType = findViewById(R.id.serv_bookType);
        serviceType = findViewById(R.id.serv_serviceType);
        acType = findViewById(R.id.serv_acType);
        acBrand = findViewById(R.id.serv_acBrand);
        unitType = findViewById(R.id.serv_unitType);
        noUnit = findViewById(R.id.serv_noUnit);
        serviceDate = findViewById(R.id.serv_serviceDate);
        serviceTime = findViewById(R.id.serv_serviceTime);
        servicePrice = findViewById(R.id.serv_servicePrice);
        adminLastname = findViewById(R.id.serv_adminLname);
        techLastname = findViewById(R.id.serv_techLname);
        customerFullname = findViewById(R.id.serv_customerFull);
        fullAddress = findViewById(R.id.serv_fullAddress);
        fullContact = findViewById(R.id.serv_fullContact);
        goBack = findViewById(R.id.btn_back);


        id.setText("ID: " + ServHistoryActivity.ServiceArrayList.get(position).getId());
        bookType.setText("Book Type: " + ServHistoryActivity.ServiceArrayList.get(position).getBookType());
        serviceType.setText("Service Type: " + ServHistoryActivity.ServiceArrayList.get(position).getServiceType());
        acType.setText("AC Type: " + ServHistoryActivity.ServiceArrayList.get(position).getAcType());
        acBrand.setText("AC Brand: " + ServHistoryActivity.ServiceArrayList.get(position).getAcBrand());

        unitType.setText("Unit Type: " + ServHistoryActivity.ServiceArrayList.get(position).getUnitType());
        noUnit.setText("No Unit: " + ServHistoryActivity.ServiceArrayList.get(position).getNoUnit());
        serviceDate.setText("Service Date: " + ServHistoryActivity.ServiceArrayList.get(position).getServiceDate());
        serviceTime.setText("Service Time: " + ServHistoryActivity.ServiceArrayList.get(position).getServiceTime());

        servicePrice.setText("Service Price: " + ServHistoryActivity.ServiceArrayList.get(position).getServicePrice());
        adminLastname.setText("Admin Last name: " + ServHistoryActivity.ServiceArrayList.get(position).getAdminLastname());
        techLastname.setText("Technician Last names: " + ServHistoryActivity.ServiceArrayList.get(position).getTechLastname());
        customerFullname.setText("Customer Fullname: " + ServHistoryActivity.ServiceArrayList.get(position).getCustomerFullname());
        fullAddress.setText("Full Address: " + ServHistoryActivity.ServiceArrayList.get(position).getFullAddress());
        fullContact.setText("Full Contact: " + ServHistoryActivity.ServiceArrayList.get(position).getFullContact());

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });










    }
}