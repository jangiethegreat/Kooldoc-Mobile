package com.example.kooldocandroidfinal.Fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kooldocandroidfinal.AppLogin.UserLoginActivity;
import com.example.kooldocandroidfinal.Cleaning.bookCleaning;
import com.example.kooldocandroidfinal.Cleaning.startCleaning;
import com.example.kooldocandroidfinal.Installation.bookInstallation;
import com.example.kooldocandroidfinal.Installation.startInstallation;
import com.example.kooldocandroidfinal.Maintenance.startMaintenance;
import com.example.kooldocandroidfinal.R;
import com.example.kooldocandroidfinal.Maintenance.bookMaintenance;
import com.example.kooldocandroidfinal.Repair.bookRepair;
import com.example.kooldocandroidfinal.Repair.startRepair;

import org.json.JSONException;
import org.json.JSONObject;


public class ServiceFragment extends Fragment {



    RelativeLayout gotoService;
    RelativeLayout gotoMaintenance;
    RelativeLayout gotoCleaning;
    RelativeLayout gotoRepair;



    SharedPreferences sharedPreferences2;
    SharedPreferences.Editor editor2;
 SharedPreferences preferences;
    String access_token;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_service, container, false);

        preferences = getActivity().getApplicationContext().getSharedPreferences("userPref",MODE_PRIVATE);
        access_token = getActivity().getIntent().getStringExtra("access_token");


        sharedPreferences2 = getActivity().getSharedPreferences("LoginFile",MODE_PRIVATE);
        editor2=sharedPreferences2.edit();

//        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
//        String accessToken = sharedPreferences.getString("access_token", null);



        gotoService = view.findViewById(R.id.acInstallation);
        gotoMaintenance  = view.findViewById(R.id.acMaintain);
        gotoCleaning = view.findViewById(R.id.acCleaning);
        gotoRepair = view.findViewById(R.id.acRepair);





        gotoService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), startInstallation.class);
                startActivity(intent);
            }
        });
        gotoMaintenance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), startMaintenance.class);
                startActivity(intent);
            }
        });
        gotoCleaning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), startCleaning.class);
//                intent.putExtra("access_token", access_token);
                startActivity(intent);
            }
        });
        gotoRepair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), startRepair.class);
                startActivity(intent);
            }
        });

        return view;



    }
}