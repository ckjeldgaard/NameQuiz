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
import com.trifork.ckp.namequiz.model.Question;
import com.trifork.ckp.namequiz.model.Quiz;
import com.trifork.ckp.namequiz.quiz.question.QuestionAdapter;
import com.trifork.ckp.namequiz.quiz.question.QuestionLayout;

import java.util.ArrayList;
import java.util.List;

import flow.Flow;

public final class QuizLayout extends MvpViewStateRelativeLayout<QuizView, QuizPresenter> implements QuizView, PagerActions {

    private static final String TAG = QuizLayout.class.getSimpleName();

    private Quiz quiz;

    private RelativeLayout contentView;
    private TextView errorView, questionNumber;
    private ProgressBar loadingView;
    private ViewPager questionPager;
    private QuestionAdapter questionAdapter;

    public QuizLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setRetainInstance(true);
    }

    @NonNull
    @Override
    public QuizPresenter createPresenter() {
        return new QuizPresenter(
                new Injection().provideRepository(),
                this
        );
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        contentView = (RelativeLayout) findViewById(R.id.quizContentView);
        errorView = (TextView) findViewById(R.id.errorView);
        loadingView = (ProgressBar) findViewById(R.id.loadingView);

        questionNumber = (TextView) findViewById(R.id.text_question_number);
        questionPager = (ViewPager) findViewById(R.id.question_pager);
    }

    @NonNull
    @Override
    public ViewState<QuizView> createViewState() {
        return new RetainingLceViewState<Quiz, QuizView>();
    }

    @Override
    public void onNewViewStateInstance() {
        loadData(true);
    }

    @Override
    public void showLoading(boolean pullToRefresh) {
        loadingView.setVisibility(VISIBLE);
        errorView.setVisibility(GONE);
        contentView.setVisibility(GONE);
        castedViewState().setStateShowLoading(pullToRefresh);
    }

    @Override
    public void showContent() {
        loadingView.setVisibility(GONE);
        errorView.setVisibility(GONE);
        contentView.setVisibility(VISIBLE);
        castedViewState().setStateShowContent(this.quiz);
    }

    @Override
    public void showError(Throwable e, boolean pullToRefresh) {
        loadingView.setVisibility(GONE);
        errorView.setVisibility(VISIBLE);
        contentView.setVisibility(GONE);
        castedViewState().setStateShowError(e, pullToRefresh);
    }

    private RetainingLceViewState<Quiz, QuizView> castedViewState() {
        return (RetainingLceViewState<Quiz, QuizView>)viewState;
    }

    @Override
    public void setData(Quiz nameQuiz) {
        this.quiz = nameQuiz;

        List<QuestionLayout> questionLayouts = new ArrayList<>();
        for (Question question : quiz.getQuestions()) {
            questionLayouts.add(
                    new QuestionLayout(getContext(), question, this)
            );
        }

        questionAdapter = new QuestionAdapter(questionLayouts);
        questionPager.setAdapter(questionAdapter);
        questionPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setQuestionNumberText(position + 1);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        setQuestionNumberText(questionPager.getCurrentItem() + 1);
    }

    private void setQuestionNumberText(int no) {
        questionNumber.setText(
                String.format(
                        getResources().getString(R.string.text_question_number),
                        no,
                        questionAdapter.getCount()
                )
        );
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        QuizScreen screen = Flow.getKey(this);
        this.presenter.loadPersons(screen.department);
    }

    @Override
    public void moveNext() {
        this.questionPager.setCurrentItem(questionPager.getCurrentItem() + 1, true);
    }
}
