package com.trifork.ckp.namequiz.model;

import android.support.annotation.NonNull;

import com.trifork.ckp.namequiz.util.MaximumQuizQuestions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class NameQuiz implements Quiz {

    private final List<Person> persons;
    private final List<Question> questions;

    public NameQuiz(List<Person> persons) {
        if (persons == null) {
            throw new IllegalArgumentException("List<Person> argument is null!");
        }
        if (persons.size() == 0) {
            throw new IllegalArgumentException("List of persons cannot be empty");
        }
        this.persons = persons;
        this.questions = generateQuestions();
    }

    private List<Question> generateQuestions() {
        int maximumNumberOfQuestions = new MaximumQuizQuestions().number();
        ArrayList<Question> questions = new ArrayList<>(maximumNumberOfQuestions);

        ArrayList<Person> personsList = (ArrayList<Person>) persons;
        Collections.copy(personsList, persons);
        Collections.shuffle(personsList);

        for (int i = 0; i < maximumNumberOfQuestions; i++) {
            if (i < personsList.size()) {
                questions.add(
                        new Question(
                                personsList.get(i),
                                new AnswerOptionListFactory(
                                        personsList.get(i),
                                        this.persons,
                                        new FictionalPersonNames(personsList.get(i).gender())
                                ).list()
                        )
                );
            }
        }
        return questions;
    }

    @Override
    public List<Question> getQuestions() {
        return this.questions;
    }

    @Override
    public List<QuestionResult> checkAnswers(@NonNull List<Answer> answers) throws IllegalArgumentException {
        validateAnswers(answers);
        List<QuestionResult> questionResults = new ArrayList<>(answers.size());
        for (int i = 0; i < this.questions.size(); i++) {
            questionResults.add(
                    new QuestionResult(
                            answers.get(i).get(),
                            this.getQuestions().get(i).person().firstName(),
                            this.getQuestions().get(i).person().imageUrl()
                    )
            );
        }
        return questionResults;
    }

    @Override
    public int numberOfCorrectAnswers(@NonNull List<Answer> answers) {
        validateAnswers(answers);
        int numCorrectAnswers = 0;
        for (QuestionResult questionResult : this.checkAnswers(answers)) {
            if (questionResult.isAnswerCorrect()) {
                numCorrectAnswers++;
            }
        }
        return numCorrectAnswers;
    }

    private void validateAnswers(List<Answer> answers) throws IllegalArgumentException {
        if (answers == null) {
            throw new IllegalArgumentException("List of answers cannot be null.");
        }
        if (answers.size() == 0) {
            throw new IllegalArgumentException("List of answers cannot be empty.");
        }
        if (answers.size() != this.questions.size()) {
            throw new IllegalArgumentException(
                    String.format(
                            "Number of answers (%s) must match number of questions (%s).",
                            answers.size(),
                            this.questions.size()
                    )
            );
        }
    }
}
