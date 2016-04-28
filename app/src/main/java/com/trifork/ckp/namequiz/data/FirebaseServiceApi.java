package com.trifork.ckp.namequiz.data;

import android.util.Log;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.trifork.ckp.namequiz.model.Department;

import java.util.ArrayList;
import java.util.List;

public class FirebaseServiceApi implements ServiceApi {

    private final String firebaseUrl;

    public FirebaseServiceApi(String firebaseUrl) {
        this.firebaseUrl = firebaseUrl;
    }

    @Override
    public void getAllDepartments(final ServiceCallback<List<Department>> callback) {
        Firebase firebaseRef = new Firebase(
                String.format(
                        "%s%s",
                        this.firebaseUrl,
                        "/data/departments"
                )
        );
        Log.d("Firebase", "Retrieving data...");
        firebaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                List<Department> departments = new ArrayList<>();
                for (DataSnapshot departmentSnapshot : snapshot.getChildren()) {
                    departments.add(
                            new Department(
                                    (long) departmentSnapshot.child("id").getValue(),
                                    (String) departmentSnapshot.child("departmentName").getValue()
                            )
                    );
                }
                if (departments.size() > 0) {
                    callback.onLoaded(departments);
                } else {
                    callback.onError("No departments");
                }
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.e("Firebase", "The read failed: " + firebaseError.getMessage());
            }
        });
    }
}
