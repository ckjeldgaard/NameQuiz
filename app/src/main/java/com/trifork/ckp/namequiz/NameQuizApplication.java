package com.trifork.ckp.namequiz;

import android.app.Application;

public class NameQuizApplication extends Application {

    private Injection injection;

    @Override
    public void onCreate() {
        super.onCreate();
        injection = new Injection(this);
    }

    public Injection getInjection() {
        return injection;
    }
}
