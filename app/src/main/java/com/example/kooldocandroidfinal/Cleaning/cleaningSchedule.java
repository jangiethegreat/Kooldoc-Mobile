package com.example.kooldocandroidfinal.Cleaning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.example.kooldocandroidfinal.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class cleaningSchedule extends AppCompatActivity {
    private CalendarView calendarView;
    private Button submitButton;
    private String selectedDate,selectedTime;
    private TextView calendar_text,timeslot,alertMessage;
    private RadioButton rb1,rb2,rb3,rb4;
    String cooling,mechanical,electric;

    //intent filter
    String passACbrand,passACtype,passACUnitType,descriptionValue,ACHorsePower;
    int valueofService;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cleaning_schedule);



        //intent filter
        Intent intent = getIntent();
        valueofService = intent.getIntExtra("valueofService",0);
        passACbrand = intent.getStringExtra("ACbrand");
        passACtype = intent.getStringExtra("ACtype");
        passACUnitType = intent.getStringExtra("ACUnitType");

        descriptionValue = intent.getStringExtra("descriptionValue");
        ACHorsePower = intent.getStringExtra("ACHorsePower");
        cooling = intent.getStringExtra("cooling");
        mechanical = intent.getStringExtra("mechanical");
        electric = intent.getStringExtra("electric");

        setContentView(R.layout.activity_cleaning_schedule);
        calendarView = findViewById(R.id.calendar_view);
        submitButton = findViewById(R.id.submit_button);
        calendar_text = findViewById(R.id.calendar_text);
        alertMessage = findViewById(R.id.alertMessage);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);
        rb4 = findViewById(R.id.rb4);
        timeslot = findViewById(R.id.timeslot);

        Calendar calendar = Calendar.getInstance();
        long currentDate = calendar.getTimeInMillis();

// Set minimum and maximum date range
        calendarView.setMinDate(currentDate + (1000 * 60 * 60 * 24)); // set minimum date to tomorrow


        checkAvailability(calendarView);
        Toast.makeText(cleaningSchedule.this, "Select Timeslot First then select a date: " , Toast.LENGTH_LONG).show();
        calendarView.setVisibility(View.GONE);
        submitButton.setEnabled(false);

        // Set the date format to match MySQL date format (YYYY-MM-DD)
        rb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeslot.setText("6:00 A.M. - 9:00 A.M.");
                calendarView.setVisibility(View.VISIBLE);
                alertMessage.setText("");
            }

        });

        rb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeslot.setText("9:00 A.M. - 12:00 P.M.");
                calendarView.setVisibility(View.VISIBLE);
                alertMessage.setText("");
            }
        });
        rb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeslot.setText("12:00 P.M. - 3:00 P.M.");
                calendarView.setVisibility(View.VISIBLE);
                alertMessage.setText("");
            }
        });
        rb4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeslot.setText("3:00 P.M. - 6:00 P.M.");
                calendarView.setVisibility(View.VISIBLE);
                alertMessage.setText("");
            }
        });










        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectedDate = calendar_text.getText().toString();
                selectedTime = timeslot.getText().toString();
                Intent intent = new Intent(cleaningSchedule.this, CleaningDT.class);
                intent.putExtra("ACbrand",passACbrand);
                intent.putExtra("ACtype",passACtype);
                intent.putExtra("ACUnitType",passACUnitType);
                intent.putExtra("valueofService",valueofService);
                intent.putExtra("selectedDate", selectedDate);
                intent.putExtra("selectedTime", selectedTime);
                intent.putExtra("descriptionValue", descriptionValue);
                intent.putExtra("ACHorsePower", ACHorsePower);
                intent.putExtra("cooling",cooling);
                intent.putExtra("electric",electric);
                intent.putExtra("mechanical",mechanical);

                startActivity(intent);

                //insertData();
                submitButton.setEnabled(false);
            }


            //ignore insert data
            private void insertData() {
                selectedDate = calendar_text.getText().toString();
                selectedTime = timeslot.getText().toString();
                String url = "http:/192.168.100.74/android/insert.php";
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                Toast.makeText(cleaningSchedule.this, "Event added successfully", Toast.LENGTH_SHORT).show();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(cleaningSchedule.this, "Error adding event: " + error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        // Set the request parameters (date and event)
                        Map<String, String> params = new HashMap<String,String>();
                        params.put("date", selectedDate);
                        params.put("time",selectedTime);

                        return params;
                    }
                };

                // Add the request to the Volley request queue
                Volley.newRequestQueue(cleaningSchedule.this).add(stringRequest);
            }});}

