package com.trifork.ckp.namequiz.data;

import java.util.List;

/**
 * Defines an interface to the service API that is used by this application. All data request should
 * be piped through this interface.
 */
public interface ServiceApi {

    interface ServiceCallback<T> {
        void onLoaded(T notes);
    }

    void getAllDepartments(ServiceCallback<List<Department>> callback);
}
