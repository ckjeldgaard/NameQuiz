package com.trifork.ckp.namequiz.result;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.trifork.ckp.namequiz.R;
import com.trifork.ckp.namequiz.model.Answer;

import java.util.List;

public class AnswerResultListAdapter extends BaseAdapter {

    private final Context context;
    private final List<Answer> answers;

    public AnswerResultListAdapter(Context context, List<Answer> answers) {
        this.context = context;
        this.answers = answers;

    }

    @Override
    public int getCount() {
        return answers.size();
    }

    @Override
    public Object getItem(int position) {
        return answers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return answers.get(position).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.answer_result_list_item, parent, false);

            holder = new ViewHolder();
            holder.imagePerson = (ImageView) convertView.findViewById(R.id.image_answer_result_person);
            holder.textHeadline = (TextView) convertView.findViewById(R.id.text_answer_result_headline);
            holder.textExplanation = (TextView) convertView.findViewById(R.id.text_answer_result_explanation);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.textHeadline.setText("Question #1: Correct");

        return convertView;
    }

    public class ViewHolder
    {
        ImageView imagePerson;
        TextView textHeadline, textExplanation;
    }
}
