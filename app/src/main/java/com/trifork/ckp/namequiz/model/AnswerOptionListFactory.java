package com.trifork.ckp.namequiz.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class AnswerOptionListFactory extends AbstractAnswerOptionListFactory {

    private final FictionalPersonNames fictionalPersonNames;

    public AnswerOptionListFactory(Person subjectPerson, List<Person> allPersons, FictionalPersonNames fictionalPersonNames) {
        super(subjectPerson, allPersons);
        this.fictionalPersonNames = fictionalPersonNames;
    }

    @Override
    protected List<AnswerOption> makeAnswerOptionList() {
        ArrayList<AnswerOption> answerOptions = new ArrayList<>(MAXIMUM_NUMBER_OF_ANSWERS_PER_QUESTIONS);
        answerOptions.add(new AnswerOption(this.subjectPerson.firstName()));

        List<String> otherOptions = generateWrongAnswerOptions();
        for (int i = 0; i < (MAXIMUM_NUMBER_OF_ANSWERS_PER_QUESTIONS-1); i++) {
            answerOptions.add(new AnswerOption(otherOptions.get(i)));
        }

        Collections.shuffle(answerOptions);
        return answerOptions;
    }

    private List<String> generateWrongAnswerOptions() {
        List<String> options = new ArrayList<>();
        for (int j = 0; j < allPersons.size(); j++) {
            if (!subjectPerson.firstName().equals(allPersons.get(j).firstName()) && subjectPerson.gender() == allPersons.get(j).gender()) {
                options.add(allPersons.get(j).firstName());
            }
        }
        if (options.size() < (MAXIMUM_NUMBER_OF_ANSWERS_PER_QUESTIONS-1)) {
            options.addAll(
                    fictionalPersonNames.list(MAXIMUM_NUMBER_OF_ANSWERS_PER_QUESTIONS - options.size())
            );
        }
        Collections.shuffle(options);
        return options;
    }
}
