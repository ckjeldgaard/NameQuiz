package com.trifork.ckp.namequiz.quiz;

import android.test.AndroidTestCase;
import android.view.View;
import android.view.ViewGroup;

import com.trifork.ckp.namequiz.model.AnswerOption;
import com.trifork.ckp.namequiz.model.Department;
import com.trifork.ckp.namequiz.model.Gender;
import com.trifork.ckp.namequiz.model.Person;
import com.trifork.ckp.namequiz.model.Question;
import com.trifork.ckp.namequiz.quiz.question.QuestionAdapter;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;

public class QuestionAdapterTest extends AndroidTestCase {

    private static List<AnswerOption> ANSWER_OPTIONS = new ArrayList<AnswerOption>() {{
        add(new AnswerOption("Jeff"));
        add(new AnswerOption("Joe"));
        add(new AnswerOption("James"));
    }};
    private static List<Question> QUESTIONS = new ArrayList<Question>() {{
        add(new Question(new Person(
                "John",
                "John Doe",
                new Department(1, "Copenhagen"),
                null,
                Gender.MALE
        ), ANSWER_OPTIONS));
        add(new Question(new Person(
                "John",
                "John Doe",
                new Department(1, "Copenhagen"),
                null,
                Gender.MALE
        ), ANSWER_OPTIONS));
        add(new Question(new Person(
                "John",
                "John Doe",
                new Department(1, "Copenhagen"),
                null,
                Gender.MALE
        ), ANSWER_OPTIONS));
    }};

    private QuestionAdapter questionAdapter;

    @Mock
    private View view;

    @Mock
    private ViewGroup container;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        MockitoAnnotations.initMocks(this);
        setContext(getContext());
        questionAdapter = new QuestionAdapter(QUESTIONS, getContext());
    }

    @Test
    public void testGetCount() {
        assertEquals(QUESTIONS.size(), questionAdapter.getCount());
    }

    @Test
    public void testIsViewFromObject() throws Exception {
        assertEquals(true, questionAdapter.isViewFromObject(view, view));
    }

    @Test
    public void testDestroyItem() throws Exception {
        questionAdapter.destroyItem(container, 0, view);

        verify(container).removeView(view);
    }

    @Test
    public void testGetItem() throws Exception {
        Question question = questionAdapter.getItem(0);

        assertEquals(QUESTIONS.get(0), question);
    }
}
