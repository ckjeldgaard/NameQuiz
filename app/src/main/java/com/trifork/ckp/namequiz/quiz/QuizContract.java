package com.trifork.ckp.namequiz.quiz;

import android.content.Context;

import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;
import com.trifork.ckp.namequiz.quiz.question.QuestionLayout;

import java.util.List;

public interface QuizContract {
    interface QuizView extends MvpLceView<List<QuestionLayout>> {
        void setNextButtonEnabled(boolean enabled);
        void setNextButtonAction();
        Context context();
    }

    interface UserActionsListener {
        void loadPersons(long departmentId);
        void gotoNext();
    }
}
