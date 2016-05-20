package com.trifork.ckp.namequiz.model;

import com.trifork.ckp.namequiz.model.stubs.StubbedDepartmentsFactory;
import com.trifork.ckp.namequiz.model.stubs.StubbedPersonsFactory;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PersonTest {

    private Person person;
    private Department department;

    @Before
    public void setUp() throws Exception {
        department = new StubbedDepartmentsFactory().produceDepartment("stubbed_department.json");
        person = new StubbedPersonsFactory().producePerson("stubbed_person.json");
    }

    @Test
    public void testFirstName() throws Exception {
        assertEquals("John", person.firstName());
    }

    @Test
    public void testFullName() throws Exception {
        assertEquals("John Doe", person.fullName());
    }

    @Test
    public void testDepartment() throws Exception {
        assertEquals(department.getDepartmentName(), person.department().getDepartmentName());
        assertEquals(department.getId(), person.department().getId());
    }

    @Test
    public void testImage() throws Exception {
        assertEquals("http://example.org/image.png", person.imageUrl());
    }

    @Test
    public void testGender() throws Exception {
        assertEquals(Gender.MALE, person.gender());
    }
}