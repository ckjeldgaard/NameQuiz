package com.trifork.ckp.namequiz.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class QuestionResultTest {

    @Test
    public void testAnswerGiven() throws Exception {
        QuestionResult qr = new QuestionResult("Jeff", "Joe", "imageUrl");
        assertEquals("Jeff", qr.answerGiven());
    }

    @Test
    public void testCorrectResult() throws Exception {
        QuestionResult qr = new QuestionResult("Jeff", "Joe", "imageUrl");
        assertEquals("Joe", qr.correctResult());
    }

    @Test
    public void testImageUrl() throws Exception {
        QuestionResult qr = new QuestionResult("Jeff", "Joe", "imageUrl");
        assertEquals("imageUrl", qr.imageUrl());
    }

    @Test
    public void testIsAnswerCorrect_WrongAnswerGiven() throws Exception {
        QuestionResult qr = new QuestionResult("Jeff", "Joe", "imageUrl");
        assertEquals(false, qr.isAnswerCorrect());
    }

    @Test
    public void testIsAnswerCorrect_CorrectAnswerGiven() throws Exception {
        QuestionResult qr = new QuestionResult("Jeff", "Jeff", "imageUrl");
        assertEquals(true, qr.isAnswerCorrect());
    }
}