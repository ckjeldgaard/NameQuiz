package com.trifork.ckp.namequiz.quiz.question;

import android.content.Context;
import android.widget.ImageView;

import com.hannesdorfmann.mosby.mvp.MvpView;
import com.trifork.ckp.namequiz.model.Question;

public interface QuestionContract {

    interface QuestionView extends MvpView {
        Question getItem(int position);
        ImageView getPersonImage();
    }

    interface UserActionsListener {
        void loadQuestion(int position, Context context);
    }
}
