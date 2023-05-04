package com.example.kooldocandroidfinal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class Map_Fragment extends Fragment {

    private GoogleMap mMap;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_map_,container, false);
        SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {

                mMap = googleMap;
                LatLng sydney = new LatLng( 14.50972, 121.05966);
                mMap.addMarker(new MarkerOptions().position(sydney).title("KoolDoc")
                        .icon(bitmapDescriptorFromVector(getContext(),R.drawable.pinkooldoc)));

                mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

                    @Override
                    public void onMapClick(@NonNull LatLng latLng) {
                        MarkerOptions markerOptions = new MarkerOptions();
                        markerOptions.position(latLng);
                        markerOptions.title(" KoolDoc " +latLng.latitude + latLng.longitude);
                        googleMap.clear();
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 20));
                        googleMap.addMarker(markerOptions);
                    }

                });
            }

            private BitmapDescriptor bitmapDescriptorFromVector(Context context, int vectorResId) {
                Drawable vectorDrawable= ContextCompat.getDrawable(context,vectorResId);
                vectorDrawable.setBounds(0,0,vectorDrawable.getIntrinsicWidth(),
                        vectorDrawable.getIntrinsicHeight());
                Bitmap bitmap=Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(),
                        vectorDrawable.getIntrinsicHeight(),Bitmap.Config.ARGB_8888);
                Canvas canvas=new Canvas(bitmap);
                vectorDrawable.draw(canvas);
                return BitmapDescriptorFactory.fromBitmap(bitmap);
            }

        });
        return view;
    }


}