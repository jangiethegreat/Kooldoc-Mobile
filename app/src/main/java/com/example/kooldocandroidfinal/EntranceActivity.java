package com.example.kooldocandroidfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.example.kooldocandroidfinal.AppLogin.UserLoginActivity;
import com.example.kooldocandroidfinal.Onboarding.OnboardingActivity;

public class EntranceActivity extends AppCompatActivity {
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrance);


        handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
//                Intent intent  =new Intent(EntranceActivity.this, OnboardingActivity.class);
//                startActivity(intent);
//                finish();

                isFirstTime();
            }
        },2000);
    }

    private void isFirstTime() {

        SharedPreferences preferences = getApplication().getSharedPreferences("onBoard", Context.MODE_PRIVATE);
        boolean isFirstTime = preferences.getBoolean("isFirstTime",true);

        //boolean value to change
        if (isFirstTime){
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("isFirstTime",false);
            editor.apply();

            //start onboard
            startActivity(new Intent(EntranceActivity.this, OnboardingActivity.class));
            finish();
        }else{

            startActivity(new Intent(EntranceActivity.this, MainActivity.class));
            finish();
        }


    }
    }



