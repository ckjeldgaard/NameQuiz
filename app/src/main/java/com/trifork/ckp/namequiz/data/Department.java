package com.trifork.ckp.namequiz.data;

public class Department {

    private final int id;
    private final String departmentName;

    public Department(int id, String departmentName) {
        this.id = id;
        this.departmentName = departmentName;
    }

    public int id() {
        return id;
    }

    public String name() {
        return departmentName;
    }

    @Override
    public String toString() {
        return this.departmentName;
    }
}
