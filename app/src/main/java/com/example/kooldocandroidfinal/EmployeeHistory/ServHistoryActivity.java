package com.example.kooldocandroidfinal.EmployeeHistory;

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
import com.example.kooldocandroidfinal.CustomerHistory.Costumer;
import com.example.kooldocandroidfinal.CustomerHistory.CostumerAdapter;
import com.example.kooldocandroidfinal.CustomerHistory.DetailActivity;
import com.example.kooldocandroidfinal.CustomerHistory.HistoryActivity;
import com.example.kooldocandroidfinal.R;
import com.itextpdf.text.pdf.fonts.otf.TableHeader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ServHistoryActivity extends AppCompatActivity {
    ListView listView;
    ServiceAdapter adapter;
    public static ArrayList<Service> ServiceArrayList = new ArrayList<>();
    Service service;
    int intValue;
    String tableID;
    String ApiURL,CancelURL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serv_history);




        SharedPreferences sharedPreferences = getSharedPreferences("userProf",MODE_PRIVATE);
        intValue = sharedPreferences.getInt("id", 0);

        ApiURL = Constant.TECHNICIANALL + intValue;
        CancelURL = Constant.CANCELBOOK + intValue;
        GetData();
        listView = findViewById(R.id.myListview);
        adapter = new ServiceAdapter(this,ServiceArrayList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                AlertDialog.Builder  builder = new AlertDialog.Builder(view.getContext());
                ProgressDialog progressDialog = new ProgressDialog(view.getContext());

                CharSequence[] dialogItem = { "View Data","Update Service","Add Follow-up"};

                builder.setTitle("User ID: "+ ServiceArrayList.get(position).getId());
                tableID = ServiceArrayList.get(position).getId();

                builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                        switch (i){

                            case 0:
                                startActivity(new Intent(getApplicationContext(), ServiceDataActivity.class)
                                        .putExtra("tableID",tableID));
                                break;
                            case 1:
                                startActivity(new Intent(getApplicationContext(),ServiceUpdate.class)
                                        .putExtra("tableID",tableID));
                                break;
                            case 2:
                                startActivity(new Intent(getApplicationContext(),FollowupActivity.class)
                                        .putExtra("tableID",tableID));
                                break;

                        }

                    }
                });

                builder.create().show();

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

                        ServiceArrayList.clear();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("technician_assigned");

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
                                String techLastname = object.getString("technician_last_names");
                                String customerFullname = object.getString("customer_full_name");
                                String fullAddress = object.getString("full_address");
                                String fullContact = object.getString("full_contact");




                                service = new Service(id,bookType,serviceType,acType,acBrand,unitType,noUnit,serviceDate,serviceTime,servicePrice,adminLastName,techLastname,customerFullname,fullAddress,fullContact);
                                ServiceArrayList.add(service);
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
                Toast.makeText(ServHistoryActivity.this, "Errorr", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);

    }
}


