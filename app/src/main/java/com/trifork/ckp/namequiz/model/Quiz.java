package com.trifork.ckp.namequiz.model;

import android.support.annotation.NonNull;

import java.util.List;

public interface Quiz {
    List<Question> getQuestions();
    int checkAnswers(@NonNull List<Answer> answers);
}