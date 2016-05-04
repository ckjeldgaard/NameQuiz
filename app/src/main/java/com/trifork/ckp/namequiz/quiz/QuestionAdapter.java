package com.trifork.ckp.namequiz.quiz;

import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.trifork.ckp.namequiz.R;
import com.trifork.ckp.namequiz.model.Question;

import java.util.List;

public final class QuestionAdapter extends PagerAdapter {

    private final List<Question> questions;
    private final LayoutInflater layoutInflater;

    public QuestionAdapter(List<Question> questions, LayoutInflater layoutInflater) {
        this.questions = questions;
        this.layoutInflater = layoutInflater;
    }

    public Question getItem(int position) {
        return this.questions.get(position);
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
        ViewGroup layout = (ViewGroup) layoutInflater.inflate(R.layout.question_view, container, false);

        ImageView image = (ImageView) layout.findViewById(R.id.image_person);
        image.setImageBitmap(Bitmap.createScaledBitmap(getItem(position).person().image(), 768, 768, false));

        container.addView(layout);
        return layout;
    }
}
