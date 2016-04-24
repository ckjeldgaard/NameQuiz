package com.trifork.ckp.namequiz.start;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.trifork.ckp.namequiz.R;

import flow.Flow;

public class StartView extends LinearLayout {

    public StartView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override protected void onFinishInflate() {
        super.onFinishInflate();
        /*EditText nameView = (EditText) findViewById(R.id.welcome_screen_name);

        nameView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override public boolean onEditorAction(TextView view, int actionId, KeyEvent event) {
                Flow.get(view).set(new HelloScreen(view.getText().toString()));
                return true;
            }
        });*/
    }
}
