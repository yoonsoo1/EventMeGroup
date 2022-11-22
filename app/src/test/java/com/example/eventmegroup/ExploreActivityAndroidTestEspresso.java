package com.example.eventmegroup;

import android.app.Activity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class ExploreActivityAndroidTestEspresso {

    @Rule public ActivityScenarioRule<Explore> activityScenarioRule
            = new ActivityScenarioRule<>(Explore.class);


    @Test
    public void Test_ClickEvent(){  // show event box
        onView(withId(R.id.listEvents)).perform(click());
        onView(withId(R.id.listEvents)).check(matches(isClickable()));
        onView(withId(R.id.listEvents)).check(matches(isDisplayed()));
    }

    @Test
    public void Test_PartyButton() {
        onView(withId(R.id.partybutton)).perform(click());
        onView(withId(R.id.partybutton)).check(matches(isClickable()));
        onView(withId(R.id.listEvents)).check(matches(isDisplayed()));
    }

    @Test
    public void Test_ShoppingButton() {
        onView(withId(R.id.shopbutton)).perform(click());
        onView(withId(R.id.shopbutton)).check(matches(isClickable()));
        onView(withId(R.id.listEvents)).check(matches(isDisplayed()));
    }

    @Test
    public void Test_AllButton() {
        onView(withId(R.id.allbutton)).perform(click());
        onView(withId(R.id.allbutton)).check(matches(isClickable()));
        onView(withId(R.id.listEvents)).check(matches(isDisplayed()));
    }

    @Test
    public void Test_CostButton() {
        onView(withId(R.id.costsort)).perform(click());
        onView(withId(R.id.costsort)).check(matches(isClickable()));
        onView(withId(R.id.listEvents)).check(matches(isDisplayed()));
    }

    @Test
    public void Test_ProximityButton() {
        onView(withId(R.id.proximitysort)).perform(click());
        onView(withId(R.id.proximitysort)).check(matches(isClickable()));
        onView(withId(R.id.listEvents)).check(matches(isDisplayed()));
    }

    @Test
    public void Test_DateButton() {
        onView(withId(R.id.datesort)).perform(click());
        onView(withId(R.id.datesort)).check(matches(isClickable()));
        onView(withId(R.id.listEvents)).check(matches(isDisplayed()));
    }

    @Test
    public void Test_AlphabeticalButton() {
        onView(withId(R.id.alphabetsort)).perform(click());
        // onView(withId(R.id.listEvents)).check(matches(isClickable()));
        onView(withId(R.id.alphabetsort)).check(matches(isClickable()));
        onView(withId(R.id.listEvents)).check(matches(isDisplayed()));
    }

}

