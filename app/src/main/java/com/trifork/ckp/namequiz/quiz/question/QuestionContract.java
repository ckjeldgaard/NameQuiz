package com.trifork.ckp.namequiz.quiz.question;

import android.content.Context;
import android.widget.ImageView;

import com.hannesdorfmann.mosby.mvp.MvpView;
import com.trifork.ckp.namequiz.model.AnswerOption;
import com.trifork.ckp.namequiz.model.Question;

import java.util.List;

public interface QuestionContract {

    interface QuestionView extends MvpView {
        Question getQuestion(int position);
        ImageView getPersonImage();
        void setNames(List<AnswerOption> answerOptions);
        void enableNextButton();
    }

    interface UserActionsListener {
        void loadQuestion(int position, Context context);
        void selectPerson(int questionIndex, int answerOptionIndex);
    }
}
