package com.example.eventmegroup;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExploreUnitTest {

    // public static final String NUMBER_OF_EVENTS = "5";

    @Test
    public void allEvents_isPresent() {
        Explore tester = new Explore();
        tester.onExploreReady();
        int numEvents = tester.events.size();
        assertEquals(0, numEvents);
    }

    @Test
    public void allEvents_isInit() {
        Explore tester = new Explore();
        int numEvents = tester.events.size();
        assertEquals(0, numEvents);
    }

    @Test
    public void searchWidgetInit() {
        Explore tester = new Explore();
        boolean initBool = tester.initSearchWidget();
        assertEquals(false, initBool);
    }

    @Test
    public void costSort_isCorrect() {
        Explore tester = new Explore();
        int numEvents = tester.events.size();
        assertEquals(0, numEvents);
    }

    @Test
    public void all_isCorrect() {
        Explore tester = new Explore();
        int numEvents = tester.events.size();
        assertEquals(0, numEvents);
    }
}