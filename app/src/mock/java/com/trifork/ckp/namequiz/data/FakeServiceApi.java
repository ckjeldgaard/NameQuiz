package com.trifork.ckp.namequiz.data;

import com.trifork.ckp.namequiz.fakes.FakeDepartmentsFactory;
import com.trifork.ckp.namequiz.fakes.FakePersonsFactory;
import com.trifork.ckp.namequiz.model.Department;
import com.trifork.ckp.namequiz.model.Person;

import java.util.List;

/**
 * Fake implementation of {@link ServiceApi} to inject a fake service in a hermetic test.
 */
public class FakeServiceApi implements ServiceApi {

    @Override
    public void getAllDepartments(ServiceCallback<List<Department>> callback) {
        callback.onLoaded(
            new FakeDepartmentsFactory().produceDepartments("departments.json")
        );
    }

    @Override
    public void getPersonsBelongingToDepartment(ServiceCallback<List<Person>> callback, Department department) {
        callback.onLoaded(
                new FakePersonsFactory().producePersons("persons.json")
        );
    }
}
