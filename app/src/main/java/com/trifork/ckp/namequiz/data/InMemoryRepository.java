package com.trifork.ckp.namequiz.data;

import android.support.annotation.NonNull;
import android.util.Log;

import com.trifork.ckp.namequiz.model.Department;
import com.trifork.ckp.namequiz.model.NameQuiz;
import com.trifork.ckp.namequiz.model.Person;
import com.trifork.ckp.namequiz.model.Quiz;

import java.util.ArrayList;
import java.util.List;

public class InMemoryRepository implements Repository {

    private static final String TAG = InMemoryRepository.class.getSimpleName();

    private final ServiceApi serviceApi;
    private List<Department> cachedDepartments = new ArrayList<>();

    public InMemoryRepository(@NonNull ServiceApi serviceApi) {
        this.serviceApi = serviceApi;
    }

    @Override
    public void getDepartments(@NonNull final LoadDepartmentsCallback callback) {
        // Load from API only if needed.
        if (cachedDepartments.isEmpty()) {
            serviceApi.getAllDepartments(new ServiceApi.ServiceCallback<List<Department>>() {
                @Override
                public void onLoaded(List<Department> departments) {
                    cachedDepartments = departments;
                    callback.onDepartmentsLoaded(departments);
                }

                @Override
                public void onError(Exception ex) {
                    callback.onFailure(ex);
                }
            });
        } else {
            callback.onDepartmentsLoaded(cachedDepartments);
        }
    }

    private Department getDepartmentFromCache(long departmentId) {
        for (Department department : cachedDepartments) {
            if (department.getId() == departmentId) {
                return department;
            }
        }
        throw new RuntimeException(String.format("Department with ID %s not found in cache.", departmentId));
    }

    @Override
    public void produceQuiz(@NonNull final LoadQuizCallback callback, @NonNull final long departmentId) {
        if (!cachedDepartments.isEmpty()) {
            requestPersons(callback, getDepartmentFromCache(departmentId));
        } else {
            // Call getDepartments to retrieve departments.
            serviceApi.getAllDepartments(new ServiceApi.ServiceCallback<List<Department>>() {
                @Override
                public void onLoaded(List<Department> departments) {
                    cachedDepartments = departments;
                    requestPersons(callback, getDepartmentFromCache(departmentId));
                }

                @Override
                public void onError(Exception ex) {
                    callback.onFailure(ex);
                }
            });
        }
    }

    private void requestPersons(@NonNull final LoadQuizCallback callback, Department department) {
        serviceApi.getPersonsBelongingToDepartment(new ServiceApi.ServiceCallback<List<Person>>() {
            @Override
            public void onLoaded(List<Person> persons) {
                callback.onQuizLoaded(new NameQuiz(persons));
            }

            @Override
            public void onError(Exception ex) {
                callback.onFailure(ex);
            }
        }, department);
    }

    @Override
    public void refreshData() {
        cachedDepartments.clear();
    }
}
