package com.trifork.ckp.namequiz.data;

public class Department {

    private final long id;
    private final String departmentName;

    public Department(long id, String departmentName) {
        this.id = id;
        this.departmentName = departmentName;
    }

    public long getId() {
        return id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    @Override
    public String toString() {
        return this.departmentName;
    }
}
