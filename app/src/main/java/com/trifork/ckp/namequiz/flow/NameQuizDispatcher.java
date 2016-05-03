package com.trifork.ckp.namequiz.flow;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trifork.ckp.namequiz.R;
import com.trifork.ckp.namequiz.quiz.QuizScreen;
import com.trifork.ckp.namequiz.start.StartScreen;

import flow.Dispatcher;
import flow.Traversal;
import flow.TraversalCallback;

public final class NameQuizDispatcher implements Dispatcher {

    private final Activity activity;

    public NameQuizDispatcher(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void dispatch(Traversal traversal, TraversalCallback callback) {
        Log.d("NameQuizDispatcher", "Dispatching " + traversal);
        Object dest = traversal.destination.top();

        ViewGroup frame = (ViewGroup) activity.findViewById(R.id.activity_main);

        if (traversal.origin != null) {
            if (frame.getChildCount() > 0) {
                traversal.getState(traversal.origin.top()).save(frame.getChildAt(0));
                frame.removeAllViews();
            }
        }

        @LayoutRes final int layout;
        if (dest instanceof StartScreen) {
            layout = R.layout.start_screen;
        } else if (dest instanceof QuizScreen) {
            layout = R.layout.quiz_screen;
        } else {
            throw new AssertionError("Unknown screen " + dest);
        }

        View incomingView = LayoutInflater.from(traversal.createContext(dest, activity)) //
                .inflate(layout, frame, false);

        frame.addView(incomingView);
        traversal.getState(traversal.destination.top()).restore(incomingView);

        callback.onTraversalCompleted();
    }
}
