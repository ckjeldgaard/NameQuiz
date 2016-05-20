package com.trifork.ckp.namequiz.result;


import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.trifork.ckp.namequiz.data.Repository;
import com.trifork.ckp.namequiz.util.PersonImage;

public final class ResultPresenter extends MvpBasePresenter<ResultContract.ResultView> implements ResultContract.UserActionsListener {

    private static final String TAG = ResultPresenter.class.getSimpleName();

    private final Repository repository;
    
    public ResultPresenter(ResultContract.ResultView view, Repository repository) {
        this.attachView(view);
        this.repository = repository;
    }
}
