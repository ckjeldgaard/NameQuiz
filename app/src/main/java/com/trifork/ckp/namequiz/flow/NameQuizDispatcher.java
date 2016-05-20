package com.trifork.ckp.namequiz.flow;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trifork.ckp.namequiz.R;
import com.trifork.ckp.namequiz.quiz.QuizScreen;
import com.trifork.ckp.namequiz.result.ResultScreen;
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
        Object destination = traversal.destination.top();

        @LayoutRes final int layout;
        if (destination instanceof StartScreen) {
            layout = R.layout.start_screen;
        } else if (destination instanceof QuizScreen) {
            layout = R.layout.quiz_screen;
        }  else if (destination instanceof ResultScreen) {
            layout = R.layout.result_screen;
        } else {
            throw new AssertionError("Unknown screen " + destination);
        }

        // Update container: remove oldView, insert newView
        ViewGroup container = (ViewGroup) activity.findViewById(R.id.activity_main);


        View newView = LayoutInflater.from(traversal.createContext(destination, activity))
                .inflate(layout, container, false);

        // Remove current screen from container
        if (traversal.origin != null && container.getChildCount() > 0) {
            traversal.getState(traversal.origin.top()).save(container.getChildAt(0));
            container.removeAllViews();
        }

        // Restore state before adding view (i.e. caused by onBackPressed)
        traversal.getState(traversal.destination.top()).restore(newView);

        // add new screen
        container.addView(newView);

        callback.onTraversalCompleted(); // Tell Flow that we are done
    }
}
