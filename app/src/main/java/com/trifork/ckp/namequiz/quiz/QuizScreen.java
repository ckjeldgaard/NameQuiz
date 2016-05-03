package com.trifork.ckp.namequiz.quiz;

import android.os.Parcel;
import android.os.Parcelable;

public final class QuizScreen implements Parcelable {

    public static final Creator<QuizScreen> CREATOR = new Creator<QuizScreen>() {
        @Override
        public QuizScreen createFromParcel(Parcel in) {
            return new QuizScreen();
        }

        @Override
        public QuizScreen[] newArray(int size) {
            return new QuizScreen[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    @Override
    public boolean equals(Object o) {
        return o != null && o instanceof QuizScreen;
    }
}
