package com.trifork.ckp.namequiz.model;

public final class Question {

    private final Person person;

    public Question(Person person) {
        this.person = person;
    }

    public Person person() {
        return person;
    }
}
