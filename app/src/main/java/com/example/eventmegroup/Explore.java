package com.example.eventmegroup;

import static android.widget.AdapterView.*;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.AdapterView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
// import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class Explore extends AppCompatActivity {

    private LinearLayoutCompat eventContainer;
    private FirebaseAuth auth;
    private FirebaseFirestore db;
    public static ArrayList<Event> allEvents = new ArrayList<Event>();
    public ArrayList<Event> events = new ArrayList<Event>();
    private String searchName;
    private ListView listView;

    private Button allFilter, partyFilter, shoppingFilter, costSort, proximitySort, dateSort, alphabetSort;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);

        onExploreReady();
        setUpList();
        setUpOnclickListener();
        initSearchWidget();

        listView = findViewById(R.id.listEvents);

        // ----- SORTING FUNCTIONS
        costSort = findViewById(R.id.costsort);
        proximitySort = findViewById(R.id.proximitysort);
        dateSort = findViewById(R.id.datesort);
        alphabetSort = findViewById(R.id.alphabetsort);

        costSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        proximitySort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        dateSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        alphabetSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



        // ----- FILTER FUNCTIONS
        allFilter = findViewById(R.id.allbutton);
        partyFilter = findViewById(R.id.partybutton);
        shoppingFilter = findViewById(R.id.shopbutton);

        allFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

        partyFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        shoppingFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    public void onExploreReady(){
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        List<Event> eventsList = new ArrayList<>();

        CollectionReference query = db.collection("Events");

        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                eventsList.clear();
                for(QueryDocumentSnapshot postSnapshot: Objects.requireNonNull(task.getResult())){
                    Event event = (Event) postSnapshot.getData();
                    eventsList.add(event);
                    events.add(event);
                }
            }
        });

    }

    public Explore(){

    }

    /*
    // filter based on event type
    public ArrayList<Event> eventTypeFilter(){
        ArrayList<Event> resultList = new ArrayList<Event>();

        allFilter = findViewById(R.id.allbutton);
        partyFilter = findViewById(R.id.partybutton);
        shoppingFilter = findViewById(R.id.shopbutton);

        allFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

        partyFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        shoppingFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        return resultList;
    }

    // sort via cost, proximity, date, alphabetical
    public ArrayList<Event> eventSort(){
        ArrayList<Event> resultList = new ArrayList<Event>();

        costSort = findViewById(R.id.costsort);
        proximitySort = findViewById(R.id.proximitysort);
        dateSort = findViewById(R.id.datesort);
        alphabetSort = findViewById(R.id.alphabetsort);

        costSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

        proximitySort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        dateSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        alphabetSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return resultList;
    }

    */


    public boolean initSearchWidget(){  // search via name, location, sponsoring org
        SearchView searchView = (SearchView) findViewById(R.id.search_bar);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                ArrayList<Event> filteredEvents = new ArrayList<Event>();
                for(Event event: events) {
                    if(event.getName().toLowerCase().contains(s.toLowerCase())
                    || event.getLocation().toLowerCase().contains(s.toLowerCase()) ||
                            event.getSponsorOrg().toLowerCase().contains(s.toLowerCase())){
                        filteredEvents.add(event);
                    }
                }

                EventAdapter adapter = new EventAdapter(getApplicationContext(), 0, filteredEvents);
                listView.setAdapter(adapter);

                return false;
            }
        });
        return false;
    }

    private void setUpList() {

        listView = (ListView) findViewById(R.id.listEvents);
        EventAdapter adapter = new EventAdapter(getApplicationContext(), 0, events);
        listView.setAdapter(adapter);

    }

    private void setUpOnclickListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
            {
                Event selectEvent = (Event) (listView.getItemAtPosition(position));
                Intent showDetail = new Intent(getApplicationContext(), Event.class);
                startActivity(showDetail);
            }
        });
    }








}


