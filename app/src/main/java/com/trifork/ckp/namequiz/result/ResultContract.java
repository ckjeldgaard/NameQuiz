package com.trifork.ckp.namequiz.result;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;

public interface ResultContract {

    interface ResultView extends MvpView {
    }

    interface UserActionsListener extends MvpPresenter<ResultView> {

    }
}
