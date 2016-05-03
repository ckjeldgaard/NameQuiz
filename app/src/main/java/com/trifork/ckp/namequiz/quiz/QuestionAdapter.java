package com.trifork.ckp.namequiz.quiz;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trifork.ckp.namequiz.R;
import com.trifork.ckp.namequiz.model.Question;

import java.util.List;

public class QuestionAdapter extends PagerAdapter {

    private final Context context;
    private final List<Question> questions;

    public QuestionAdapter(Context ctx, List<Question> questions) {
        this.context = ctx;
        this.questions = questions;
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
        LayoutInflater inflater = LayoutInflater.from(context);
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.question_view, container, false);
        container.addView(layout);
        return layout;
    }
}
