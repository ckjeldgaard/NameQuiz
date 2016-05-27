package com.trifork.ckp.namequiz.start;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.trifork.ckp.namequiz.MainActivity;
import com.trifork.ckp.namequiz.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class StartScreenTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void clickStartButton_opensQuizUi() throws Exception {
        onView(withId(R.id.start_button)).perform(click());
        onView(withId(R.id.screenQuiz)).check(matches(isDisplayed()));
    }
}