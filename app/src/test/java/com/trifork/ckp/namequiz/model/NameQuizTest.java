package com.trifork.ckp.namequiz.model;

import com.trifork.ckp.namequiz.model.stubs.StubbedPersonsFactory;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class NameQuizTest {

    private List<Person> persons;

    @Before
    public void setUp() throws Exception {
        persons = new StubbedPersonsFactory().producePersons("stubbed_persons.json");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructQuizWithEmptyListThrowsException() throws Exception {
        new NameQuiz(new ArrayList<Person>());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructQuizWithNullListThrowsException() throws Exception {
        new NameQuiz(null);
    }

    @Test
    public void testGetQuestions_producesQuizWith10Questions() throws Exception {
        List<Question> questions = new NameQuiz(persons).getQuestions();
        assertEquals(10, questions.size());
    }

    @Test
    public void testGetQuestions_producesQuizWithSmallDepartment() throws Exception {
        List<Person> smallDepartment = new ArrayList<>(1);
        smallDepartment.add(persons.get(0));
        List<Question> questions = new NameQuiz(smallDepartment).getQuestions();
        assertEquals(smallDepartment.size(), questions.size());
    }
}