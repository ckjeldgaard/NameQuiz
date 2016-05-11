package com.trifork.ckp.namequiz.quiz.question;

import android.support.v4.view.ViewPager;
import android.view.View;
import com.trifork.ckp.namequiz.view.ViewPagerAdapter;

import java.util.List;

public class QuestionAdapter extends ViewPagerAdapter {

    private final List<QuestionLayout> questionLayouts;

    public QuestionAdapter(List<QuestionLayout> questionLayouts) {
        this.questionLayouts = questionLayouts;
    }

    @Override
    public View getView(int position, ViewPager pager) {
        return questionLayouts.get(position);
    }

    @Override
    public int getCount() {
        return questionLayouts.size();
    }
}
