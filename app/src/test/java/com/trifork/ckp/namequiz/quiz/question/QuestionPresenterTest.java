package com.trifork.ckp.namequiz.quiz.question;

import com.trifork.ckp.namequiz.model.Question;
import com.trifork.ckp.namequiz.model.stubs.StubbedAnswerOptionsFactory;
import com.trifork.ckp.namequiz.model.stubs.StubbedPersonsFactory;
import com.trifork.ckp.namequiz.util.PersonImage;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class QuestionPresenterTest {


    private QuestionPresenter questionPresenter;

    @Mock
    QuestionContract.QuestionView questionView;

    @Mock
    PersonImage personImage;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.questionPresenter = new QuestionPresenter(questionView, personImage);
    }

    @Test
    public void testLoadQuestion() throws Exception {
        Question question = new Question(
                new StubbedPersonsFactory().producePerson(),
                new StubbedAnswerOptionsFactory().produceAnswerOptions()
        );
        questionPresenter.loadQuestion(question);

        verify(personImage).loadImage(question.person().imageUrl(), questionView.getPersonImageView());
        verify(questionView).setNames(question.answerOptions());
    }

    @Test
    public void testSelectPerson() throws Exception {
        questionPresenter.selectPerson(0);
        verify(questionView).setSelected(0);
    }
}