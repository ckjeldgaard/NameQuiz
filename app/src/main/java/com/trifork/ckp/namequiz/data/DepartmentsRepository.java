package com.trifork.ckp.namequiz.data;

import android.support.annotation.NonNull;

import com.trifork.ckp.namequiz.model.Department;

import java.util.List;

/**
 * Main entry point for accessing departments data.
 */
public interface DepartmentsRepository {

    interface LoadDepartmentsCallback {
        void onDepartmentsLoaded(List<Department> departments);
        void onFailure(String errorMessage);
    }

    void getDepartments(@NonNull LoadDepartmentsCallback callback);

    void refreshData();
}
