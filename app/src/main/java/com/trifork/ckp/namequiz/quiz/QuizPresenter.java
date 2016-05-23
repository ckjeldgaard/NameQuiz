package com.trifork.ckp.namequiz.quiz;

import android.support.annotation.NonNull;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.trifork.ckp.namequiz.data.Repository;
import com.trifork.ckp.namequiz.model.Answer;
import com.trifork.ckp.namequiz.model.QuestionResult;
import com.trifork.ckp.namequiz.model.Quiz;

import java.util.ArrayList;
import java.util.List;

public final class QuizPresenter extends MvpBasePresenter<QuizContract.QuizView> implements QuizContract.UserActionsListener, PagerActions {

    private final Repository repository;
    private List<Answer> answers;

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
    public void gotoNext() {
        getView().setNextButtonEnabled(false);
        getView().setNextButtonAction();
    }

    @Override
    public List<QuestionResult> questionResults(Quiz quiz) {
        List<QuestionResult> questionResults = new ArrayList<>(this.answers.size());
        for (int i = 0; i < this.answers.size(); i++) {
            if (quiz.getQuestions().size() > i) {
                questionResults.add(
                        new QuestionResult(
                                this.answers.get(i).get(),
                                quiz.getQuestions().get(i).person().firstName(),
                                quiz.getQuestions().get(i).person().imageUrl()
                        )
                );
            }
        }
        return questionResults;
    }

    @Override
    public void answerSelected(Answer answer) {
        answers.add(answer);
        getView().setNextButtonEnabled(true);
    }
}
