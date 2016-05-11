package com.trifork.ckp.namequiz.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class NameQuiz implements Quiz {

    private static final int MAXIMUM_NUMBER_OF_QUESTIONS = 10;

    private final List<Person> persons;

    public NameQuiz(List<Person> persons) {
        if (persons == null) {
            throw new IllegalArgumentException("List<Person> argument is null!");
        }
        if (persons.size() == 0) {
            throw new IllegalArgumentException("List of persons cannot be empty");
        }
        this.persons = persons;
    }

    @Override
    public List<Question> getQuestions() {
        ArrayList<Question> questions = new ArrayList<>(MAXIMUM_NUMBER_OF_QUESTIONS);

        ArrayList<Person> personsShuffled = (ArrayList<Person>) persons;
        Collections.copy(personsShuffled, persons);
        Collections.shuffle(personsShuffled);

        for (int i = 0; i < MAXIMUM_NUMBER_OF_QUESTIONS; i++) {
            questions.add(
                    new Question(
                            personsShuffled.get(i),
                            new AnswerOptionListFactory(
                                    personsShuffled.get(i),
                                    this.persons,
                                    new FakeNames(personsShuffled.get(i).gender())
                            ).list()
                    )
            );
        }
        return questions;
    }

    /*private List<AnswerOption> produceAnswerOptions(Person person) {
        ArrayList<AnswerOption> answerOptions = new ArrayList<>(MAXIMUM_NUMBER_OF_ANSWERS_PER_QUESTIONS);
        answerOptions.add(new AnswerOption(person.firstName()));
        for (int i = 1; i < MAXIMUM_NUMBER_OF_ANSWERS_PER_QUESTIONS; i++) {
            answerOptions.add(new AnswerOption("Jeff"));
        }
        Collections.shuffle(answerOptions);
        return answerOptions;
    }*/
}
