package com.example.kooldocandroidfinal.Feedback;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.kooldocandroidfinal.Constant;
import com.example.kooldocandroidfinal.CustomerHistory.CancelBookActivity;
import com.example.kooldocandroidfinal.CustomerHistory.Costumer;
import com.example.kooldocandroidfinal.CustomerHistory.CostumerAdapter;
import com.example.kooldocandroidfinal.CustomerHistory.DetailActivity;
import com.example.kooldocandroidfinal.CustomerHistory.HistoryActivity;
import com.example.kooldocandroidfinal.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FeedbackActivity extends AppCompatActivity {

    ListView listView;
    FeedbackAdapter adapter;
    public static ArrayList<Feedback> FeedbackArrayList = new ArrayList<>();
    Feedback feedback;
    int intValue;

    String ApiURL,CancelURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        SharedPreferences sharedPreferences = getSharedPreferences("userProf",MODE_PRIVATE);
        intValue = sharedPreferences.getInt("id", 0);

        ApiURL ="https://kooldocbusiness.com/api/customer-completed-services/19";

//        ApiURL = Constant.COMPLETEDBOOKING + intValue;
        CancelURL = Constant.CANCELBOOK + intValue;
        listView = findViewById(R.id.myListview);
        adapter = new FeedbackAdapter(this,FeedbackArrayList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                AlertDialog.Builder  builder = new AlertDialog.Builder(view.getContext());
                ProgressDialog progressDialog = new ProgressDialog(view.getContext());

                CharSequence[] dialogItem = { "View Data","Service Feedback"};

                builder.setTitle("User ID: "+ FeedbackArrayList.get(position).getId());


                builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                        switch (i){

                            case 0:
                                startActivity(new Intent(getApplicationContext(), FeedbackDataActivity.class)
                                        .putExtra("position",position));
                                break;
                            case 1:
                                startActivity(new Intent(getApplicationContext(), ReviewActivity.class)
                                        .putExtra("position",position));

                                break;



                        }

                    }
                });

                builder.create().show();

            }
        });

        GetData();
    }

    private void GetData() {


        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        StringRequest request = new StringRequest(Request.Method.GET, ApiURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        FeedbackArrayList.clear();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("customer_completed");

                            for (int i=0; i<jsonArray.length();i++){

                                JSONObject object = jsonArray.getJSONObject(i);
                                String id = object.getString("id");
                                String bookType = object.getString("book_type");
                                String serviceType = object.getString("service_type");
                                String acType = object.getString("ac_type");
                                String acBrand = object.getString("ac_brand");
                                String acHp = object.getString("ac_hp");
                                String cooling = object.getString("cooling");
                                String description = object.getString("description");
                                String mechanicalNoise = object.getString("mechanical_noise");
                                String electricConnectivity = object.getString("electric_connectivity");
                                String unitType = object.getString("unit_type");
                                String noUnit = object.getString("no_unit");
                                String serviceDate = object.getString("service_date");
                                String serviceTime = object.getString("service_time");
                                String servicePrice = object.getString("service_price");
                                String serviceStatus = object.getString("service_status");
                                String adminLastName = object.getString("admin_last_name");
                                String techLastname = object.getString("technician_last_names");




                                feedback = new Feedback(id,bookType,serviceType,description,acType,acHp,cooling,mechanicalNoise,electricConnectivity,acBrand,unitType,noUnit,serviceDate,serviceTime,servicePrice,serviceStatus,techLastname,adminLastName);
                                FeedbackArrayList.add(feedback);
                                adapter.notifyDataSetChanged();
                                progressDialog.dismiss();
                            }

                        }catch (JSONException e){
                            e.printStackTrace();
                        }



                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(FeedbackActivity.this, "Errorr", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);

    }


}