//ignore insert data


    private void checkAvailability(CalendarView calendarView) {


        calendarView = findViewById(R.id.calendar_view);
        RadioButton rb1 = findViewById(R.id.rb1);
        RadioButton rb2 = findViewById(R.id.rb2);
        RadioButton rb3 = findViewById(R.id.rb3);
        RadioButton rb4 = findViewById(R.id.rb4);
        TextView selectedDateTimeTextView = findViewById(R.id.selected_date_time_text_view);

// Initialize API URL and Volley request queue
        String apiUrl = "https://kooldocbusiness.com/api/all-schedule";
        RequestQueue requestQueue = Volley.newRequestQueue(this);

// Set the date change listener for the calendar view
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                String selectedDate = String.format(Locale.getDefault(), "%04d-%02d-%02d", year, month + 1, dayOfMonth);

                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);

                // Check if the selected date is a weekend day (Saturday or Sunday)
                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
                if (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY) {
                    // Do nothing
                    Calendar currentDate = Calendar.getInstance();
                    calendarView.setDate(currentDate.getTimeInMillis(), true, true);
                    Toast.makeText(cleaningSchedule.this, "Not Accepting Booking on Weekends", Toast.LENGTH_SHORT).show();
                    submitButton.setEnabled(false);
                    return;
                }


                calendar_text.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                calendarView.setVisibility(View.GONE);

                // Get the selected time slot
                String selectedTime = "";
                if (rb1.isChecked()) {
                    selectedTime = "6:00 A.M. - 9:00 A.M.";
                    selectedDateTimeTextView.setText(selectedDate + " " + selectedTime);
                } else if (rb2.isChecked()) {
                    selectedTime = "9:00 A.M. - 12:00 P.M.";
                    selectedDateTimeTextView.setText(selectedDate + " " + selectedTime);
                } else if (rb3.isChecked()) {
                    selectedTime = "12:00 P.M. - 3:00 P.M.";
                    selectedDateTimeTextView.setText(selectedDate + " " + selectedTime);
                } else if (rb4.isChecked()) {
                    selectedTime = "3:00 P.M. - 6:00 P.M.";
                    selectedDateTimeTextView.setText(selectedDate + " " + selectedTime);
                }

                // Check if the selected date and time slot is in the API response
                String finalSelectedTime = selectedTime;
                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, apiUrl, null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                // Parse the response and check if the selected date and time slot is in the list
                                List<String> dateTimePairs = new ArrayList<>();
                                for (int i = 0; i < response.length(); i++) {
                                    try {
                                        JSONObject schedule = response.getJSONObject(i);
                                        String date = schedule.getString("date");
                                        String time = schedule.getString("time");
                                        dateTimePairs.add(date + "-" + time);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                                String selectedDateTimePair = selectedDate + "-" + finalSelectedTime;
                                if (dateTimePairs.contains(selectedDateTimePair)) {
                                    // The selected date and time slot is available
                                    Toast.makeText(cleaningSchedule.this, "Selected date and time slot is not available", Toast.LENGTH_SHORT).show();
                                    alertMessage.setText("Not Available! \n Please Select New TimeSlot and Date");
                                    submitButton.setEnabled(false);
                                } else {
                                    // The selected date and time slot is not available
                                    Toast.makeText(cleaningSchedule.this, "Selected date and time slot is available", Toast.LENGTH_SHORT).show();
                                    alertMessage.setText("This Timeslot and Date is Available");
                                    submitButton.setEnabled(true);
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });
                // Add the request to the queue
                requestQueue.add(jsonArrayRequest);

            }

        });
    }
}