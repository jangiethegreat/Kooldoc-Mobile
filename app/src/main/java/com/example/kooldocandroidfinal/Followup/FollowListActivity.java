package com.example.kooldocandroidfinal.Followup;

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

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.kooldocandroidfinal.Constant;
import com.example.kooldocandroidfinal.CustomerHistory.CancelBookActivity;
import com.example.kooldocandroidfinal.CustomerHistory.DetailActivity;
import com.example.kooldocandroidfinal.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FollowListActivity extends AppCompatActivity {

    ListView listView;
    FollowupAdapter adapter;
    public static ArrayList<Followup> FollowupArrayList = new ArrayList<>();
    Followup followup;
    int intValue;
    String tableID;
    String ApiURL,CancelURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follow_list);

        SharedPreferences sharedPreferences = getSharedPreferences("userProf",MODE_PRIVATE);
        intValue = sharedPreferences.getInt("id", 0);

        ApiURL = Constant.TECHALLFOLLOWUP + intValue;
        CancelURL = Constant.CANCELBOOK + intValue;
        listView = findViewById(R.id.myListview);
        adapter = new FollowupAdapter(this,FollowupArrayList);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                AlertDialog.Builder  builder = new AlertDialog.Builder(view.getContext());
                ProgressDialog progressDialog = new ProgressDialog(view.getContext());

                CharSequence[] dialogItem = { "View Data","Update Followup"};

                builder.setTitle("User ID: "+ FollowupArrayList.get(position).getId());
                tableID = FollowupArrayList.get(position).getId();

                builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                        switch (i){

                            case 0:
                                startActivity(new Intent(getApplicationContext(), DetailActivity.class)
                                        .putExtra("position",position));
                                break;
                            case 1:
                                startActivity(new Intent(getApplicationContext(), UpdateFollowupActivity.class)
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

    private void GetData() {

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        StringRequest request = new StringRequest(Request.Method.GET, ApiURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        FollowupArrayList.clear();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("followup_services");

                            for (int i=0; i<jsonArray.length();i++){

                                JSONObject object = jsonArray.getJSONObject(i);
                                String id = object.getString("id");
                                String serviceId = object.getString("service_id");
                                String adminId = object.getString("admin_id");
                                String reason = object.getString("reason");
                                String followupDate = object.getString("followup_date");
                                String followupTime = object.getString("followup_time");
                                String followupReport = object.getString("followup_report");
                                String followupStatus = object.getString("followup_status");
                                String createdat = object.getString("created_at");
                                String updatedat = object.getString("updated_at");
                                String customerLastName = object.getString("customer_last_name");
                                String adminLastName = object.getString("admin_last_name");
                                String fullAddress = object.getString("full_address");
                                String techLastname = object.getString("technician_last_names");
                                String fullContact = object.getString("full_contact");

                                followup = new Followup(id,serviceId,adminId,reason,followupDate,followupTime,followupReport,followupStatus,createdat,updatedat,customerLastName,adminLastName,fullAddress,techLastname,fullContact);
                                FollowupArrayList.add(followup);
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
                Toast.makeText(FollowListActivity.this, "Errorr", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);



    }
}