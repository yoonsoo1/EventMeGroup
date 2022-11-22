package com.example.eventmegroup;

import java.util.ArrayList;

public class User {
    private String uid;
    private ArrayList<String> registeredEvents;
    User(String uid) {
        this.uid = uid;
    }

    public void setRegisteredEvents(ArrayList<String> registeredEvents) {
        this.registeredEvents = registeredEvents;
    }

    public ArrayList<String> getRegisteredEvents() {
        return registeredEvents;
    }

    public int registeredEvent(String event) {
        if(registeredEvents == null) {
            return 1;
        }
        else {
            if(registeredEvents.contains(event)) {
                return 2;
            }
            else {
                return 3;
            }
        }
    }
}
