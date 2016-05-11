package com.trifork.ckp.namequiz.quiz.question;

import android.content.Context;
import android.util.Log;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.squareup.picasso.Picasso;
import com.trifork.ckp.namequiz.R;
import com.trifork.ckp.namequiz.model.Question;

public final class QuestionPresenter extends MvpBasePresenter<QuestionContract.QuestionView> implements QuestionContract.UserActionsListener {

    private static final String TAG = QuestionAdapter.class.getSimpleName();

    public QuestionPresenter(QuestionContract.QuestionView view) {
        this.attachView(view);
    }

    @Override
    public void loadQuestion(Context context) {
        Question question = getView().getQuestion();

        int width = Math.round(context.getResources().getDimension(R.dimen.person_image_width));
        int height = Math.round(context.getResources().getDimension(R.dimen.person_image_height));

        Picasso.with(context)
                .load(question.person().imageUrl())
                .placeholder(R.drawable.progress_animation)
                .resize(width, height)
                .centerCrop()
                .into(getView().getPersonImage());

        getView().setNames(question.answerOptions());
    }

    @Override
    public void selectPerson(int answerOptionIndex) {
        Log.d(TAG, "Selecting person at index " + answerOptionIndex + " = " + getView().getQuestion().answerOptions().get(answerOptionIndex).displayOption());
        getView().enableNextButton();
        getView().setSelected(answerOptionIndex);
    }

    @Override
    public void nextQuestion() {
        getView().swipeToNextPage();
    }

}
