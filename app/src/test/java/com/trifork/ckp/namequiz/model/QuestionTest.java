package com.trifork.ckp.namequiz.model;

import com.trifork.ckp.namequiz.fakes.FakeAnswerOptionsFactory;
import com.trifork.ckp.namequiz.fakes.FakePersonsFactory;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class QuestionTest {

    private Person person;
    private List<AnswerOption> answerOptions;
    private Question question;

    @Before
    public void setUp() throws Exception {
        person = new FakePersonsFactory().producePerson("person.json");
        answerOptions = new FakeAnswerOptionsFactory().produceAnswerOptions("answer_options.json");
        question = new Question(person, answerOptions);
    }

    @Test
    public void testGetQuestionsSubjectPerson() throws Exception {
        assertEquals(person, question.person());
    }

    @Test
    public void testGetQuestionsAnswerOptions() throws Exception {
        assertEquals(answerOptions, question.answerOptions());
    }
}
