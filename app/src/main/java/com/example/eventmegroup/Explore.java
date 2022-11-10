package com.example.eventmegroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Explore extends AppCompatActivity {

    private LinearLayoutCompat eventContainer;

        Date date1 = new Date();
        Time time1 = new Time(60, 60, 60);

        Event e1 = new Event("name1", "email1", date1, time1, "loc1", "desc1", "type1", "org1");
        Event e2 = new Event("name1", "email2", date1, time1, "loc1", "desc1", "type1", "org1");
        Event e3 = new Event("name1", "email3", date1, time1, "loc1", "desc1", "type2", "org2");
        Event e4 = new Event("name1", "email4", date1, time1, "loc1", "desc1", "type2", "org2");

        ArrayList<Event> eventArrayList = new ArrayList<>();

        eventArrayList.add(e1);
        eventArrayList.add(e2);
        eventArrayList.add(e3);
        eventArrayList.add(e4);

        for(Event e: eventArrayList){
            if(e.)
        }











}


