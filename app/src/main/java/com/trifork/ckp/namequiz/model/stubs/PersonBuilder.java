package com.trifork.ckp.namequiz.model.stubs;

import com.trifork.ckp.namequiz.model.Department;
import com.trifork.ckp.namequiz.model.Gender;
import com.trifork.ckp.namequiz.model.Person;

public class PersonBuilder {

    public static final String DEFAULT_FIRST_NAME = "John";
    public static final String DEFAULT_FULL_NAME = "John Smith";
    public static final Department DEFAULT_DEPARTMENT = new Department(1, "Copenhagen");
    public static final String DEFAULT_IMAGE_URL = "http://example.org/image.png";
    public static final Gender DEFAULT_GENDER = Gender.MALE;

    private String firstName = DEFAULT_FIRST_NAME;
    private String fullName = DEFAULT_FULL_NAME;
    private Department department = DEFAULT_DEPARTMENT;
    private String imageUrl = DEFAULT_IMAGE_URL;
    private Gender gender = DEFAULT_GENDER;

    private PersonBuilder() {
    }

    public static PersonBuilder aPerson() {
        return new PersonBuilder();
    }

    public PersonBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public PersonBuilder withFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public PersonBuilder withDepartment(Department department) {
        this.department = department;
        return this;
    }

    public PersonBuilder withImage(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public PersonBuilder withGender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public Person build() {
        return new Person(firstName, fullName, department, imageUrl, gender);
    }
}
