package com.trifork.ckp.namequiz.quiz.question;

import android.widget.ImageView;

import com.hannesdorfmann.mosby.mvp.MvpView;
import com.trifork.ckp.namequiz.model.AnswerOption;
import com.trifork.ckp.namequiz.model.Question;

import java.util.List;

public interface QuestionContract {

    interface QuestionView extends MvpView {
        Question getQuestion();
        ImageView getPersonImageView();
        void setNames(List<AnswerOption> answerOptions);
        void setSelected(int index);
    }

    interface UserActionsListener {
        void loadQuestion(Question question);
        void selectPerson(int answerOptionIndex);
    }
}
