package com.trifork.ckp.namequiz.result;

import android.os.Parcel;
import android.os.Parcelable;

import com.trifork.ckp.namequiz.model.Answer;
import com.trifork.ckp.namequiz.model.QuestionResult;

import java.util.List;

public class ResultScreen implements Parcelable {

    final List<QuestionResult> questionResults;

    public ResultScreen(List<QuestionResult> questionResults) {
        this.questionResults = questionResults;
    }

    protected ResultScreen(Parcel in) {
        questionResults = in.createTypedArrayList(QuestionResult.CREATOR);
    }

    public static final Creator<ResultScreen> CREATOR = new Creator<ResultScreen>() {
        @Override
        public ResultScreen createFromParcel(Parcel in) {
            return new ResultScreen(in);
        }

        @Override
        public ResultScreen[] newArray(int size) {
            return new ResultScreen[size];
        }
    };

    @Override
    public boolean equals(Object o) {
        return o != null && o instanceof ResultScreen;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(questionResults);
    }
}
