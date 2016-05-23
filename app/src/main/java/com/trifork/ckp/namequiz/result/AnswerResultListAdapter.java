package com.trifork.ckp.namequiz.result;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.trifork.ckp.namequiz.R;
import com.trifork.ckp.namequiz.model.QuestionResult;

import java.util.List;

public class AnswerResultListAdapter extends ArrayAdapter<QuestionResult> {

    private final Context context;
    private final int layoutResourceId;
    private final List<QuestionResult> questionResults;

    public AnswerResultListAdapter(Context context, int layoutResourceId, List<QuestionResult> questionResults) {
        super(context, layoutResourceId, questionResults);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.questionResults = questionResults;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new ViewHolder();
            holder.imagePerson = (ImageView) row.findViewById(R.id.image_answer_result_person);
            holder.textHeadline = (TextView) row.findViewById(R.id.text_answer_result_headline);
            holder.textExplanation = (TextView) row.findViewById(R.id.text_answer_result_explanation);

            row.setTag(holder);
        } else {
            holder = (ViewHolder)row.getTag();
        }

        QuestionResult questionResult = questionResults.get(position);
        holder.textHeadline.setText("Question #1: " + questionResult.answerGiven());

        return row;
    }

    public class ViewHolder
    {
        ImageView imagePerson;
        TextView textHeadline, textExplanation;
    }
}
