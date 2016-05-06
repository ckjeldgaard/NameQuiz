package com.trifork.ckp.namequiz.model;

import com.trifork.ckp.namequiz.repository.StubbedData;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class QuizTest {

    private List<Person> persons;

    @Before
    public void setUp() throws Exception {
        persons = new StubbedData().producePersons();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructQuizWithEmptyListThrowsException() throws Exception {
        new Quiz(new ArrayList<Person>());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructQuizWithNullListThrowsException() throws Exception {
        new Quiz(null);
    }

    @Test
    public void testQuiz() throws Exception {
        new Quiz(persons);
        assertEquals("14", persons.size());
    }
}