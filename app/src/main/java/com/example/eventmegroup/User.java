package com.example.eventmegroup;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;

public class User {
    private String uid;
    private ArrayList<String> registeredEvents;
    private ArrayList<EventDate> eventDates;

    User(String uid) {
        this.uid = uid;
    }

    public void setRegisteredEvents(ArrayList<String> registeredEvents) {
        this.registeredEvents = registeredEvents;
    }

    public ArrayList<String> getRegisteredEvents() {
        return registeredEvents;
    }

    public ArrayList<EventDate> getEventDates() {
        return eventDates;
    }

    public void addEvent(String eventId, EventDate date) {
//        System.out.println("registedEvents: " + registeredEvents.toString());
//        System.out.println("eventDates : " + eventDates.toString());
        if(registeredEvents == null) {
            eventDates = new ArrayList<EventDate>();
            registeredEvents = new ArrayList<String>();
        }
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

    public void setEventDates(ArrayList<EventDate> eventDates) {
        this.eventDates = eventDates;
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
        for(EventDate ed : eventDates) {
            if(ed.getYear() == newEvent.getYear()
                    && ed.getMonth() == newEvent.getMonth())
            {
                if(ed.getStartDay() <= newEvent.getEndDay() &&
                newEvent.getStartDay() <= ed.getEndDay()) {
                    int edStart = ed.getStartHour() * 100 + ed.getStartMin();
                    int edEnd = ed.getEndHour() * 100 + ed.getEndMin();
                    int neStart = newEvent.getStartHour() * 100 + newEvent.getStartMin();
                    int neEnd = newEvent.getEndHour() * 100 + newEvent.getEndMin();

                    if(ed.getEndDay() > ed.getStartDay()) {
                        edStart += ( ed.getEndDay() - ed.getStartDay() ) * 24 * 100;
                    }
                    if(newEvent.getStartDay() > newEvent.getEndDay()) {
                        neStart += ( newEvent.getEndDay() - newEvent.getStartDay() * 24 * 100 );
                    }

                    // Check overlap in hours
                    if(edStart <= neEnd && neStart <= edEnd) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}


