package com.trifork.ckp.namequiz.quiz;

import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;
import com.trifork.ckp.namequiz.model.Answer;
import com.trifork.ckp.namequiz.model.QuestionResult;
import com.trifork.ckp.namequiz.model.Quiz;

import java.util.List;

public interface QuizContract {
    interface QuizView extends MvpLceView<Quiz> {
        void setNextButtonEnabled(boolean enabled);
        void setNextButtonAction();
    }

    interface UserActionsListener {
        void loadPersons(long departmentId);
        void gotoNext();
        List<QuestionResult> questionResults(Quiz quiz);
    }
}
