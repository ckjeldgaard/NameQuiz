package com.trifork.ckp.namequiz.start;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.trifork.ckp.namequiz.MainActivity;
import com.trifork.ckp.namequiz.R;
import com.trifork.ckp.namequiz.model.QuestionResult;
import com.trifork.ckp.namequiz.util.MaximumQuizQuestions;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
public class QuizScreenTest {

    private static final int NUMBER_OF_QUESTIONS = new MaximumQuizQuestions().number();

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void completingAQuiz_opensResultUi() throws Exception {
        performQuiz();
        onView(withId(R.id.screenResult)).check(matches(isDisplayed()));

        // Check that the checklist ListView contains results for all answers:
        onData(instanceOf(QuestionResult.class))
                .inAdapterView(allOf(withId(R.id.checklist), isDisplayed()))
                .atPosition(NUMBER_OF_QUESTIONS-1)
                .check(matches(isDisplayed()));
    }

    /**
     * Helper method to perform a quiz from start screen to result screen answering only
     * the first option throughout the quiz.
     */
    private void performQuiz() {
        onView(withId(R.id.start_button)).perform(click());
        for (int i = 0; i < NUMBER_OF_QUESTIONS; i++) {
            onView(allOf(withId(R.id.option_1), isDisplayed())).perform(click());
            onView(withId(R.id.button_next)).perform(click());
        }
    }

}
