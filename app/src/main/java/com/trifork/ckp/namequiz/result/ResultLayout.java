package com.trifork.ckp.namequiz.result;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.trifork.ckp.namequiz.NameQuizApplication;
import com.trifork.ckp.namequiz.R;

import flow.Flow;

public class ResultLayout extends RelativeLayout implements ResultContract.ResultView {

    private final ResultPresenter presenter;

    public ResultLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.presenter = new ResultPresenter(this, ((NameQuizApplication)getContext().getApplicationContext()).getInjection().provideRepository());
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        //TextView textView = (TextView) findViewById(R.id.textView);
        //textView.setText("Hallo!");

        ResultScreen screen = Flow.getKey(this);

        ListView checklist = (ListView) findViewById(R.id.checklist);
        checklist.setAdapter(new AnswerResultListAdapter(getContext(), screen.answers));

        Log.d("Answers", "" + screen.answers);
    }
}
