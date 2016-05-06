package com.trifork.ckp.namequiz.data;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;
import com.trifork.ckp.namequiz.model.Department;
import com.trifork.ckp.namequiz.model.Gender;
import com.trifork.ckp.namequiz.model.Person;

import java.util.ArrayList;
import java.util.List;

public class FirebaseServiceApi implements ServiceApi {

    private static final String TAG = FirebaseServiceApi.class.getSimpleName();

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
        Query queryRef = firebaseRef.orderByChild("id");
        queryRef.addValueEventListener(new ValueEventListener() {
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
                Log.e(TAG, "The getAllDepartments read failed: " + firebaseError.getMessage());
            }
        });
    }

    @Override
    public void getPersonsBelongingToDepartment(final ServiceCallback<List<Person>> callback, final Department department) {
        Firebase personsRef = new Firebase(
                String.format(
                        "%s%s",
                        this.firebaseUrl,
                        "/data/persons"
                )
        );
        Query personsQueryRef = personsRef.orderByChild("department")
                .startAt(department.getDepartmentName().toLowerCase())
                .endAt(department.getDepartmentName().toLowerCase());

        personsQueryRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Person> persons = new ArrayList<>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String firstName = (String) snapshot.child("firstName").getValue();
                    String fullName = (String) snapshot.child("fullName").getValue();
                    String imageUrl = (String) snapshot.child("image").getValue();
                    String genderString = (String) snapshot.child("gender").getValue();
                    Gender gender = genderString.equals("male") ? Gender.MALE : Gender.FEMALE;

                    persons.add(
                            new Person(
                                firstName,
                                fullName,
                                department,
                                imageUrl,
                                gender
                            )
                    );
                }
                if (persons.size() > 0) {
                    callback.onLoaded(persons);
                } else {
                    callback.onError("No departments");
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.e(TAG, "The getPersonsBelongingToDepartment read failed: " + firebaseError.getMessage());
            }
        });
    }
}
