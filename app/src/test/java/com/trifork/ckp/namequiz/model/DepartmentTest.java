package com.trifork.ckp.namequiz.model;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class DepartmentTest {

    private Department department;

    @Before
    public void setUp() throws Exception {
        department = new Department(1, "Copenhagen");
    }

    @Test
    public void testGetId() throws Exception {
        assertEquals(1, department.getId());
    }

    @Test
    public void testGetDepartmentName() throws Exception {
        assertEquals("Copenhagen", department.getDepartmentName());
    }

    @Test
    public void testToString() throws Exception {
        assertEquals("Copenhagen", department.toString());
    }
}