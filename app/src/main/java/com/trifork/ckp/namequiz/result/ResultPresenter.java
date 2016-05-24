package com.trifork.ckp.namequiz.result;

import android.view.View;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import flow.Flow;

public final class ResultPresenter extends MvpBasePresenter<ResultContract.ResultView> implements ResultContract.UserActionsListener {

    private static final String TAG = ResultPresenter.class.getSimpleName();
    
    public ResultPresenter(ResultContract.ResultView view) {
        this.attachView(view);
    }

    @Override
    public void loadData() {
        ResultScreen screen = Flow.getKey((View)getView());

        getView().setResult(screen.score);
        getView().setScore(screen.score, screen.questionResults.size());
        getView().showResultList(screen.questionResults);
    }

    @Override
    public void restart() {
        getView().returnToStart();
    }
}
