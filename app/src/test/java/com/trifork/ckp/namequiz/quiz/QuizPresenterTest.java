package com.trifork.ckp.namequiz.quiz;

import com.trifork.ckp.namequiz.data.Repository;
import com.trifork.ckp.namequiz.model.Answer;
import com.trifork.ckp.namequiz.model.Question;
import com.trifork.ckp.namequiz.model.QuestionResult;
import com.trifork.ckp.namequiz.model.stubs.StubbedPersonsFactory;
import com.trifork.ckp.namequiz.model.NameQuiz;
import com.trifork.ckp.namequiz.model.Quiz;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Matchers.eq;
import static org.junit.Assert.*;

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
    public void testLoadPersonsFails() throws Exception {
        long departmentId = 1;
        Exception ex = new Exception("Some exception");

        this.quizPresenter.loadPersons(departmentId);

        verify(repository).produceQuiz(loadQuizCallbackCaptor.capture(), eq(departmentId));
        loadQuizCallbackCaptor.getValue().onFailure(ex);

        verify(quizPresenter.getView()).showLoading(false);
        verify(quizPresenter.getView()).showError(ex, false);
    }

    @Test
    public void testAnswerSelected() throws Exception {
        this.quizPresenter.answerSelected(new Answer("Jeff"));
        verify(quizPresenter.getView()).setNextButtonEnabled(true);
    }

    @Test
    public void testGotoNext() throws Exception {
        this.quizPresenter.buttonAction();
        verify(quizPresenter.getView()).setNextButtonEnabled(false);
        verify(quizPresenter.getView()).setNextButtonAction();
    }

    @Test
    public void testAnswers() throws Exception {

        Answer answer = new Answer("Joe");

        this.quizPresenter.answers();
        this.quizPresenter.answerSelected(answer);
        this.quizPresenter.buttonAction();
        assertEquals(answer, this.quizPresenter.answers().get(0));
    }
}