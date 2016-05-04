package com.trifork.ckp.namequiz.start;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.hannesdorfmann.mosby.mvp.viewstate.ViewState;
import com.hannesdorfmann.mosby.mvp.viewstate.layout.MvpViewStateRelativeLayout;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.data.RetainingLceViewState;
import com.trifork.ckp.namequiz.Injection;
import com.trifork.ckp.namequiz.R;
import com.trifork.ckp.namequiz.model.Department;
import com.trifork.ckp.namequiz.quiz.QuizScreen;

import java.util.ArrayList;
import java.util.List;

import flow.Flow;

public final class StartLayout extends MvpViewStateRelativeLayout<StartContract.StartView, StartPresenter> implements StartContract.StartView {

    private final StartPresenter presenter;
    private List<Department> departments;

    private RelativeLayout contentView;
    private TextView errorView;
    private ProgressBar loadingView;
    private Button startButton;
    private Spinner departmentSpinner;

    private ArrayAdapter<String> departmentsAdapter;

    public StartLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.presenter = new StartPresenter(Injection.provideRepository(), this);
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

        startButton = (Button) findViewById(R.id.start_button);

        departmentsAdapter = new ArrayAdapter(getContext(), R.layout.spinner_item, this.departments);
        departmentsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        departmentSpinner = (Spinner) findViewById(R.id.department_spinner);
        departmentSpinner.setAdapter(departmentsAdapter);

        startButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.startNewQuiz(
                        departments.get(
                                departmentSpinner.getSelectedItemPosition()
                        )
                );
            }
        });
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
        return new RetainingLceViewState<List<Department>, StartContract.StartView>();
    }

    @Override
    public void onNewViewStateInstance() {
        loadData(false);
    }

    @Override
    public void showStartQuiz(Department department) {
        Flow.get(startButton).set(
                new QuizScreen(
                        department
                )
        );
    }
}
