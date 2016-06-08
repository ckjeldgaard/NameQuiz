package com.trifork.ckp.namequiz;

import android.app.Application;

import com.firebase.client.Firebase;

public class NameQuizApplication extends Application {

    private Injection injection;

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
        injection = new Injection(this);
    }

    public Injection getInjection() {
        return injection;
    }
}
