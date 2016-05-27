package com.trifork.ckp.namequiz.result;

import android.support.test.InstrumentationRegistry;
import android.support.test.annotation.UiThreadTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.trifork.ckp.namequiz.MainActivity;
import com.trifork.ckp.namequiz.R;
import com.trifork.ckp.namequiz.model.QuestionResult;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class AnswerResultListAdapterTest {

    private AnswerResultListAdapter adapter;

    private List<QuestionResult> questionResults;

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() throws Exception {
        questionResults = new ArrayList<>(2);
        questionResults.add(new QuestionResult("John", "Joe", "null"));
        questionResults.add(new QuestionResult("Jeff", "Jeff", "null"));

        adapter = new AnswerResultListAdapter(
                InstrumentationRegistry.getTargetContext(),
                R.layout.answer_result_list_item,
                questionResults
        );
    }

    @Test
    public void getViewTestObtainingViewReferences() {
        // Run this test on UI thread due to dependency on Picasso
        InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                View view = adapter.getView(0, null, null);

                ImageView imagePerson = (ImageView) view.findViewById(R.id.image_answer_result_person);
                TextView textHeadline = (TextView) view.findViewById(R.id.text_answer_result_headline);
                TextView textExplanation = (TextView) view.findViewById(R.id.text_answer_result_explanation);

                Assert.assertNotNull("View is null. ", view);
                Assert.assertNotNull("imagePerson ImageView is null. ", imagePerson);
                Assert.assertNotNull("textHeadline TextView is null. ", textHeadline);
                Assert.assertNotNull("textExplanation TextView is null. ", textExplanation);
            }
        });
    }

    @Test
    public void getViewWrongAnswer() {
        // Run this test on UI thread due to dependency on Picasso
        InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                View view = adapter.getView(0, null, null);
                
                TextView textHeadline = (TextView) view.findViewById(R.id.text_answer_result_headline);
                TextView textExplanation = (TextView) view.findViewById(R.id.text_answer_result_explanation);

                String wrongHeadline = InstrumentationRegistry.getInstrumentation().getTargetContext().getString(R.string.list_item_answer_result_headline, 1, "Wrong");
                String wrongExplanation = InstrumentationRegistry.getInstrumentation().getTargetContext().getString(R.string.list_item_answer_result_explanation_wrong, questionResults.get(0).correctResult(), questionResults.get(0).answerGiven());
                Assert.assertEquals(wrongHeadline, textHeadline.getText());
                Assert.assertEquals(wrongExplanation, textExplanation.getText());
            }
        });
    }

    @Test
    public void getViewCorrectAnswer() {
        // Run this test on UI thread due to dependency on Picasso
        InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                View view = adapter.getView(1, null, null);

                TextView textHeadline = (TextView) view.findViewById(R.id.text_answer_result_headline);
                TextView textExplanation = (TextView) view.findViewById(R.id.text_answer_result_explanation);

                String correctHeadline = InstrumentationRegistry.getInstrumentation().getTargetContext().getString(R.string.list_item_answer_result_headline, 2, "Correct");
                String correctExplanation = InstrumentationRegistry.getInstrumentation().getTargetContext().getString(R.string.list_item_answer_result_explanation_correct, questionResults.get(1).correctResult());
                Assert.assertEquals(correctHeadline, textHeadline.getText());
                Assert.assertEquals(correctExplanation, textExplanation.getText());
            }
        });
    }
}