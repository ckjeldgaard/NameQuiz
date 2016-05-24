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

    @Test
    public void testCheckAnswersWithAllCorrectAnswers() throws Exception {
        Quiz quiz = new NameQuiz(persons);
        List<Answer> correctAnswers = new ArrayList<>(10);
        for (Question question : quiz.getQuestions()) {
            correctAnswers.add(new Answer(question.person().firstName()));
        }

        // Count correct answers:
        int numCorrectAnswers = 0;
        for (QuestionResult questionResult : quiz.checkAnswers(correctAnswers)) {
            if (questionResult.isAnswerCorrect()) {
                numCorrectAnswers++;
            }
        }

        assertEquals(quiz.getQuestions().size(), numCorrectAnswers);
    }

    @Test
    public void testCheckAnswersWithAllIncorrectButPossibleAnswers() throws Exception {
        Quiz quiz = new NameQuiz(persons);
        List<Answer> incorrectAnswers = new ArrayList<>(10);
        for (Question question : quiz.getQuestions()) {
            String answer = "";
            for (AnswerOption option : question.answerOptions()) {
                if (!option.displayOption().equals(question.person().firstName())) {
                    answer = option.displayOption();
                    break;
                }
            }

            incorrectAnswers.add(new Answer(answer));
        }

        // Count correct answers:
        int numCorrectAnswers = 0;
        for (QuestionResult questionResult : quiz.checkAnswers(incorrectAnswers)) {
            if (questionResult.isAnswerCorrect()) {
                numCorrectAnswers++;
            }
        }

        assertEquals(0, numCorrectAnswers);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCheckAnswersWithEmptyListOfAnswersThrowsException() throws Exception {
        new NameQuiz(persons).checkAnswers(new ArrayList<Answer>(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCheckAnswersWithNullListOfAnswersThrowsException() throws Exception {
        new NameQuiz(persons).checkAnswers(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCheckAnswersWithMismatchingNumberOfAnswersThrowsException() throws Exception {
        Quiz quiz = new NameQuiz(persons);
        List<Answer> answers = new ArrayList<>(10);
        for (Question question : quiz.getQuestions()) {
            answers.add(new Answer(question.person().firstName()));
        }
        answers.remove(answers.size()-1);
        new NameQuiz(persons).checkAnswers(answers);
    }
}