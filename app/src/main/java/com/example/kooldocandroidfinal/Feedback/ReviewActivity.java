package com.example.kooldocandroidfinal.Feedback;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.kooldocandroidfinal.AppLogin.UserLoginActivity;
import com.example.kooldocandroidfinal.Constant;
import com.example.kooldocandroidfinal.CustomerHistory.HistoryActivity;
import com.example.kooldocandroidfinal.HomeActivity;
import com.example.kooldocandroidfinal.R;
import com.example.kooldocandroidfinal.Register.ConfirmPassActivity;
import com.example.kooldocandroidfinal.Register.PropertytypeActivity;
import com.example.kooldocandroidfinal.UserProfile.UserFragment;

import java.util.HashMap;
import java.util.Map;

public class ReviewActivity extends AppCompatActivity {

    TextView textViewPrompt;
    RatingBar ratingBar;
    Button btnSubmit;
    ImageView btnBack;
    EditText txtDescription;
    int intValue;
    private Button submitButton;
//    float userRating;
//    String description;
    String ApiURL;

int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        SharedPreferences sharedPreferences = getSharedPreferences("userProf",MODE_PRIVATE);
        intValue = sharedPreferences.getInt("id", 0);
        textViewPrompt = findViewById(R.id.text_view_prompt);
        ratingBar = findViewById(R.id.rating_bar);
        btnSubmit=findViewById(R.id.btn_submit);
        txtDescription = findViewById(R.id.edt_description);
        ApiURL = Constant.FEEDBACK +intValue;
        btnBack = findViewById(R.id.btn_back);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              onBackPressed();
            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                
                sendData();
            }
        });

    }

    private void sendData() {

        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");

        String idValue = FeedbackActivity.FeedbackArrayList.get(position).getId();
        String userRating = String.valueOf(ratingBar.getRating());
        String description = txtDescription.getText().toString();
        String sendrating = userRating;
        String senddescription = description;

        StringRequest request = new StringRequest(Request.Method.POST, ApiURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Intent intent = new Intent(ReviewActivity.this, HomeActivity.class);
                        startActivity(intent);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ReviewActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();


            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {


                Map<String,String> params = new HashMap<String,String>();

                params.put("service_id", idValue);
                params.put("rating", sendrating);
                params.put("review", senddescription);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(ReviewActivity.this);
        requestQueue.add(request);







    }
}