package com.trifork.ckp.namequiz.quiz;

import android.support.annotation.NonNull;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.trifork.ckp.namequiz.data.Repository;
import com.trifork.ckp.namequiz.model.Answer;
import com.trifork.ckp.namequiz.model.Quiz;

import java.util.ArrayList;
import java.util.List;

public final class QuizPresenter extends MvpBasePresenter<QuizContract.QuizView> implements QuizContract.UserActionsListener, PagerActions {

    private final Repository repository;
    private List<Answer> answers;
    private Answer answerSelected;

    public QuizPresenter(@NonNull Repository repository, QuizContract.QuizView view) {
        this.answers = new ArrayList<>(10);
        this.repository = repository;
        this.attachView(view);
    }

    @Override
    public void loadPersons(long departmentId) {
        getView().showLoading(false);

        this.repository.produceQuiz(new Repository.LoadQuizCallback() {
            @Override
            public void onQuizLoaded(Quiz nameQuiz) {
                if (isViewAttached()) {
                    getView().setData(nameQuiz);
                    getView().showContent();
                }
            }

            @Override
            public void onFailure(Exception ex) {
                if (isViewAttached()) {
                    getView().showError(ex, false);
                }
            }
        }, departmentId);
    }

    @Override
    public void buttonAction() {
        getView().setNextButtonEnabled(false);
        answers.add(answerSelected);
        getView().setNextButtonAction();
    }

    @Override
    public List<Answer> answers() {
        return this.answers;
    }

    @Override
    public void answerSelected(Answer answer) {
        this.answerSelected = answer;
        getView().setNextButtonEnabled(true);
    }
}
