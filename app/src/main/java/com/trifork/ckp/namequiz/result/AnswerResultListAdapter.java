package com.trifork.ckp.namequiz.result;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.trifork.ckp.namequiz.R;
import com.trifork.ckp.namequiz.model.Person;
import com.trifork.ckp.namequiz.model.QuestionResult;
import com.trifork.ckp.namequiz.util.PersonImage;

import java.util.List;

public class AnswerResultListAdapter extends ArrayAdapter<QuestionResult> {

    private final Context context;
    private final int layoutResourceId;
    private final List<QuestionResult> questionResults;
    private final PersonImage personImage;

    public AnswerResultListAdapter(Context context, int layoutResourceId, List<QuestionResult> questionResults, PersonImage personImage) {
        super(context, layoutResourceId, questionResults);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.questionResults = questionResults;
        this.personImage = personImage;
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

        defineViewBehavior(position, holder, questionResults.get(position));

        return row;
    }

    private void defineViewBehavior(int position, ViewHolder holder, QuestionResult questionResult) {
        personImage.loadThumbnail(questionResult.imageUrl(), holder.imagePerson);

        holder.textHeadline.setText(
                context.getString(
                        R.string.list_item_answer_result_headline,
                        position + 1,
                        questionResult.isAnswerCorrect() ? context.getString(R.string.correct) : context.getString(R.string.wrong)
                )
        );

        if (questionResult.isAnswerCorrect()) {
            holder.textHeadline.setTextColor(ContextCompat.getColor(context, R.color.green));
            holder.textExplanation.setText(
                    context.getString(
                            R.string.list_item_answer_result_explanation_correct,
                            questionResult.correctResult()
                    )
            );
        } else {
            holder.textHeadline.setTextColor(ContextCompat.getColor(context, R.color.red));
            holder.textExplanation.setText(
                    context.getString(
                            R.string.list_item_answer_result_explanation_wrong,
                            questionResult.correctResult(),
                            questionResult.answerGiven()
                    )
            );
        }
    }

    public class ViewHolder
    {
        ImageView imagePerson;
        TextView textHeadline, textExplanation;
    }
}
