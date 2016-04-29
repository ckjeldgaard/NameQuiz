package com.trifork.ckp.namequiz.question;

import android.os.Parcel;
import android.os.Parcelable;

public final class QuestionScreen implements Parcelable {

    public static final Creator<QuestionScreen> CREATOR = new Creator<QuestionScreen>() {
        @Override
        public QuestionScreen createFromParcel(Parcel in) {
            return new QuestionScreen();
        }

        @Override
        public QuestionScreen[] newArray(int size) {
            return new QuestionScreen[size];
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
        return o != null && o instanceof QuestionScreen;
    }
}
