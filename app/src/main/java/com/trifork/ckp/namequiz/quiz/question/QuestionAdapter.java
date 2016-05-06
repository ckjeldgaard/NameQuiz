package com.trifork.ckp.namequiz.quiz.question;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.trifork.ckp.namequiz.R;
import com.trifork.ckp.namequiz.model.Question;

import java.util.List;

public final class QuestionAdapter extends PagerAdapter implements QuestionContract.QuestionView {

    private final List<Question> questions;
    private final Context context;

    private final QuestionContract.UserActionsListener questionPresenter;

    private ImageView personImage;

    public QuestionAdapter(@NonNull List<Question> questions, Context context) {
        this.questionPresenter = new QuestionPresenter(this);
        this.questions = questions;
        this.context = context;
    }

    @Override
    public Question getItem(int position) {
        return this.questions.get(position);
    }

    @Override
    public ImageView getPersonImage() {
        return this.personImage;
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
    public Object instantiateItem(ViewGroup container, int position) {
        ViewGroup layout = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.question_view, container, false);

        this.personImage = (ImageView) layout.findViewById(R.id.image_person);
        this.questionPresenter.loadQuestion(position, this.context);

        container.addView(layout);
        return layout;
    }
}
