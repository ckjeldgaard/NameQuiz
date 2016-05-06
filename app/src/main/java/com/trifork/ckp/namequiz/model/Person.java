package com.trifork.ckp.namequiz.model;

import android.graphics.Bitmap;

public final class Person {

    private final String firstName;
    private final String fullName;
    private final Department department;
    private final String imageUrl;
    private final Gender gender;

    public Person(String firstName, String fullName, Department department, String imageUrl, Gender gender) {
        this.firstName = firstName;
        this.fullName = fullName;
        this.department = department;
        this.imageUrl = imageUrl;
        this.gender = gender;
    }

    public String firstName() {
        return firstName;
    }

    public String fullName() {
        return fullName;
    }

    public Department department() {
        return department;
    }

    public String imageUrl() {
        return imageUrl;
    }

    public Gender gender() {
        return gender;
    }
}
