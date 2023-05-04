package com.example.kooldocandroidfinal.CanceledServices;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
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
import com.example.kooldocandroidfinal.EmployeeHistory.ServHistoryActivity;
import com.example.kooldocandroidfinal.EmployeeHistory.Service;
import com.example.kooldocandroidfinal.EmployeeHistory.ServiceAdapter;
import com.example.kooldocandroidfinal.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class XservicesActivity extends AppCompatActivity {

    ListView listView;
    CancelledAdapter adapter;
    public static ArrayList<Cancelled> CancelledArrayList = new ArrayList<>();
    Cancelled cancelled;
    int intValue;
    String ApiURL = Constant.HOME + "/customer-cancelled-services/1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xservices);

        SharedPreferences sharedPreferences = getSharedPreferences("userProf",MODE_PRIVATE);
        intValue = sharedPreferences.getInt("id", 0);


        listView = findViewById(R.id.cancelListview);
        adapter = new CancelledAdapter(this,CancelledArrayList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                AlertDialog.Builder  builder = new AlertDialog.Builder(view.getContext());
                ProgressDialog progressDialog = new ProgressDialog(view.getContext());

                CharSequence[] dialogItem = { "View Data","Cancel Booking"};

                builder.setTitle("User ID: "+ CancelledArrayList.get(position).getId());


                builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                        switch (i){

                            case 0:
//                                startActivity(new Intent(getApplicationContext(), DetailActivity.class)
//                                        .putExtra("position",position));
                                break;
                            case 1:

//                                DeleteData();
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

                        CancelledArrayList.clear();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("customer_cancelled");

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
                                String serviceStatus = object.getString("service_status");





                                cancelled = new Cancelled(id,bookType,serviceType,acType,acBrand,unitType,noUnit,serviceDate,serviceTime,servicePrice,serviceStatus);
                                CancelledArrayList.add(cancelled);
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
                Toast.makeText(XservicesActivity.this, "Errorr", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);

    }

}