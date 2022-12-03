package com.example.eventmegroup;


import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;

//import org.junit.runner.RunWith;
//
//@RunWith()
@LargeTest
public class SetUpEspTest {

    @Rule public ActivityScenarioRule<SetUpActivity> setUpRule
            = new ActivityScenarioRule<>(SetUpActivity.class);

    public static final String name_to_be_typed = "John Snow";
    public static final String bday_to_be_typed = "01/01/2001";

    @Test
    public void Test_Changing_Name() throws InterruptedException {
        Thread.sleep(2000);
        onView(withId(R.id.profile_name)).perform(clearText());
        onView(withId(R.id.profile_name)).perform(typeText(name_to_be_typed));
        closeSoftKeyboard();

        onView(withId(R.id.edit_btn)).perform(click());
        Thread.sleep(2000);

        onView(withId(R.id.profile_name)).check(matches(withText(name_to_be_typed)));
    }

    @Test
    public void Test_Changing_Bday() throws InterruptedException {
        Thread.sleep(2000);
        onView(withId(R.id.bday)).perform(clearText());
        onView(withId(R.id.bday)).perform(typeText(bday_to_be_typed));
        closeSoftKeyboard();

        onView(withId(R.id.edit_btn)).perform(click());
        Thread.sleep(2000);

        onView(withId(R.id.bday)).check(matches(withText(bday_to_be_typed)));
    }

}
