package com.trifork.ckp.namequiz.quiz;

import com.trifork.ckp.namequiz.data.Repository;
import com.trifork.ckp.namequiz.model.stubs.StubbedPersonsFactory;
import com.trifork.ckp.namequiz.model.NameQuiz;
import com.trifork.ckp.namequiz.model.Quiz;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Matchers.eq;

public class QuizPresenterTest {

    private QuizPresenter quizPresenter;

    @Mock
    private QuizContract.QuizView quizView;

    @Mock
    private Repository repository;

    @Captor
    private ArgumentCaptor<Repository.LoadQuizCallback> loadQuizCallbackCaptor;

    @Before
    public void setupQuizPresenter() throws Exception {
        MockitoAnnotations.initMocks(this);

        this.quizPresenter = new QuizPresenter(repository, quizView);
    }

    @Test
    public void testLoadPersons() throws Exception {
        long departmentId = 1;
        Quiz quiz = new NameQuiz(new StubbedPersonsFactory().producePersons("stubbed_persons.json"));

        this.quizPresenter.loadPersons(departmentId);

        verify(repository).produceQuiz(loadQuizCallbackCaptor.capture(), eq(departmentId));
        loadQuizCallbackCaptor.getValue().onQuizLoaded(quiz);

        // Then progress indicator is hidden and quiz questions are shown in UI
        verify(quizPresenter.getView()).showLoading(false);
        verify(quizPresenter.getView()).setData(quiz);
        verify(quizPresenter.getView()).showContent();
    }

    @Test
    public void testAnswerSelected() throws Exception {
        this.quizPresenter.answerSelected();
        verify(quizPresenter.getView()).setNextButtonEnabled(true);
    }

    @Test
    public void testGotoNext() throws Exception {
        this.quizPresenter.gotoNext();
        verify(quizPresenter.getView()).setNextButtonEnabled(false);
        verify(quizPresenter.getView()).setNextButtonAction();
    }
}