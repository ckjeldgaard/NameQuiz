package com.trifork.ckp.namequiz.result;

import com.trifork.ckp.namequiz.model.QuestionResult;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.verify;


public class ResultPresenterTest {

    private static final int SCORE = 7;

    @Mock
    private ResultContract.ResultView resultView;

    @Mock
    List<QuestionResult> questionResults;

    private ResultPresenter resultPresenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        resultPresenter = new ResultPresenter(resultView, SCORE, questionResults);
    }

    @Test
    public void testLoadData() throws Exception {
        resultPresenter.loadData();
        verify(resultPresenter.getView()).setResult(SCORE);
        verify(resultPresenter.getView()).setScore(SCORE, questionResults.size());
        verify(resultPresenter.getView()).showResultList(questionResults);
    }

    @Test
    public void testRestart() throws Exception {
        resultPresenter.restart();
        verify(resultPresenter.getView()).returnToStart();
    }
}