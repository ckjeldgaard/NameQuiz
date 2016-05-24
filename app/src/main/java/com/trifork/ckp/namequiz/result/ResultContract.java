package com.trifork.ckp.namequiz.result;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;
import com.trifork.ckp.namequiz.model.QuestionResult;

import java.util.List;

public interface ResultContract {

    interface ResultView extends MvpView {
        void setResult(int score);
        void setScore(int score, int numQuestions);
        void showResultList(List<QuestionResult> questionResults);
        void returnToStart();
    }

    interface UserActionsListener extends MvpPresenter<ResultView> {
        void loadData();
        void restart();
    }
}
