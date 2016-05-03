package com.trifork.ckp.namequiz.quiz;

import android.test.AndroidTestCase;
import android.test.mock.MockContext;
import android.view.View;
import android.view.ViewGroup;

import com.trifork.ckp.namequiz.model.Question;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;

public class QuestionAdapterTest extends AndroidTestCase {

    private static List<Question> QUESTIONS = new ArrayList<Question>() {{
        add(new Question());
        add(new Question());
        add(new Question());
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
        questionAdapter = new QuestionAdapter(new MockContext(), QUESTIONS);
    }

    public void testGetCount() {
        assertEquals(QUESTIONS.size(), questionAdapter.getCount());
    }

    public void testIsViewFromObject() throws Exception {
        assertEquals(true, questionAdapter.isViewFromObject(view, view));
    }

    public void testDestroyItem() throws Exception {
        questionAdapter.destroyItem(container, 0, view);

        verify(container).removeView(view);
    }

}
