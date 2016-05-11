package com.trifork.ckp.namequiz.model;

import java.util.List;

public final class Question {

    private final Person person;
    private final List<AnswerOption> answerOptions;

    public Question(Person person, List<AnswerOption> answerOptions) {
        this.person = person;
        this.answerOptions = answerOptions;
    }

    public Person person() {
        return person;
    }

    public List<AnswerOption> answerOptions() {
        return answerOptions;
    }
}
