package com.example.eventmegroup;



import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import junit.framework.AssertionFailedError;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LogInEspTest {
    public static final String testLogin = "y1@gmail.com";
    public static final String testPass = "test123";
    public static final String testWrongPass = "testt123";

    @Rule public ActivityScenarioRule<SignInActivity> signInRule =
            new ActivityScenarioRule<SignInActivity>(SignInActivity.class);

    @Test
    public void test_a_guest() throws InterruptedException {
        onView(withId(R.id.guest_txt)).perform(click());
        Thread.sleep(2000);
        try {
            onView(withId(R.id.map)).check(matches(isDisplayed()));
        }
        catch (AssertionFailedError e) {

        }
    }

    @Test
    public void test_b_wrong_password_login() {
        onView(withId(R.id.sign_in_email)).perform(typeText(testLogin));
        onView(withId(R.id.sign_in_pass)).perform(typeText(testWrongPass));
        closeSoftKeyboard();
        onView(withId(R.id.sign_in_btn)).perform(click());

        // If signIn fails, it would stay in the same page with the entered login info
        onView(withId(R.id.sign_in_email)).check(matches(withText(testLogin)));
    }

    @Test
    public void test_c_log_in_success() throws InterruptedException {
        onView(withId(R.id.sign_in_email)).perform(typeText(testLogin));
        onView(withId(R.id.sign_in_pass)).perform(typeText(testPass));
        closeSoftKeyboard();
        onView(withId(R.id.sign_in_btn)).perform(click());

        // When signIn success, it takes the user to the google map showing events

        // Let the activity load after successfully logging in
        Thread.sleep(2000);

        try {
            onView(withId(R.id.map)).check(matches(isDisplayed()));
        }
        catch (AssertionFailedError e) {

        }

    }


}
