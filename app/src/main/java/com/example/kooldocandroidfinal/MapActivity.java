package com.example.kooldocandroidfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends AppCompatActivity {
//    private OnMapReadyCallback onMapReadyCallback;
//    LocationManager locationManager;
//    LocationListener locationListener;
//    LatLng userLatLong;

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        //super class
//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.MY_MAP);
//        mapFragment.getMapAsync((OnMapReadyCallback) this);

        //Map_Fragment.java calling
        Fragment fragment = new Map_Fragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, fragment).commit();

    }
}

//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        mMap = googleMap;
//        LatLng sydney = new LatLng( -31, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("KoolDoc")
//                .icon(bitmapDescriptorFromVector(getApplicationContext(),R.drawable.kooldoc)));
//
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
//    }
//
//    private BitmapDescriptor bitmapDescriptorFromVector(Context context,int vectorResId) {
//        Drawable vectorDrawable= ContextCompat.getDrawable(context,vectorResId);
//        vectorDrawable.setBounds(0,0,vectorDrawable.getIntrinsicWidth(),
//                vectorDrawable.getIntrinsicHeight());
//        Bitmap bitmap=Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(),
//                vectorDrawable.getIntrinsicHeight(),Bitmap.Config.ARGB_8888);
//        Canvas canvas=new Canvas(bitmap);
//        vectorDrawable.draw(canvas);
//        return BitmapDescriptorFactory.fromBitmap(bitmap);
//    }
