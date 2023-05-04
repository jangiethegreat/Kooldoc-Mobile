package com.example.kooldocandroidfinal.Feedback;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kooldocandroidfinal.R;

public class FeedbackDataActivity extends AppCompatActivity {

    int position;

    TextView id,bookType,serviceType,acType,acBrand,acHp,cooling,description,mechanicalNoise,
            electricConnectivity,unitType,noUnit,serviceDate,serviceTime,servicePrice,
            adminLastname,technicianLastname;

    ImageView goBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_data);

        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");

        id = findViewById(R.id.acID);
        bookType = findViewById(R.id.bkType);
        serviceType = findViewById(R.id.servType);
        acType = findViewById(R.id.acType);
        acBrand = findViewById(R.id.acBrand);
        acHp = findViewById(R.id.acHp);

        cooling = findViewById(R.id.cooling);
        mechanicalNoise = findViewById(R.id.mechanicalNoise);
        electricConnectivity = findViewById(R.id.electricConnectivity);
        description = findViewById(R.id.description);

        unitType = findViewById(R.id.utType);
        noUnit = findViewById(R.id.noUnit);
        serviceDate = findViewById(R.id.servDate);
        serviceTime = findViewById(R.id.servTime);
        servicePrice = findViewById(R.id.servPrice);

        adminLastname = findViewById(R.id.adminLastname);
        technicianLastname = findViewById(R.id.techLastname);
        goBack = findViewById(R.id.btn_back);

        id.setText("ID:" + FeedbackActivity.FeedbackArrayList.get(position).getId());
        bookType.setText("Book Type:" + FeedbackActivity.FeedbackArrayList.get(position).getBookType());
        serviceType.setText("Service Type:" + FeedbackActivity.FeedbackArrayList.get(position).getServiceType());
        acType.setText("AC Type:" + FeedbackActivity.FeedbackArrayList.get(position).getAcType());
        acBrand.setText("AC Brand:" + FeedbackActivity.FeedbackArrayList.get(position).getAcBrand());
        acHp.setText("AC Horsepower:" + FeedbackActivity.FeedbackArrayList.get(position).getAcHp());

        cooling.setText("Cooling Condition:" + FeedbackActivity.FeedbackArrayList.get(position).getCooling());
        mechanicalNoise.setText("Mechanical Noise Condition:" + FeedbackActivity.FeedbackArrayList.get(position).getMechanicalNoise());
        electricConnectivity.setText("Electric Connectivity Condition:" + FeedbackActivity.FeedbackArrayList.get(position).getElectricConnectivity());
        description.setText("Description:" + FeedbackActivity.FeedbackArrayList.get(position).getDescription());
        unitType.setText("Unit Type:" + FeedbackActivity.FeedbackArrayList.get(position).getUnitType());
        noUnit.setText("Number of Unit/s:" + FeedbackActivity.FeedbackArrayList.get(position).getNoUnit());

        serviceDate.setText("Service Date: " + FeedbackActivity.FeedbackArrayList.get(position).getServiceDate());
        serviceTime.setText("Service Time: " + FeedbackActivity.FeedbackArrayList.get(position).getServiceTime());
        servicePrice.setText("Service Price: " + FeedbackActivity.FeedbackArrayList.get(position).getServicePrice());
        adminLastname.setText("Admin Last names:" + FeedbackActivity.FeedbackArrayList.get(position).getAdminLastname());
        technicianLastname.setText("Technician Last names:" + FeedbackActivity.FeedbackArrayList.get(position).getTechnicianLastname());




        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });








    }
}