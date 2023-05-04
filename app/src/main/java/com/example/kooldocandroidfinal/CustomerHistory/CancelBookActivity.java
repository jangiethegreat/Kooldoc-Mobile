package com.example.kooldocandroidfinal.CustomerHistory;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.kooldocandroidfinal.Constant;
import com.example.kooldocandroidfinal.EmployeeProfile.EmpInfoActivity;
import com.example.kooldocandroidfinal.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class CancelBookActivity extends AppCompatActivity {

    Button btnCancel;
    String ApiURL;
    String idValue,created_at;
    ImageView btnBack;
    int position;
    TextView mema;
    private boolean buttonClickable = true;
    private long createdAtTimestamp;

    String reason;

    Spinner spinnerOne;
    String[]  reasonList;
    ArrayAdapter<String> adapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_book);
        btnCancel = findViewById(R.id.btn_cancelBook);
        btnBack = findViewById(R.id.btn_back);
        spinnerOne = findViewById(R.id.spinner_one);
        reasonList = new String[]{"Select a reason","Change in plans", "Schedule conflict","Unforseen circumstances","Dissatisfied with service",
                "Service not needed","Cost concerns","Found a better deal","Poor communication","Bad reputation","Others"};
        adapter = new ArrayAdapter<>(this, R.layout.spinner_layout, reasonList);
        spinnerOne.setAdapter(adapter);
        //---------------FETCHING ID----------//
        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");
        idValue = HistoryActivity.CostumerArrayList.get(position).getId();
        //---------------API CONCAT----------//
        ApiURL= Constant.CANCELBOOK + idValue;

        created_at = HistoryActivity.CostumerArrayList.get(position).getServiceDate();


//        Date currentDate = new Date();
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
//        String dateString = dateFormat.format(currentDate);
//
//        long currentTimeStamp = System.currentTimeMillis();
//        long twentyFourHoursAgo = currentTimeStamp - (24 * 60 * 60 * 1000);
////        String currentTimeStampString = String.valueOf(currentTimeStamp);
//        if (btnCancel.isEnabled() && btnCancel.getTag() != null) {
//            long buttonTimeStamp = Long.parseLong(btnCancel.getTag().toString());
//            if (buttonTimeStamp < twentyFourHoursAgo) {
//                btnCancel.setEnabled(false);
//            }
//        }

        //---------------DISABLE THE BUTTON IF THE BOOKING IS OVER A DAY----------//



        created_at = HistoryActivity.CostumerArrayList.get(position).getCreatedat();
        createdAtTimestamp = getTimestampFromString(created_at);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dbDate = null;
        try {
            dbDate = dateFormat.parse(created_at);
        } catch (ParseException e) {
            e.printStackTrace();
        }

// Get the current date
        Calendar cal = Calendar.getInstance();
        Date currentDate = cal.getTime();

// Calculate the difference between the dates in milliseconds
        long diffMillis = currentDate.getTime() - dbDate.getTime();

// Calculate the difference in days
        int diffDays = (int) (diffMillis / (24 * 60 * 60 * 1000));

// Disable the button if the difference is more than one day
        if (diffDays > 1) {
            btnCancel.setEnabled(false);
        }
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date = null;
//        try {
//            date = dateFormat.parse(created_at);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        SimpleDateFormat displayFormat = new SimpleDateFormat("MMM dd, yyyy h:mm a");
//        String dateString = displayFormat.format(date);
//        String currentTimeStampString = String.valueOf(createdAtTimestamp);
//        long timeDiff = System.currentTimeMillis() - createdAtTimestamp;
//        // Disable the button if the time difference is greater than or equal to 24 hours
//        if (timeDiff >= 24 * 60 * 60 * 1000) {
//            enableButton();
//
//        }else{
//            disableButton();
//        }
//        mema.setText(dateString);

//        if (buttonClickable){
            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    cancelBook();
                }
            });
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    disableButton();
//                }
//            }, 5000);
//        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    private long getTimestampFromString(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
        try {
            Date date = dateFormat.parse(dateString);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private void disableButton() {
        btnCancel.setClickable(false);
        btnCancel.setEnabled(false);
        buttonClickable = false;
    }

    private void enableButton() {
        btnCancel.setClickable(true);
        btnCancel.setEnabled(true);
        buttonClickable = true;
    }

    private void cancelBook() {

        reason = spinnerOne.getSelectedItem().toString();
        String sendReason = reason;



        StringRequest request = new StringRequest(Request.Method.POST, ApiURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {
                            JSONObject jsonObject = new JSONObject(response);


                        }catch (JSONException e){
                            e.printStackTrace();

                        }



                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CancelBookActivity.this, "Error", Toast.LENGTH_SHORT).show();

            }
        }){


            @Nullable
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> map = new HashMap<>();

                map.put("service_id", idValue);
                map.put("why", sendReason);
                return map;
            }
        };

        Toast.makeText(CancelBookActivity.this, "Canceled Booking", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(CancelBookActivity.this,HistoryActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);





    }
}