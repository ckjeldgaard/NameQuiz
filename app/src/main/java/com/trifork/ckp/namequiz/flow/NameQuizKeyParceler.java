package com.trifork.ckp.namequiz.flow;

import android.os.Parcelable;
import android.support.annotation.NonNull;

import flow.KeyParceler;

/**
 * Assumes states are {@link Parcelable}.
 */
public class NameQuizKeyParceler implements KeyParceler {
    @NonNull @Override
    public Parcelable toParcelable(@NonNull Object key) {
        return (Parcelable) key;
    }

    @NonNull @Override
    public Object toKey(@NonNull Parcelable parcelable) {
        return parcelable;
    }
}
