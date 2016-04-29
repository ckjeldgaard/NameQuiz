package com.trifork.ckp.namequiz.question;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;

import com.hannesdorfmann.mosby.mvp.viewstate.ViewState;
import com.hannesdorfmann.mosby.mvp.viewstate.layout.MvpViewStateRelativeLayout;

public class QuestionLayout extends MvpViewStateRelativeLayout<QuestionView, QuestionPresenter> implements QuestionView {

    public QuestionLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public QuestionPresenter createPresenter() {
        return null;
    }

    @NonNull
    @Override
    public ViewState<QuestionView> createViewState() {
        return null;
    }

    @Override
    public void onNewViewStateInstance() {

    }
}
