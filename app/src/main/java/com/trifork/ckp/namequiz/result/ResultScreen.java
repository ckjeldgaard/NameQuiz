package com.trifork.ckp.namequiz.result;

import android.os.Parcel;
import android.os.Parcelable;

public class ResultScreen implements Parcelable {

    public static final Creator<ResultScreen> CREATOR = new Creator<ResultScreen>() {
        @Override
        public ResultScreen createFromParcel(Parcel in) {
            return new ResultScreen();
        }

        @Override
        public ResultScreen[] newArray(int size) {
            return new ResultScreen[size];
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
        return o != null && o instanceof ResultScreen;
    }
}
