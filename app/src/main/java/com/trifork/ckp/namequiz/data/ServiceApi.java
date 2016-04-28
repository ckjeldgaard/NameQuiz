package com.trifork.ckp.namequiz.data;

import com.trifork.ckp.namequiz.model.Department;

import java.util.List;

/**
 * Defines an interface to the service API that is used by this application. All data request should
 * be piped through this interface.
 */
public interface ServiceApi {

    interface ServiceCallback<T> {
        void onLoaded(T departments);
        void onError(String errorMessage);
    }

    void getAllDepartments(ServiceCallback<List<Department>> callback);
}
