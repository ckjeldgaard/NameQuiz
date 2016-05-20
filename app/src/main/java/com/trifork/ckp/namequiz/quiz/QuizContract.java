package com.trifork.ckp.namequiz.quiz;

import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;
import com.trifork.ckp.namequiz.model.Quiz;

public interface QuizContract {
    interface QuizView extends MvpLceView<Quiz> {
        void setNextButtonEnabled(boolean enabled);
        void setNextButtonAction();
    }

    interface UserActionsListener {
        void loadPersons(long departmentId);
        void gotoNext();
    }
}
