package com.trifork.ckp.namequiz.model.stubs;

import com.trifork.ckp.namequiz.model.Department;
import java.util.ArrayList;
import java.util.List;

public final class StubbedDepartmentsFactory {

    public StubbedDepartmentsFactory() {
    }

    public List<Department> produceDepartments() {
        return new ArrayList<Department>() {{
            add(new Department(1, "New York"));
            add(new Department(2, "Los Angeles"));
            add(new Department(3, "Chicago"));
        }};
    }

    public Department produceDepartment() {
        return new Department(1, "Copenhagen");
    }
}
