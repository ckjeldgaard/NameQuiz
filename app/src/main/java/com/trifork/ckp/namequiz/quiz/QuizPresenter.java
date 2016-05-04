package com.trifork.ckp.namequiz.quiz;

import android.support.annotation.NonNull;
import android.util.Log;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.trifork.ckp.namequiz.data.Repository;
import com.trifork.ckp.namequiz.model.Department;
import com.trifork.ckp.namequiz.model.Person;

import java.util.List;

public final class QuizPresenter extends MvpBasePresenter<QuizView> {

    private final Repository repository;

    public QuizPresenter(@NonNull Repository repository, QuizView view) {
        this.repository = repository;
        this.attachView(view);
    }

    public void loadPersons(Department department) {
        getView().showLoading(false);

        this.repository.getPersons(new Repository.LoadPersonsCallback() {
            @Override
            public void onPersonsLoaded(List<Person> persons) {
                Log.d("QuizPresenter", "onPersonsLoaded() called with: " + "persons = [" + persons + "]");
                getView().setData(persons);
                getView().showContent();
            }

            @Override
            public void onFailure(String errorMessage) {
                Log.e("QuizPresenter", "onFailure() called with: " + "errorMessage = [" + errorMessage + "]");
                getView().showError(null, false);
            }
        }, department);
    }
}
