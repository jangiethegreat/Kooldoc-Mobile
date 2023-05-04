package com.example.kooldocandroidfinal.AppLogin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
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

public class UserRegisterActivity extends AppCompatActivity {

    EditText edtfirstName,edtlastName, edtEmail,edtPassword,edtconfirmPass,edtphonenumber,edtAddress,edtBarangay,edtCity,edtProvince,edtZipcode;

    Button btnInsert;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        edtfirstName= findViewById(R.id.edt_firstname);
        edtlastName= findViewById(R.id.edt_lastname);
        edtEmail = findViewById(R.id.edt_email);
        edtPassword = findViewById(R.id.edt_password);
        edtconfirmPass = findViewById(R.id.edt_confirmPass);
        edtphonenumber = findViewById(R.id.edt_phoneNumber);
        edtAddress = findViewById(R.id.edt_address);
        edtBarangay = findViewById(R.id.edt_barangay);
        edtCity = findViewById(R.id.edt_city);
        edtProvince = findViewById(R.id.edt_province);
        edtZipcode = findViewById(R.id.edt_zipCode);

        btnInsert = findViewById(R.id.btn_register);


        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                insertData();
            }
        });




    }

    private void insertData() {

        String firstname = edtfirstName.getText().toString().trim();
        String lastname = edtlastName.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();
        String confirmPass = edtconfirmPass.getText().toString().trim();
        String phoneNumber = edtphonenumber.getText().toString().trim();
        String address = edtAddress.getText().toString().trim();
        String barangay = edtBarangay.getText().toString().trim();
        String city = edtCity.getText().toString().trim();
        String province = edtProvince.getText().toString().trim();
        String zipcode = edtZipcode.getText().toString().trim();







        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");



        if (TextUtils.isEmpty(firstname)){
            Toast.makeText(this, "Enter Name", Toast.LENGTH_SHORT).show();
            edtfirstName.setError("Full name is required");
            edtfirstName.requestFocus();
            finish();
        }else if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Enter Email", Toast.LENGTH_SHORT).show();
            edtEmail.setError("Full name is required");
            edtEmail.requestFocus();
            finish();

        }else if (TextUtils.isEmpty(password)){
            Toast.makeText(this, "Enter Contact", Toast.LENGTH_SHORT).show();
            edtPassword.setError("Full name is required");
            edtPassword.requestFocus();
            finish();

        }else{

            progressDialog.show();

            StringRequest request = new StringRequest(Request.Method.POST, Constant.REGISTER,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.equalsIgnoreCase("Data Inserted")){
                                Toast.makeText(UserRegisterActivity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }else{
                                Toast.makeText(UserRegisterActivity.this, response, Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }


                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(UserRegisterActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();

                }
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {


                    Map<String,String> params = new HashMap<String,String>();

                    params.put("first_name", firstname);
                    params.put("last_name", lastname);

                    params.put("email", email);
                    params.put("password", password);
                    params.put("password_confirmation", confirmPass);
                    params.put("phone_number", phoneNumber);
                    params.put("address", address);
                    params.put("barangay", barangay);
                    params.put("city", city);
                    params.put("province", province);
                    params.put("zip_code", zipcode);

                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(UserRegisterActivity.this);
            requestQueue.add(request);
        }
    }

    public  void onBackPressed(){
        super.onBackPressed();
        finish();
    }
}