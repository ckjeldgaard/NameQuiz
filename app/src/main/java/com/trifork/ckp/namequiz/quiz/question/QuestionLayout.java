package com.trifork.ckp.namequiz.quiz.question;

import android.content.Context;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.trifork.ckp.namequiz.MainActivity;
import com.trifork.ckp.namequiz.NameQuizApplication;
import com.trifork.ckp.namequiz.R;
import com.trifork.ckp.namequiz.model.AnswerOption;
import com.trifork.ckp.namequiz.model.Question;
import com.trifork.ckp.namequiz.quiz.PagerActions;

import java.util.List;

public class QuestionLayout extends RelativeLayout implements QuestionContract.QuestionView {

    private static final String TAG = QuestionLayout.class.getSimpleName();

    private final Question question;
    private final QuestionContract.UserActionsListener presenter;
    private final PagerActions pagerActions;

    public QuestionLayout(Context context, Question question, PagerActions pagerActions) {
        super(context);
        this.question = question;
        this.pagerActions = pagerActions;
        this.presenter = new QuestionPresenter(this, ((NameQuizApplication)getContext().getApplicationContext()).getInjection().providePersonImage(context));
        this.inflateLayout(context);
    }

    private ImageView personImage;
    private Button buttonOption1, buttonOption2, buttonOption3, buttonOption4, buttonNext;

    protected View inflateLayout(Context context) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rootView = layoutInflater.inflate(R.layout.question_view, this);

        this.personImage = (ImageView) rootView.findViewById(R.id.image_person);
        this.buttonOption1 = (Button) rootView.findViewById(R.id.option_1);
        this.buttonOption2 = (Button) rootView.findViewById(R.id.option_2);
        this.buttonOption3 = (Button) rootView.findViewById(R.id.option_3);
        this.buttonOption4 = (Button) rootView.findViewById(R.id.option_4);
        this.buttonNext = (Button) rootView.findViewById(R.id.question_next_button);
        buttonNext.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.nextQuestion();
            }
        });

        buttonOption1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.selectPerson(0);
            }
        });
        buttonOption2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.selectPerson(1);
            }
        });
        buttonOption3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.selectPerson(2);
            }
        });
        buttonOption4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.selectPerson(3);
            }
        });

        this.presenter.loadQuestion(context);

        return rootView;
    }

    @Override
    public Question getQuestion() {
        return this.question;
    }

    @Override
    public ImageView getPersonImageView() {
        return this.personImage;
    }

    @Override
    public void setNames(List<AnswerOption> answerOptions) {
        buttonOption1.setText(answerOptions.get(0).displayOption());
        buttonOption2.setText(answerOptions.get(1).displayOption());
        buttonOption3.setText(answerOptions.get(2).displayOption());
        buttonOption4.setText(answerOptions.get(3).displayOption());
    }

    @Override
    public void setSelected(int index) {
        Drawable defaultState = ContextCompat.getDrawable(getContext(), R.drawable.question_button_shape);
        Drawable selected = ContextCompat.getDrawable(getContext(), R.drawable.question_button_shape_selected);
        switch (index) {
            case 0:
                buttonOption1.setBackground(selected);
                buttonOption2.setBackground(defaultState);
                buttonOption3.setBackground(defaultState);
                buttonOption4.setBackground(defaultState);
                break;
            case 1:
                buttonOption1.setBackground(defaultState);
                buttonOption2.setBackground(selected);
                buttonOption3.setBackground(defaultState);
                buttonOption4.setBackground(defaultState);
                break;
            case 2:
                buttonOption1.setBackground(defaultState);
                buttonOption2.setBackground(defaultState);
                buttonOption3.setBackground(selected);
                buttonOption4.setBackground(defaultState);
                break;
            case 3:
                buttonOption1.setBackground(defaultState);
                buttonOption2.setBackground(defaultState);
                buttonOption3.setBackground(defaultState);
                buttonOption4.setBackground(selected);
                break;
            default:
                buttonOption1.setBackground(defaultState);
                buttonOption2.setBackground(defaultState);
                buttonOption3.setBackground(defaultState);
                buttonOption4.setBackground(defaultState);
                break;
        }
    }

    @Override
    public void enableNextButton() {
        buttonNext.setEnabled(true);
    }

    @Override
    public void swipeToNextPage() {
        pagerActions.moveNext();
    }
}
