package com.trifork.ckp.namequiz.model;

import android.media.Image;

public final class Person {

    private final String firstName;
    private final String fullName;
    private final Department department;
    private final Image image;
    private final Gender gender;

    public Person(String firstName, String fullName, Department department, Image image, Gender gender) {
        this.firstName = firstName;
        this.fullName = fullName;
        this.department = department;
        this.image = image;
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

    public Image image() {
        return image;
    }

    public Gender gender() {
        return gender;
    }
}
