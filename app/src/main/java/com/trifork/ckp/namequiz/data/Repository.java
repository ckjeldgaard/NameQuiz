package com.trifork.ckp.namequiz.data;

import android.support.annotation.NonNull;

import com.trifork.ckp.namequiz.model.Department;
import com.trifork.ckp.namequiz.model.Person;

import java.util.List;

/**
 * Main entry point for accessing departments data.
 */
public interface Repository {

    interface LoadDepartmentsCallback {
        void onDepartmentsLoaded(List<Department> departments);
        void onFailure(String errorMessage);
    }

    interface LoadPersonsCallback {
        void onPersonsLoaded(List<Person> persons);
        void onFailure(String errorMessage);
    }

    void getDepartments(@NonNull LoadDepartmentsCallback callback);

    void getPersons(@NonNull LoadPersonsCallback callback, @NonNull Department department);

    void refreshData();
}
