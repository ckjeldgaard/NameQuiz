package com.trifork.ckp.namequiz.model;

import com.trifork.ckp.namequiz.model.stubs.StubbedAnswerOptionsFactory;
import com.trifork.ckp.namequiz.model.stubs.StubbedPersonsFactory;

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
        person = new StubbedPersonsFactory().producePerson("stubbed_person.json");
        answerOptions = new StubbedAnswerOptionsFactory().produceAnswerOptions("stubbed_answer_options.json");
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
