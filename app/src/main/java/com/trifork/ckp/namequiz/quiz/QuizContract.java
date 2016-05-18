package com.trifork.ckp.namequiz.quiz;

import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;
import com.trifork.ckp.namequiz.model.Quiz;

public interface QuizContract {
    interface QuizView extends MvpLceView<Quiz> {
    }

    interface UserActionsListener {
        void loadPersons(long departmentId);
    }
}
