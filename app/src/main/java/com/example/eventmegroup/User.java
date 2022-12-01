package com.example.eventmegroup;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;

public class User {
    private String uid;
    private ArrayList<String> registeredEvents;
    private ArrayList<EventDate> eventDates;

    User(String uid) {
        this.uid = uid;
        eventDates = new ArrayList<>();
        registeredEvents = new ArrayList<>();
    }

    public void setRegisteredEvents(ArrayList<String> registeredEvents) {
        this.registeredEvents = registeredEvents;
    }

    public ArrayList<String> getRegisteredEvents() {
        return registeredEvents;
    }

    public void addEvent(String eventId, EventDate date) {
        registeredEvents.add(eventId);
        eventDates.add(date);
    }

    public int removeEvent(String eventId) {
        int idx = -1;
        for(int i = 0; i < registeredEvents.size(); i++) {
            if(registeredEvents.get(i).equals(eventId)) {
                idx = i;
            }
        }
        if(idx == -1) {
            return 1;
        }
        registeredEvents.remove(idx);
        eventDates.remove(idx);
        return 0;
    }

    public void setEventDates(ArrayList<String> eventDatesStr) {
        String currDates;
        for(int i = 0; i < eventDates.size(); i++) {
            currDates = eventDatesStr.get(i);
            // Array of eventDate is a concat string of date + time + duration
            String[] eventD = currDates.split(" ");
            EventDate ed = new EventDate(eventD[0], eventD[1], eventD[2]);
            eventDates.add(ed);
        }
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

    public boolean eventConf(EventDate newEvent) {
        // Check if the new event has any conflicts with the events in eventDates
        return false;
    }
}


