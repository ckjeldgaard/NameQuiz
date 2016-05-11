package com.trifork.ckp.namequiz.model;

import android.support.annotation.NonNull;

public final class AnswerOption {

    private final String option;

    public AnswerOption(@NonNull String option) {
        this.option = option;
    }

    public String displayOption() {
        return option;
    }

    @Override
    public String toString() {
        return option;
    }
}
