package com.trifork.ckp.namequiz.start;

import android.support.annotation.NonNull;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.trifork.ckp.namequiz.model.Department;
import com.trifork.ckp.namequiz.data.Repository;

import java.util.List;

public class StartPresenter extends MvpBasePresenter<StartContract.StartView> implements StartContract.UserActionsListener {

    private final Repository repository;

    public StartPresenter(@NonNull Repository repository, StartContract.StartView view) {
        this.repository = repository;
        this.attachView(view);
    }

    public void loadDepartments() {
        getView().showLoading(false);

        repository.getDepartments(new Repository.LoadDepartmentsCallback() {
            @Override
            public void onDepartmentsLoaded(List<Department> departments) {
                getView().setData(departments);
                getView().showContent();
            }

            @Override
            public void onFailure(String errorMessage) {
                getView().showError(null, false);
            }
        });
    }

    @Override
    public void startNewQuiz(Department department) {
        getView().showStartQuiz(department);
    }
}
