package com.example.kooldocandroidfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import com.example.kooldocandroidfinal.EmployeeHistory.ServiceStatus;
import com.example.kooldocandroidfinal.Fragments.MapFragment;
import com.example.kooldocandroidfinal.Fragments.ReviewFragment;
import com.example.kooldocandroidfinal.Fragments.ServiceFragment;
import com.example.kooldocandroidfinal.UserProfile.UserFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    private ImageView gotoMessenger;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        bottomNav();
    }

    private void bottomNav() {

        bottomNavigationView =findViewById(R.id.bottom_navigation);


        getSupportFragmentManager().beginTransaction().replace(R.id.main_container,new ServiceFragment()).commit();
        bottomNavigationView.setSelectedItemId(R.id.nav_service);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                Fragment fragment=null;
                switch (item.getItemId()){
                    case R.id.nav_user:
                        fragment = new UserFragment();
                        break;

                    case R.id.nav_service:
                        fragment = new ServiceFragment();
                        break;

                    case R.id.nav_map:
                        fragment = new MapFragment();
                        break;

                    case R.id.nav_review:
                        fragment = new ReviewFragment();
                        break;



                }
                getSupportFragmentManager().beginTransaction().replace(R.id.main_container,fragment).commit();

                return true;
            }
        });

    }
}