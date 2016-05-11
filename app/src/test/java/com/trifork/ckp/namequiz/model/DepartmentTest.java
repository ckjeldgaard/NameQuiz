package com.trifork.ckp.namequiz.model;

import android.os.Parcel;
import android.test.AndroidTestCase;

import com.trifork.ckp.namequiz.mock.MockParcel;

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

    @Test
    public void testParcelableImplementation() throws Exception {
        Parcel parcel = new MockParcel().getMockedParcel();
        department.writeToParcel(parcel, 0);

        // Reset the parcel for reading:
        parcel.setDataPosition(0);

        // Reconstruct object from parcel and assert:
        Department departmentCreatedFromParcel = Department.CREATOR.createFromParcel(parcel);
        assertEquals(department.getDepartmentName(), departmentCreatedFromParcel.getDepartmentName());
        assertEquals(department.getId(), departmentCreatedFromParcel.getId());
    }

    @Test
    public void testParcelableArrayImplementation() throws Exception {
        Parcel parcel = new MockParcel().getMockedParcel();
        department.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        Department[] departmentCreatedFromParcel = Department.CREATOR.newArray(3);
        assertEquals(3, departmentCreatedFromParcel.length);
    }
}