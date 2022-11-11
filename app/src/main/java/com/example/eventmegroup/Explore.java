package com.example.eventmegroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Explore extends AppCompatActivity {

    private LinearLayoutCompat eventContainer;
    public ArrayList<Event> events;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);

    }

    public void onExploreReady(){

    }

    public Explore(){

    }

    // filter based on event type
    public ArrayList eventTypeFilter(){
        return events;
    }

    // search via name, location, sponsoring org
    public ArrayList eventSearch(){
        return events;
    }

    // range of dates
    public ArrayList eventDates(){
        return events;
    }

    // sort via cost, proximity, date, alphabetical
    public ArrayList eventSort(){
        return events;
    }











}


