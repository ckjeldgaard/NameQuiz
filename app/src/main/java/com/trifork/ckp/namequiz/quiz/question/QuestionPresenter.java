package com.trifork.ckp.namequiz.quiz.question;

import android.util.Log;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.trifork.ckp.namequiz.model.Question;
import com.trifork.ckp.namequiz.util.PersonImage;

public final class QuestionPresenter extends MvpBasePresenter<QuestionContract.QuestionView> implements QuestionContract.UserActionsListener {

    private static final String TAG = QuestionAdapter.class.getSimpleName();

    private final PersonImage personImage;

    public QuestionPresenter(QuestionContract.QuestionView view, PersonImage personImage) {
        this.attachView(view);
        this.personImage = personImage;
    }

    @Override
    public void loadQuestion(Question question) {
        personImage.loadImage(
                question.person().imageUrl(),
                getView().getPersonImageView()
        );
        getView().setNames(question.answerOptions());
    }

    @Override
    public void selectPerson(int answerOptionIndex) {
        //Log.d(TAG, "Selecting person at index " + answerOptionIndex + " = " + getView().getQuestion().answerOptions().get(answerOptionIndex).displayOption());
        getView().setSelected(answerOptionIndex);
    }

}
