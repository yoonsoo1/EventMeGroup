package com.example.eventmegroup;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.*;

public class EventRegUnitTest {
    @Test
    public void testNotRegistered() {
        User currU = new User("1155");

        assertTrue(1 == currU.registeredEvent("event15"));
        assertTrue(1 == currU.registeredEvent("event12"));
        assertTrue(1 == currU.registeredEvent("event13"));
        assertTrue(1 == currU.registeredEvent("event4"));

        ArrayList<String> events = new ArrayList<>(Arrays.asList("event1", "event2", "event3"));
        currU.setRegisteredEvents(events);
        assertTrue(3 == currU.registeredEvent("event15"));
        assertTrue(3 == currU.registeredEvent("event12"));
        assertTrue(3 == currU.registeredEvent("event13"));
        assertTrue(3 == currU.registeredEvent("event4"));
    }

    @Test
    public void testRegistered() {
        User currU = new User("1155");

        ArrayList<String> events = new ArrayList<>(Arrays.asList("event1", "event2", "event3"));
        currU.setRegisteredEvents(events);
        assertTrue(2 == currU.registeredEvent("event1"));
        assertTrue(2 == currU.registeredEvent("event3"));
        assertTrue(2 == currU.registeredEvent("event2"));
        assertTrue(2 == currU.registeredEvent("event3"));
    }
}
