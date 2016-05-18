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

    @Override
    public void loadDepartments() {
        getView().showLoading(false);

        repository.getDepartments(new Repository.LoadDepartmentsCallback() {
            @Override
            public void onDepartmentsLoaded(List<Department> departments) {
                if (isViewAttached()) {
                    getView().setData(departments);
                    getView().showContent();
                }
            }

            @Override
            public void onFailure(Exception ex) {
                if (isViewAttached()) {
                    getView().showError(ex, false);
                }
            }
        });
    }

    @Override
    public void startNewQuiz(Department department) {
        getView().showStartQuiz(department);
    }
}
