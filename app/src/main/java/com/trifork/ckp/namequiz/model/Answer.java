package com.trifork.ckp.namequiz.model;

public final class Answer {

    private final String answer;

    public Answer(String answer) {
        this.answer = answer;
    }

    public String get() {
        return answer;
    }

    @Override
    public String toString() {
        return answer;
    }
}
