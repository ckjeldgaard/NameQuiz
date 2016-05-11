package com.trifork.ckp.namequiz.model;

import java.util.List;

public abstract class AbstractAnswerOptionListFactory {

    protected static final int MAXIMUM_NUMBER_OF_ANSWERS_PER_QUESTIONS = 4;

    protected final Person subjectPerson;
    protected final List<Person> allPersons;

    public AbstractAnswerOptionListFactory(Person subjectPerson, List<Person> allPersons) {
        this.subjectPerson = subjectPerson;
        this.allPersons = allPersons;
    }

    public List<AnswerOption> list() {
        return this.makeAnswerOptionList();
    }

    abstract protected List<AnswerOption> makeAnswerOptionList();
}
