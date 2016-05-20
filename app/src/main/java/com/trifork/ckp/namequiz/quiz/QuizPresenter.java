package com.trifork.ckp.namequiz.quiz;

import android.support.annotation.NonNull;
import android.util.Log;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.trifork.ckp.namequiz.data.Repository;
import com.trifork.ckp.namequiz.model.Department;
import com.trifork.ckp.namequiz.model.Question;
import com.trifork.ckp.namequiz.model.Quiz;
import com.trifork.ckp.namequiz.quiz.question.QuestionLayout;

import java.util.ArrayList;
import java.util.List;

public final class QuizPresenter extends MvpBasePresenter<QuizContract.QuizView> implements QuizContract.UserActionsListener, PagerActions {

    private final Repository repository;
    private Quiz quiz;

    public QuizPresenter(@NonNull Repository repository, QuizContract.QuizView view) {
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
                    Log.d("QuizPresenter", "onPersonsLoaded() called with: " + "quiz = [" + quiz + "]");

                    quiz = nameQuiz;
                    getView().setData(nameQuiz);
                    getView().showContent();
                }
            }

            @Override
            public void onFailure(Exception ex) {
                if (isViewAttached()) {
                    Log.e("QuizPresenter", "onFailure() called with: " + "errorMessage = [" + ex.getMessage() + "]");
                    getView().showError(ex, false);
                }
            }
        }, departmentId);
    }

    @Override
    public void gotoNext() {
        getView().setNextButtonEnabled(false);
        getView().setNextButtonAction();
    }

    @Override
    public void answerSelected() {
        getView().setNextButtonEnabled(true);
    }
}
