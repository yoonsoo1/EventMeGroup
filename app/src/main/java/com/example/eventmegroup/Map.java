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

public class Map extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.InfoWindowAdapter {
    private GoogleMap mMap;
    private FirebaseFirestore db;
    private String uid;
    private FirebaseAuth auth;
    private Context context;

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

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        uid = auth.getCurrentUser().getUid();

        db.collection("Events").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot document : task.getResult()){
                        String eventId = document.getId();
//                        String name = (String) document.get("name");
//                        String addy = (String) document.get("location");
//                        String time = (String) document.get("time");
//                        String cost = (String) document.get("cost");
//                        String date = (String) document.get("date");
//                        String sponsor = (String) document.get("sponsor");

                        LatLng loc = new LatLng(Double.parseDouble((String) document.get("lat")), Double.parseDouble((String) document.get("long")));
                        mMap.addMarker(new MarkerOptions().position(loc).title(eventId)
                        );

                        mMap.moveCamera(CameraUpdateFactory.newLatLng(loc));
                    }
                }
            }
        });

        mMap.setInfoWindowAdapter(this);
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
        Intent intent = new Intent(this, Search.class);
        startActivity(intent);
    }

    @Nullable
    @Override
    public View getInfoWindow(@NonNull Marker marker) {

        View v = getLayoutInflater().inflate(R.layout.map_box, null);
        TextView mapName = (TextView) v.findViewById(R.id.e_name);

//        mapName.setText("test string");
//
        String eventId = marker.getTitle();
        db.collection("Events").document(eventId).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
             //       for(QueryDocumentSnapshot document : task.getResult()){
                        String name = (String) task.getResult().getString("name");
                        String loc = (String) task.getResult().getString("location");
                        String time = (String) task.getResult().getString("time");
                        String cost = (String) task.getResult().getString("cost");
                        String date = (String) task.getResult().getString("date");
                        String sponsor = (String) task.getResult().getString("sponsor");

                        System.out.println(name);


                        mapName.setText("test");
//                        mapName.setText(name);

                }
            }
        });

        return v;
    }

    @Nullable
    @Override
    public View getInfoContents(@NonNull Marker marker) {
        return null;
    }
}
