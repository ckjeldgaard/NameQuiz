package com.trifork.ckp.namequiz.data;

import android.support.annotation.NonNull;

import java.util.List;

public class InMemoryDepartmentsRepository implements DepartmentsRepository {

    private final ServiceApi serviceApi;
    private List<Department> cachedDepartments;

    public InMemoryDepartmentsRepository(@NonNull ServiceApi serviceApi) {
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
            });
        } else {
            callback.onDepartmentsLoaded(cachedDepartments);
        }
    }
}
