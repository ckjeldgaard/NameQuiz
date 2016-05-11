package com.trifork.ckp.namequiz.quiz.question;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import com.trifork.ckp.namequiz.R;
import com.trifork.ckp.namequiz.model.AnswerOption;
import com.trifork.ckp.namequiz.model.Question;

import java.util.List;

public final class QuestionAdapter extends PagerAdapter implements QuestionContract.QuestionView {

    private static final String TAG = QuestionAdapter.class.getSimpleName();

    private final List<Question> questions;
    private final Context context;

    private final QuestionContract.UserActionsListener questionPresenter;

    private ImageView personImage;
    private Button buttonOption1, buttonOption2, buttonOption3, buttonOption4, buttonNext;

    public QuestionAdapter(@NonNull List<Question> questions, Context context) {
        this.questionPresenter = new QuestionPresenter(this);
        this.questions = questions;
        this.context = context;
    }

    @Override
    public Question getQuestion(int position) {
        return this.questions.get(position);
    }

    @Override
    public ImageView getPersonImage() {
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
    public void enableNextButton() {
        Log.d(TAG, "enableNextButton() called");
        buttonNext.setEnabled(true);
        buttonNext.setText("HALLO");
        //notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return this.questions.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object view) {
        container.removeView((View) view);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        ViewGroup layout = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.question_view, container, false);

        this.personImage = (ImageView) layout.findViewById(R.id.image_person);
        this.buttonOption1 = (Button) layout.findViewById(R.id.option_1);
        this.buttonOption2 = (Button) layout.findViewById(R.id.option_2);
        this.buttonOption3 = (Button) layout.findViewById(R.id.option_3);
        this.buttonOption4 = (Button) layout.findViewById(R.id.option_4);
        this.buttonNext = (Button) layout.findViewById(R.id.question_next_button);

        buttonOption1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionPresenter.selectPerson(position, 0);
            }
        });
        buttonOption2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionPresenter.selectPerson(position, 1);
            }
        });
        buttonOption3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionPresenter.selectPerson(position, 2);
            }
        });
        buttonOption4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionPresenter.selectPerson(position, 3);
            }
        });

        this.questionPresenter.loadQuestion(position, this.context);

        container.addView(layout);
        return layout;
    }
}
