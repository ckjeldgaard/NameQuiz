package com.trifork.ckp.namequiz;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.firebase.client.Firebase;
import com.trifork.ckp.namequiz.flow.NameQuizDispatcher;
import com.trifork.ckp.namequiz.flow.NameQuizKeyParceler;
import com.trifork.ckp.namequiz.start.StartScreen;

import flow.Flow;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override protected void attachBaseContext(Context baseContext) {
        baseContext = Flow.configure(baseContext, this)
                .dispatcher(new NameQuizDispatcher(this))
                .defaultKey(new StartScreen())
                .keyParceler(new NameQuizKeyParceler())
                .install();
        super.attachBaseContext(baseContext);
    }

    @Override public void onBackPressed() {
        if (!Flow.get(this).goBack()) {
            super.onBackPressed();
        }
    }
}
