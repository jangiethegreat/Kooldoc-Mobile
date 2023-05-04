package com.example.kooldocandroidfinal.EmployeeHistory;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.kooldocandroidfinal.Constant;
import com.example.kooldocandroidfinal.R;

import java.util.HashMap;
import java.util.Map;

public class FollowupActivity extends AppCompatActivity {

    Button btnSubmit;
    ImageView btnBack;
    EditText txtReason;

    String tableID,ApiURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followup);

        txtReason = findViewById(R.id.txt_reason);
        btnSubmit = findViewById(R.id.btnProceed);
        btnBack = findViewById(R.id.btn_back);


        Intent intent = getIntent();
        tableID = intent.getExtras().getString("tableID");
        ApiURL = Constant.FOLLOWUP + tableID;

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                sendData();
            }
        });



    }

    private void sendData() {

        String reason = txtReason.getText().toString();
        String servStatus = "followup";
        StringRequest request = new StringRequest(Request.Method.POST, ApiURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    Toast.makeText(FollowupActivity.this, "Service Updated", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(FollowupActivity.this, EmployeeActivity.class);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(FollowupActivity.this, "Service Updated", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(FollowupActivity.this, EmployeeActivity.class);
            }
        }) {


             @Nullable
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> map = new HashMap<>();

                map.put("reason", reason);


                return map;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(FollowupActivity.this);
        requestQueue.add(request);


    }
}