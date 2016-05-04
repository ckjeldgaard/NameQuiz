package com.trifork.ckp.namequiz.data;

import android.support.annotation.NonNull;
import android.util.Log;

import com.trifork.ckp.namequiz.model.Department;
import com.trifork.ckp.namequiz.model.Person;

import java.util.List;

public class InMemoryRepository implements Repository {

    private static final String TAG = InMemoryRepository.class.getSimpleName();

    private final ServiceApi serviceApi;
    private List<Department> cachedDepartments;

    public InMemoryRepository(@NonNull ServiceApi serviceApi) {
        this.serviceApi = serviceApi;
    }

    @Override
    public void getDepartments(@NonNull final LoadDepartmentsCallback callback) {
        // Load from API only if needed.
        if (cachedDepartments == null) {
            serviceApi.getAllDepartments(new ServiceApi.ServiceCallback<List<Department>>() {
                @Override
                public void onLoaded(List<Department> departments) {
                    cachedDepartments = departments;
                    callback.onDepartmentsLoaded(departments);
                }

                @Override
                public void onError(String errorMessage) {
                    callback.onFailure(errorMessage);
                }
            });
        } else {
            callback.onDepartmentsLoaded(cachedDepartments);
        }
    }

    @Override
    public void getPersons(@NonNull final LoadPersonsCallback callback, @NonNull Department department) {
        // TODO: Caching
        serviceApi.getPersonsBelongingToDepartment(new ServiceApi.ServiceCallback<List<Person>>() {
            @Override
            public void onLoaded(List<Person> persons) {
                Log.d(TAG, "getPersons onLoaded() called");
                callback.onPersonsLoaded(persons);
            }

            @Override
            public void onError(String errorMessage) {
                Log.e(TAG, "getPersons onError() called with: " + "errorMessage = [" + errorMessage + "]");
                callback.onFailure(errorMessage);
            }
        }, department);
    }

    @Override
    public void refreshData() {
        cachedDepartments = null;
    }
}
