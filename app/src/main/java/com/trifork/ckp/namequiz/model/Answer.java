package com.trifork.ckp.namequiz.model;

import android.os.Parcel;
import android.os.Parcelable;

public final class Answer implements Parcelable {

    private final String answer;

    public Answer(String answer) {
        this.answer = answer;
    }

    protected Answer(Parcel in) {
        answer = in.readString();
    }

    public static final Creator<Answer> CREATOR = new Creator<Answer>() {
        @Override
        public Answer createFromParcel(Parcel in) {
            return new Answer(in);
        }

        @Override
        public Answer[] newArray(int size) {
            return new Answer[size];
        }
    };

    public String get() {
        return answer;
    }

    @Override
    public String toString() {
        return answer;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(answer);
    }
}
