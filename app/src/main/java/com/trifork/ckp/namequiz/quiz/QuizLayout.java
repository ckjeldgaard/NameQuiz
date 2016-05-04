package com.trifork.ckp.namequiz.quiz;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;

import com.hannesdorfmann.mosby.mvp.viewstate.ViewState;
import com.hannesdorfmann.mosby.mvp.viewstate.layout.MvpViewStateRelativeLayout;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.data.RetainingLceViewState;
import com.trifork.ckp.namequiz.R;
import com.trifork.ckp.namequiz.model.Question;
import com.trifork.ckp.namequiz.model.Quiz;

import java.util.ArrayList;
import java.util.List;

public final class QuizLayout extends MvpViewStateRelativeLayout<QuizView, QuizPresenter> implements QuizView {

    private ViewPager questionPager;

    public QuizLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @NonNull
    @Override
    public QuizPresenter createPresenter() {
        return new QuizPresenter(this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        List<Question> questions = new ArrayList<Question>() {{
            add(new Question());
            add(new Question());
            add(new Question());
        }};
        questionPager = (ViewPager) findViewById(R.id.question_pager);
        questionPager.setAdapter(new QuestionAdapter(questions, LayoutInflater.from(getContext())));
    }

    @NonNull
    @Override
    public ViewState<QuizView> createViewState() {
        return new RetainingLceViewState<Quiz, QuizView>();
    }

    @Override
    public void onNewViewStateInstance() {
        loadData(false);
    }

    @Override
    public void showLoading(boolean pullToRefresh) {
        Log.d("QuizLayout", "showLoading() called with: " + "pullToRefresh = [" + pullToRefresh + "]");
    }

    @Override
    public void showContent() {
        Log.d("QuizLayout", "showContent() called with: " + "");
    }

    @Override
    public void showError(Throwable e, boolean pullToRefresh) {
        Log.d("QuizLayout", "showError() called with: " + "e = [" + e + "], pullToRefresh = [" + pullToRefresh + "]");
    }

    @Override
    public void setData(Quiz data) {
        Log.d("QuizLayout", "setData() called with: " + "data = [" + data + "]");
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        Log.d("QuizLayout", "loadData() called with: " + "pullToRefresh = [" + pullToRefresh + "]");
    }
}
