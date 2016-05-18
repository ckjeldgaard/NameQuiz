package com.trifork.ckp.namequiz.model;

import android.test.AndroidTestCase;

import org.junit.Before;
import org.junit.Test;

public class DepartmentTest extends AndroidTestCase {

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