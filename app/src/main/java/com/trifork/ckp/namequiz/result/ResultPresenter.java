package com.trifork.ckp.namequiz.result;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.trifork.ckp.namequiz.model.QuestionResult;

import java.util.List;

public final class ResultPresenter extends MvpBasePresenter<ResultContract.ResultView> implements ResultContract.UserActionsListener {

    private static final String TAG = ResultPresenter.class.getSimpleName();
    private final int score;
    private final List<QuestionResult> questionResults;

    public ResultPresenter(ResultContract.ResultView view, int score, List<QuestionResult> questionResults) {
        this.attachView(view);
        this.score = score;
        this.questionResults = questionResults;
    }

    @Override
    public void loadData() {
        //ResultScreen screen = Flow.getKey((View)getView());

        getView().setResult(score);
        getView().setScore(score, questionResults.size());
        getView().showResultList(questionResults);
    }

    @Override
    public void restart() {
        getView().returnToStart();
    }
}
