package com.trifork.ckp.namequiz.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class NameQuiz implements Quiz {

    private static final int MAXIMUM_NUMBER_OF_QUESTIONS = 10;

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
        ArrayList<Question> questions = new ArrayList<>(MAXIMUM_NUMBER_OF_QUESTIONS);

        ArrayList<Person> personsList = (ArrayList<Person>) persons;
        Collections.copy(personsList, persons);
        Collections.shuffle(personsList);

        for (int i = 0; i < MAXIMUM_NUMBER_OF_QUESTIONS; i++) {
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
}
