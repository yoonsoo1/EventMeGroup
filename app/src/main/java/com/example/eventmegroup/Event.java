package com.example.eventmegroup;

import java.sql.Time;
import java.util.Date;


public class Event {
    private String name, email, location, description, type, sponsorOrg, date, time;

    // CONSTRUCTORS
    public Event(){

    }
    public Event(String n, String e, String d, String t, String l, String desc, String eventType, String sponsoringOrg){
        name = n;
        email = e;
        date = d;
        time = t;
        location = l;
        description = desc;
        type = eventType;
        sponsorOrg = sponsoringOrg;
    }

    // GETTERS
    public String getName(){
        return name;
    }
    public String getEmail(){
        return email;
    }
    public String getDate(){
        return date;
    }
    public String getTime(){
        return time;
    }
    public String getLocation(){
        return location;
    }
    public String getDescription(){
        return description;
    }
    public String getEventType(){
        return type;
    }
    public String getSponsorOrg(){
        return sponsorOrg;
    }

    // SETTERS
    public void setName(String n){
        name = n;
    }
    public void setEmail(String e){
        email = e;
    }
    public void setDate(String d){
        date = d;
    }
    public void setTime(String t){
        time = t;
    }
    public void setLocation(String loc){
        location = loc;
    }
    public void setDescription(String desc){
        description = desc;
    }
    public void setEventType(String eventType){
        type = eventType;
    }
    public void setSponsorOrg(String sponsoringOrg){
        sponsorOrg = sponsoringOrg;
    }
}
