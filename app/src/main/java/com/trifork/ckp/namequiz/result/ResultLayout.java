package com.trifork.ckp.namequiz.result;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.trifork.ckp.namequiz.NameQuizApplication;
import com.trifork.ckp.namequiz.R;
import com.trifork.ckp.namequiz.model.QuestionResult;
import com.trifork.ckp.namequiz.start.StartScreen;

import java.util.List;

import flow.Direction;
import flow.Flow;

public class ResultLayout extends RelativeLayout implements ResultContract.ResultView {

    private static final int SCORE_GOOD_THRESHOLD = 8;
    private static final int SCORE_BAD_THRESHOLD = 3;
    private final ResultContract.UserActionsListener presenter;

    private ImageView resultImage;
    private TextView resultHeadline, resultScore;
    private ListView checklist;
    private Button resultButton;

    public ResultLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        ResultScreen screen = Flow.getKey(this);
        this.presenter = new ResultPresenter(this, screen.score, screen.questionResults);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        resultImage = (ImageView) findViewById(R.id.image_result);
        resultHeadline = (TextView) findViewById(R.id.text_result_headline);
        resultScore = (TextView) findViewById(R.id.text_result_score);
        checklist = (ListView) findViewById(R.id.checklist);
        resultButton = (Button) findViewById(R.id.result_button);
        resultButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.restart();
            }
        });

        presenter.loadData();
    }

    @Override
    public void setResult(int score) {
        int imageId;
        String headline;

        if (score >= SCORE_GOOD_THRESHOLD) {
            imageId = R.mipmap.smiley_happy;
            headline = getResources().getString(R.string.text_result_headline_good);
        } else if (score <= SCORE_BAD_THRESHOLD) {
            imageId = R.mipmap.smiley_negative;
            headline = getResources().getString(R.string.text_result_headline_bad);
        } else {
            imageId = R.mipmap.smiley_neutral;
            headline = getResources().getString(R.string.text_result_headline_neutral);
        }
        resultImage.setImageResource(imageId);
        resultHeadline.setText(headline);
    }

    @Override
    public void setScore(int score, int numQuestions) {
        resultScore.setText(
                getResources().getString(
                        R.string.text_result_score,
                        score,
                        numQuestions
                )
        );
    }

    @Override
    public void showResultList(List<QuestionResult> questionResults) {
        checklist.setAdapter(
                new AnswerResultListAdapter(
                        getContext(),
                        R.layout.answer_result_list_item,
                        questionResults,
                        ((NameQuizApplication)getContext().getApplicationContext()).getInjection().providePersonImage(getContext())
                )
        );
    }

    @Override
    public void returnToStart() {
        Flow.get(resultButton).replaceTop(
                new StartScreen(),
                Direction.REPLACE
        );
    }
}
