package com.example.eventmegroup;

import java.sql.Time;
import java.util.Date;


public class Event {
    private String name, email, location, description;
    private Date date;
    private Time time;

    // CONSTRUCTORS
    public Event(){

    }
    public Event(String n, String e, Date d, Time t, String l, String desc){
        name = n;
        email = e;
        date = d;
        time = t;
        location = l;
        description = desc;
    }

    // GETTERS
    public String getName(){
        return name;
    }
    public String getEmail(){
        return email;
    }
    public Date getDate(){
        return date;
    }
    public Time getTime(){
        return time;
    }
    public String getLocation(){
        return location;
    }
    public String getDescription(){
        return description;
    }

    // SETTERS
    public void setName(String n){
        name = n;
    }
    public void setEmail(String e){
        email = e;
    }
    public void setDate(Date d){
        date = d;
    }
    public void setTime(Time t){
        time = t;
    }
    public void setLocation(String loc){
        location = loc;
    }
    public void setDescription(String desc){
        description = desc;
    }
}
