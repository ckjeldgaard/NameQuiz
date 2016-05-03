package com.trifork.ckp.namequiz.quiz;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

public class QuizPresenter extends MvpBasePresenter<QuizView> {

    public QuizPresenter(QuizView view) {
        this.attachView(view);
    }
}
