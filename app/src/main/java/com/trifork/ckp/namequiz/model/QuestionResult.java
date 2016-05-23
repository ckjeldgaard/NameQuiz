package com.trifork.ckp.namequiz.model;

import android.os.Parcel;
import android.os.Parcelable;

public final class QuestionResult implements Parcelable {

    private final String answerGiven;
    private final String correctResult;
    private final String imageUrl;

    public QuestionResult(String answerGiven, String correctResult, String imageUrl) {
        this.answerGiven = answerGiven;
        this.correctResult = correctResult;
        this.imageUrl = imageUrl;
    }

    protected QuestionResult(Parcel in) {
        answerGiven = in.readString();
        correctResult = in.readString();
        imageUrl = in.readString();
    }

    public static final Creator<QuestionResult> CREATOR = new Creator<QuestionResult>() {
        @Override
        public QuestionResult createFromParcel(Parcel in) {
            return new QuestionResult(in);
        }

        @Override
        public QuestionResult[] newArray(int size) {
            return new QuestionResult[size];
        }
    };

    public String answerGiven() {
        return answerGiven;
    }

    public String correctResult() {
        return correctResult;
    }

    public String imageUrl() {
        return imageUrl;
    }

    public boolean isAnswerCorrect() {
        return answerGiven.equals(correctResult);
    }

    @Override
    public String toString() {
        return "QuestionResult{" +
                "answerGiven='" + answerGiven + '\'' +
                ", correctResult='" + correctResult + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(answerGiven);
        dest.writeString(correctResult);
        dest.writeString(imageUrl);
    }
}
