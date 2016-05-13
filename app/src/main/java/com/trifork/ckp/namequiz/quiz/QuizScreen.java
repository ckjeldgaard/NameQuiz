package com.trifork.ckp.namequiz.quiz;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.trifork.ckp.namequiz.model.Department;

public final class QuizScreen implements Parcelable {

    final Department department;

    public QuizScreen(Department department) {
        this.department = department;
    }

    protected QuizScreen(Parcel in) {
        department = in.readParcelable(Department.class.getClassLoader());
    }

    public static final Creator<QuizScreen> CREATOR = new Creator<QuizScreen>() {
        @Override
        public QuizScreen createFromParcel(Parcel in) {
            return new QuizScreen(in);
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
        dest.writeParcelable(department, flags);
    }
}
