package com.trifork.ckp.namequiz.start;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.trifork.ckp.namequiz.R;

import java.util.ArrayList;
import java.util.List;

import flow.Flow;

public class StartView extends RelativeLayout {

    private Spinner departmentSpinner;

    public StartView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override protected void onFinishInflate() {
        super.onFinishInflate();

        departmentSpinner = (Spinner) findViewById(R.id.department_spinner);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("København");
        categories.add("Aarhus");
        categories.add("Aalborg");
        categories.add("Esbjerg");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        departmentSpinner.setAdapter(dataAdapter);

        /*EditText nameView = (EditText) findViewById(R.id.welcome_screen_name);

        nameView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override public boolean onEditorAction(TextView view, int actionId, KeyEvent event) {
                Flow.get(view).set(new HelloScreen(view.getText().toString()));
                return true;
            }
        });*/
    }
}
