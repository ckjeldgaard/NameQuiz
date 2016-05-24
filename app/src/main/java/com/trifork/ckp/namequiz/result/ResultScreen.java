package com.trifork.ckp.namequiz.result;

import android.os.Parcel;
import android.os.Parcelable;

import com.trifork.ckp.namequiz.model.QuestionResult;

import java.util.List;

public final class ResultScreen implements Parcelable {

    final int score;
    final List<QuestionResult> questionResults;

    public ResultScreen(int score, List<QuestionResult> questionResults) {
        this.score = score;
        this.questionResults = questionResults;
    }

    protected ResultScreen(Parcel in) {
        score = in.readInt();
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
        dest.writeInt(score);
        dest.writeTypedList(questionResults);
    }
}
