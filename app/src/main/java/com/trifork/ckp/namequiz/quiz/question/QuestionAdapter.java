package com.trifork.ckp.namequiz.quiz.question;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.trifork.ckp.namequiz.R;
import com.trifork.ckp.namequiz.model.Question;

import java.util.List;

public final class QuestionAdapter extends PagerAdapter {

    private final List<Question> questions;
    private final Context context;

    public QuestionAdapter(List<Question> questions, Context context) {
        this.questions = questions;
        this.context = context;
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
        ViewGroup layout = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.question_view, container, false);

        int width = Math.round(context.getResources().getDimension(R.dimen.person_image_width));
        int height = Math.round(context.getResources().getDimension(R.dimen.person_image_height));

        ImageView imageView = (ImageView) layout.findViewById(R.id.image_person);
        Picasso.with(context)
                .load(getItem(position).person().imageUrl())
                .resize(width, height)
                .centerCrop()
                .into(imageView);

        container.addView(layout);
        return layout;
    }
}
