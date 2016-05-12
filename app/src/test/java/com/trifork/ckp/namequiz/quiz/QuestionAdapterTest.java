package com.trifork.ckp.namequiz.quiz;

import android.test.AndroidTestCase;
import android.view.View;
import android.view.ViewGroup;

import com.trifork.ckp.namequiz.fakes.FakeAnswerOptionsFactory;
import com.trifork.ckp.namequiz.fakes.FakePersonsFactory;
import com.trifork.ckp.namequiz.model.AnswerOption;
import com.trifork.ckp.namequiz.model.Person;
import com.trifork.ckp.namequiz.model.Question;
import com.trifork.ckp.namequiz.quiz.question.QuestionAdapter;
import com.trifork.ckp.namequiz.quiz.question.QuestionLayout;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;

public class QuestionAdapterTest extends AndroidTestCase {

    private QuestionAdapter questionAdapter;

    @Mock
    private View view;

    @Mock
    private ViewGroup container;

    @Mock
    private List<QuestionLayout> questionLayouts;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        MockitoAnnotations.initMocks(this);
        setContext(getContext());
        questionAdapter = new QuestionAdapter(questionLayouts);
    }

    @Test
    public void testGetCount() {
        assertEquals(questionLayouts.size(), questionAdapter.getCount());
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
}
