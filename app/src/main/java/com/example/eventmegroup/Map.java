package com.example.eventmegroup;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.eventmegroup.databinding.ActivityMapsBinding;

public class Map extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.InfoWindowAdapter {
    private GoogleMap mMap;
    Context context = null;
    LayoutInflater inflater;
//    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney").title("Melbourne"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

//        mMap.setInfoWindowAdapter(new Map(this));
        mMap.setInfoWindowAdapter(this);
    }

    public Map(){
//        this.context = context;
    }


    //Nav Bar functions
    public void goProfile(View view){
        Intent intent = new Intent(this, Profile.class);
        startActivity(intent);
    }

    public void goMap(View view){
        Intent intent = new Intent(this, Map.class);
        startActivity(intent);
    }

    public void goSearch(View view){
        Intent intent = new Intent(this, Search.class);
        startActivity(intent);
    }

    @Nullable
    @Override
    public View getInfoWindow(@NonNull Marker marker) {
        View v = getLayoutInflater().inflate(R.layout.map_box, null);
        return v;
    }

    @Nullable
    @Override
    public View getInfoContents(@NonNull Marker marker) {

        return null;
    }
}
