package com.trifork.ckp.namequiz.start;

import android.os.Handler;
import android.util.Log;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.trifork.ckp.namequiz.data.Department;

import java.util.ArrayList;
import java.util.List;

public class StartPresenter extends MvpBasePresenter<StartView> {

    public void loadDepartments() {
        getView().showLoading(false);

        // Simulate network by delaying the execution.
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                List<Department> departments = new ArrayList<>();
                departments.add(new Department(1, "Copenhagen"));
                departments.add(new Department(2, "Aarhus"));
                departments.add(new Department(3, "Esbjerg"));
                getView().setData(departments);
                getView().showContent();
            }
        }, 2000);
    }
}
