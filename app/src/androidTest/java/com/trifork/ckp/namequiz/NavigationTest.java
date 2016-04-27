package com.trifork.ckp.namequiz;

import android.support.test.rule.ActivityTestRule;

import com.trifork.ckp.namequiz.start.StartLayout;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class NavigationTest {

    @Rule public ActivityTestRule rule = new ActivityTestRule<>(MainActivity.class);

    /** Verifies that the app is in its default state on a cold start. */
    @Test public void defaultKeyIsUsed() {
        onView(withId(R.id.activity_main))
                .check(matches(hasDescendant(isAssignableFrom(StartLayout.class))));
    }
}
