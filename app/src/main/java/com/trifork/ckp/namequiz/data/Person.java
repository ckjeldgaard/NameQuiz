package com.trifork.ckp.namequiz.data;

public final class Person {

    private final String firstName;
    private final String fullName;
    private final Department department;
    private final String image;

    public Person(String firstName, String fullName, Department department, String image) {
        this.firstName = firstName;
        this.fullName = fullName;
        this.department = department;
        this.image = image;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getFullName() {
        return fullName;
    }

    public Department getDepartment() {
        return department;
    }

    public String getImage() {
        return image;
    }
}
