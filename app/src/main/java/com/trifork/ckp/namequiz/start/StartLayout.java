package com.trifork.ckp.namequiz.start;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.hannesdorfmann.mosby.mvp.viewstate.ViewState;
import com.hannesdorfmann.mosby.mvp.viewstate.layout.MvpViewStateRelativeLayout;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.data.RetainingLceViewState;
import com.trifork.ckp.namequiz.R;
import com.trifork.ckp.namequiz.data.Department;

import java.util.ArrayList;
import java.util.List;

public class StartLayout extends MvpViewStateRelativeLayout<StartView, StartPresenter> implements StartView {

    private final StartPresenter presenter;
    private List<Department> departments;

    private RelativeLayout contentView;
    private TextView errorView;
    private ProgressBar loadingView;
    private Spinner departmentSpinner;

    private ArrayAdapter<String> departmentsAdapter;

    public StartLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.presenter = new StartPresenter();
        this.departments = new ArrayList<>(0);
    }

    @NonNull
    @Override
    public StartPresenter createPresenter() {
        return this.presenter;
    }

    @Override protected void onFinishInflate() {
        super.onFinishInflate();

        contentView = (RelativeLayout) findViewById(R.id.contentView);
        errorView = (TextView) findViewById(R.id.errorView);
        loadingView = (ProgressBar) findViewById(R.id.loadingView);

        /*List<Department> dep = new ArrayList<Department>();
        dep.add(new Department(1, "Copenhagen"));
        dep.add(new Department(2, "Aarhus"));*/

        departmentsAdapter = new ArrayAdapter(getContext(), R.layout.spinner_item, this.departments);
        departmentsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        departmentSpinner = (Spinner) findViewById(R.id.department_spinner);
        departmentSpinner.setAdapter(departmentsAdapter);

        /*
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

        EditText nameView = (EditText) findViewById(R.id.welcome_screen_name);

        nameView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override public boolean onEditorAction(TextView view, int actionId, KeyEvent event) {
                Flow.get(view).set(new HelloScreen(view.getText().toString()));
                return true;
            }
        });*/
    }

    @Override
    public void showLoading(boolean b) {
        loadingView.setVisibility(VISIBLE);
        errorView.setVisibility(GONE);
        contentView.setVisibility(GONE);
    }

    @Override
    public void showContent() {
        loadingView.setVisibility(GONE);
        errorView.setVisibility(GONE);
        contentView.setVisibility(VISIBLE);
    }

    @Override
    public void showError(Throwable throwable, boolean pullToRefresh) {
        loadingView.setVisibility(GONE);
        errorView.setVisibility(VISIBLE);
        contentView.setVisibility(GONE);
    }

    @Override
    public void setData(List<Department> departments) {
        this.departments = departments;
        departmentsAdapter.clear();
        for (Department d : this.departments) {
            departmentsAdapter.insert(d.toString(), departmentsAdapter.getCount());
        }
        this.departmentsAdapter.notifyDataSetChanged();
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        this.presenter.loadDepartments();
    }

    @NonNull
    @Override
    public ViewState createViewState() {
        return new RetainingLceViewState<List<Department>, StartView>();
    }

    @Override
    public void onNewViewStateInstance() {
        loadData(false);
    }
}
