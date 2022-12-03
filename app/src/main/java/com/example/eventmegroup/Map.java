package com.example.eventmegroup;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
//import com.example.eventmegroup.databinding.ActivityMapsBinding;

public class Map extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.InfoWindowAdapter, GoogleMap.OnInfoWindowClickListener {
    private GoogleMap mMap;
    private FirebaseFirestore db;
    private FirebaseAuth auth;
    private String eventId;


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

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.getUiSettings().setMapToolbarEnabled(true);

        db.collection("Events").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot document : task.getResult()){
                        eventId = document.getId();
                        LatLng loc = new LatLng(Double.parseDouble((String) document.get("lat")), Double.parseDouble((String) document.get("long")));
                        mMap.addMarker(new MarkerOptions().position(loc).title(eventId)
                        );

                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, 14F));
                    }
                }
            }
        });

        mMap.setInfoWindowAdapter(this);
        mMap.setOnInfoWindowClickListener(this);
    }

//    @Override
//    public boolean onMarkerClick(Marker marker) {
//        mMap.setInfoWindowAdapter(new MarkerAdapter(getActivity(), marker
//                .getTitle(), marker.getSnippet()));
//        return false;
//    }

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
        Intent intent = new Intent(this, Explore.class);
        startActivity(intent);
    }

    @Override
    public void onInfoWindowClick(@NonNull Marker marker) {
        Intent details = new Intent(Map.this, Detail.class);
        details.putExtra("eventId", eventId);
        startActivity(details);
    }

    String eName;
    String eLoc;
    String eTime;
    String eCost;
    String eDate;
    String eSpons;
    View infoV;
    @Nullable
    @Override
    public View getInfoWindow(@NonNull Marker marker) {

        eventId = marker.getTitle();

        db.collection("Events").document(eventId).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    //       for(QueryDocumentSnapshot document : task.getResult()){
                    eName = (String) task.getResult().getString("name");
                    eLoc = (String) task.getResult().getString("location");
                    eTime = (String) task.getResult().getString("time");
                    eCost = (String) task.getResult().getString("cost");
                    eDate = (String) task.getResult().getString("date");
                    eSpons = (String) task.getResult().getString("sponsor");

                }
                infoV = getLayoutInflater().inflate(R.layout.map_box, null);
                TextView mapName = (TextView) infoV.findViewById(R.id.e_name);
                TextView locTv = infoV.findViewById(R.id.e_loc);
                TextView timeTv = infoV.findViewById(R.id.e_time);
                TextView costTv = infoV.findViewById(R.id.e_cost);
                TextView dateTv = infoV.findViewById(R.id.e_date);
                TextView sponTv = infoV.findViewById(R.id.e_spon);

                mapName.setText(eName);
                locTv.setText(eLoc);
                timeTv.setText(eTime);
                costTv.setText(eCost);
                dateTv.setText(eDate);
                sponTv.setText(eSpons);
            }

            public void onSwipe(){}
        });
        return infoV;
    }

    @Nullable
    @Override
    public View getInfoContents(@NonNull Marker marker) {
        return null;
    }
}
