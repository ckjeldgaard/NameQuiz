package com.trifork.ckp.namequiz.data.firebase;

import android.support.annotation.NonNull;

import com.firebase.client.DataSnapshot;
import com.trifork.ckp.namequiz.model.Department;

import java.util.ArrayList;
import java.util.List;

public final class FirebaseDataFactory {

    private final DataSnapshot dataSnapshot;

    public FirebaseDataFactory(@NonNull DataSnapshot dataSnapshot) {
        if (dataSnapshot == null) {
            throw new IllegalArgumentException("DataSnapshot cannot be null");
        }
        this.dataSnapshot = dataSnapshot;
    }

    public List<Department> asDepartmentList() {
        List<Department> departments = new ArrayList<>();
        for (DataSnapshot departmentSnapshot : dataSnapshot.getChildren()) {
            departments.add(
                    new Department(
                            (long) departmentSnapshot.child("id").getValue(),
                            (String) departmentSnapshot.child("departmentName").getValue()
                    )
            );
        }
        return departments;
    }
}
