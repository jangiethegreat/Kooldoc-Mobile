package com.example.kooldocandroidfinal.CustomerHistory;

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
import com.example.kooldocandroidfinal.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    ListView listView;
    CostumerAdapter adapter;
    public static ArrayList<Costumer> CostumerArrayList = new ArrayList<>();
    Costumer costumer;
    int intValue;
    String tableID;
    String ApiURL,CancelURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        //to pass the id value of the current user using the activity
        SharedPreferences sharedPreferences = getSharedPreferences("userProf",MODE_PRIVATE);
        intValue = sharedPreferences.getInt("id", 0);

        ApiURL = Constant.CUSTOMERALL + intValue;
        CancelURL = Constant.CANCELBOOK + intValue;
        listView = findViewById(R.id.myListview);
        adapter = new CostumerAdapter(this,CostumerArrayList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                AlertDialog.Builder  builder = new AlertDialog.Builder(view.getContext());
                ProgressDialog progressDialog = new ProgressDialog(view.getContext());

                CharSequence[] dialogItem = { "View Data","Cancel Booking","Download Receipt"};

                builder.setTitle("User ID: "+ CostumerArrayList.get(position).getId());
                tableID = CostumerArrayList.get(position).getId();

                builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                        switch (i){

                            case 0:
                                startActivity(new Intent(getApplicationContext(),DetailActivity.class)
                                        .putExtra("tableID",tableID));
                                break;
                            case 1:
                                startActivity(new Intent(getApplicationContext(),CancelBookActivity.class)
                                        .putExtra("tableID",tableID));

                                break;

                            case 2:
                                startActivity(new Intent(getApplicationContext(),DetailActivity.class)
                                        .putExtra("tableID",tableID));
                                break;

                        }

                    }
                });

                builder.create().show();

            }
        });

        GetData();
    }

    private void DeleteData() {

        StringRequest request = new StringRequest(Request.Method.POST, CancelURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }

    private void GetData() {


        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        StringRequest request = new StringRequest(Request.Method.GET, ApiURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        CostumerArrayList.clear();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("customer_booked");

                            for (int i=0; i<jsonArray.length();i++){

                                JSONObject object = jsonArray.getJSONObject(i);
                                String id = object.getString("id");
                                String bookType = object.getString("book_type");
                                String serviceType = object.getString("service_type");
                                String acType = object.getString("ac_type");
                                String acBrand = object.getString("ac_brand");
                                String unitType = object.getString("unit_type");
                                String noUnit = object.getString("no_unit");
                                String serviceDate = object.getString("service_date");
                                String serviceTime = object.getString("service_time");
                                String servicePrice = object.getString("service_price");
                                String adminLastName = object.getString("admin_last_name");
                                String createdat = object.getString("created_at");
                                String techLastname = object.getString("technician_last_names");




                                costumer = new Costumer(id,bookType,serviceType,acType,acBrand,unitType,noUnit,serviceDate,serviceTime,servicePrice,adminLastName,techLastname,createdat);
                                CostumerArrayList.add(costumer);
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
                Toast.makeText(HistoryActivity.this, "Errorr", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);

    }
}
