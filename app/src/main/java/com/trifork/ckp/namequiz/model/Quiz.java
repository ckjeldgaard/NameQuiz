package com.trifork.ckp.namequiz.model;

import android.support.annotation.NonNull;

import java.util.List;

public final class Quiz {

    private final List<Person> persons;

    public Quiz(@NonNull List<Person> persons) {
        if (persons == null) {
            throw new IllegalArgumentException("List<Person> argument is null!");
        }
        if (persons.size() == 0) {
            throw new IllegalArgumentException("List of persons cannot be empty");
        }
        this.persons = persons;
    }
}
