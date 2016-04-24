package com.trifork.ckp.namequiz.start;

import android.os.Parcel;
import android.os.Parcelable;

public final class StartScreen implements Parcelable {


    public static final Creator<StartScreen> CREATOR = new Creator<StartScreen>() {
        @Override
        public StartScreen createFromParcel(Parcel in) {
            return new StartScreen();
        }

        @Override
        public StartScreen[] newArray(int size) {
            return new StartScreen[size];
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
        return o != null && o instanceof StartScreen;
    }
}
