package com.example.eventmegroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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
    private FirebaseAuth auth;
    private FirebaseFirestore db;
    public ArrayList<Event> events;
    private String uid;
    private String eventID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);

        onExploreReady();

    }

    public void onExploreReady(){
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        uid = auth.getCurrentUser().getUid();

        db.collection("Events").document(uid).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    if(task.getResult().exists()) {
                        String eventDate = task.getResult().getString("date");
                        String eventTime = task.getResult().getString("time");
                        String eventCost = task.getResult().getString("cost");
                        String eventName = task.getResult().getString("name");
                        String eventSponsor = task.getResult().getString("sponsor");
                    }
                }
            }
        });

    }

    public Explore(){

    }

    // filter based on event type
    public ArrayList<Event> eventTypeFilter(){

        return events;
    }

    // search via name, location, sponsoring org
    public ArrayList<Event> eventSearch(){
        return events;
    }

    // range of dates
    public ArrayList<Event> eventDates(){
        return events;
    }

    // sort via cost, proximity, date, alphabetical
    public ArrayList<Event> eventSort(){
        return events;
    }







}


