package com.trifork.ckp.namequiz.quiz;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hannesdorfmann.mosby.mvp.viewstate.ViewState;
import com.hannesdorfmann.mosby.mvp.viewstate.layout.MvpViewStateRelativeLayout;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.data.RetainingLceViewState;
import com.trifork.ckp.namequiz.Injection;
import com.trifork.ckp.namequiz.R;
import com.trifork.ckp.namequiz.model.Quiz;
import com.trifork.ckp.namequiz.quiz.question.QuestionAdapter;

import flow.Flow;

public final class QuizLayout extends MvpViewStateRelativeLayout<QuizView, QuizPresenter> implements QuizView {

    private RelativeLayout contentView;
    private TextView errorView;
    private ProgressBar loadingView;
    private ViewPager questionPager;

    public QuizLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @NonNull
    @Override
    public QuizPresenter createPresenter() {
        return new QuizPresenter(Injection.provideRepository(), this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        contentView = (RelativeLayout) findViewById(R.id.quizContentView);
        errorView = (TextView) findViewById(R.id.errorView);
        loadingView = (ProgressBar) findViewById(R.id.loadingView);

        questionPager = (ViewPager) findViewById(R.id.question_pager);
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
        loadingView.setVisibility(VISIBLE);
        errorView.setVisibility(GONE);
        contentView.setVisibility(GONE);
    }

    @Override
    public void showContent() {
        loadingView.setVisibility(GONE);
        errorView.setVisibility(GONE);
        contentView.setVisibility(VISIBLE);
    }

    @Override
    public void showError(Throwable e, boolean pullToRefresh) {
        loadingView.setVisibility(GONE);
        errorView.setVisibility(VISIBLE);
        contentView.setVisibility(GONE);
    }

    @Override
    public void setData(Quiz quiz) {
        // TODO: Refactor
        /*List<Question> questions = new ArrayList<Question>() {{
            add(new Question(persons.get(0)));
            add(new Question(persons.get(1)));
            add(new Question(persons.get(2)));
        }};*/
        questionPager.setAdapter(new QuestionAdapter(quiz.getQuestions(), getContext()));
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        QuizScreen screen = Flow.getKey(this);
        this.presenter.loadPersons(screen.department);
    }
}
