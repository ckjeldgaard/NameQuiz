package com.trifork.ckp.namequiz.result;

import android.support.test.InstrumentationRegistry;
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

import java.util.ArrayList;
import java.util.List;

import flow.Flow;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class ResultScreenTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    public void setUpResultScreen(final int score, final List<QuestionResult> questionResults) throws Exception {
        InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                Flow.get(activityTestRule.getActivity().getBaseContext()).set(
                        new ResultScreen(
                                score,
                                questionResults
                        )
                );
            }
        });
    }

    private List<QuestionResult> dummyQuestionResults() {
        int numQuestions = new MaximumQuizQuestions().number();
        List<QuestionResult> questionResults = new ArrayList<>(numQuestions);
        for (int i = 0; i < numQuestions; i++) {
            questionResults.add(new QuestionResult("Joe", "Joe", ""));
        }
        return questionResults;
    }

    @Test
    public void testAllCorrectAnswers() throws Exception {
        setUpResultScreen(10, dummyQuestionResults());

        onView(withText("Congratulations")).check(matches(isDisplayed()));
        onView(withText("You scored 10 out of 10")).check(matches(isDisplayed()));
    }

    @Test
    public void testAllWrongAnswers() throws Exception {
        setUpResultScreen(0, dummyQuestionResults());

        onView(withText("You should practice")).check(matches(isDisplayed()));
        onView(withText("You scored 0 out of 10")).check(matches(isDisplayed()));
    }

    @Test
    public void testNeutralScore() throws Exception {
        setUpResultScreen(5, dummyQuestionResults());

        onView(withText("There’s some room for improvement")).check(matches(isDisplayed()));
        onView(withText("You scored 5 out of 10")).check(matches(isDisplayed()));
    }

    @Test
    public void pressingBackButtonOnResultPage_opensStartUi() throws Exception {
        setUpResultScreen(5, dummyQuestionResults());
        Espresso.pressBack();
        onView(withId(R.id.screenStart)).check(matches(isDisplayed()));
    }
}
