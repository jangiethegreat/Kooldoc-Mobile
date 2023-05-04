package com.example.kooldocandroidfinal.AppLogin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.kooldocandroidfinal.Cleaning.ConfirmActivity;
import com.example.kooldocandroidfinal.Cleaning.bookCleaning;
import com.example.kooldocandroidfinal.Constant;
import com.example.kooldocandroidfinal.EmployeeHistory.EmployeeActivity;
import com.example.kooldocandroidfinal.HomeActivity;
import com.example.kooldocandroidfinal.MainActivity;
import com.example.kooldocandroidfinal.R;
import com.example.kooldocandroidfinal.Register.NameActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class UserLoginActivity extends AppCompatActivity {

    Button btnLogin;
    TextView btnRegister;
    EditText edtEmail,edtPassword;
    String email,password;
    SharedPreferences sharedPreferences2;
    SharedPreferences.Editor editor2;

    ImageView btnBack;


    String role;
    private String access_token;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);


        btnRegister = findViewById(R.id.btn_register);
        btnLogin = findViewById(R.id.btn_login);
        btnBack = findViewById(R.id.btn_back);
        edtEmail = findViewById(R.id.edt_email);
        edtPassword = findViewById(R.id.edt_password);




        sharedPreferences2 = getSharedPreferences("LoginFile",MODE_PRIVATE);
        editor2=sharedPreferences2.edit();

        if (sharedPreferences2.getString("isLoggedIn", "").equals("true")){
            SharedPreferences sharedPreferences = getSharedPreferences("userProf",MODE_PRIVATE);
            role = sharedPreferences.getString("role","role");
            if (role.equals("customer")){
                startActivity(new Intent(UserLoginActivity.this,HomeActivity.class));
                finishAffinity();
            }else if (role.equals("technician")){
                startActivity(new Intent(UserLoginActivity.this,EmployeeActivity.class));
                finishAffinity();
            }else{

            }

        }
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(UserLoginActivity.this, NameActivity.class);
                startActivity(intent);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    insertData();
            }
        });

    }

    private void insertData() {

        email = edtEmail.getText().toString().trim();
        password = edtPassword.getText().toString().trim();



        StringRequest request = new StringRequest(Request.Method.
POST, Constant.LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {


                    editor2.putString("isLoggedIn","true");
                    editor2.commit();

                    JSONObject theobject = new JSONObject(response);
                    JSONObject jsonObject = theobject.getJSONObject("user_data");
//                    for (int i = 0; i < jsonObject.length(); i++) {
//                        JSONObject object = JSONObject.getJSONObject(i);
                        SharedPreferences sharedPreferences = getSharedPreferences("userProf", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();


                        editor.putInt("id", jsonObject.getInt("id"));
                        editor.putInt("user_id", jsonObject.getInt("user_id"));
                        editor.putString("role", jsonObject.getString("role"));
                        editor.putString("email", jsonObject.getString("email"));
                        editor.putString("phone_number", jsonObject.getString("phone_number"));
                        editor.putString("image", jsonObject.getString("image"));
                        editor.apply();

                        String role = jsonObject.getString("role");
                    if (role.equals("customer")){
                        startActivity(new Intent(UserLoginActivity.this, HomeActivity.class));
                        finish();
                    }else if (role.equals("technician")){
                        startActivity(new Intent(UserLoginActivity.this, EmployeeActivity.class));
                        finish();
                    }
//                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

            }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(UserLoginActivity.this, "An error occurred", Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() {

                Map<String,String> map = new HashMap<>();

                map.put("email",email);
                map.put("password",password);
                return map;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }


}