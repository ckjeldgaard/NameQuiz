package com.trifork.ckp.namequiz.data.firebase;

import android.util.Log;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;
import com.trifork.ckp.namequiz.data.ServiceApi;
import com.trifork.ckp.namequiz.model.Department;
import com.trifork.ckp.namequiz.model.Gender;
import com.trifork.ckp.namequiz.model.Person;
import com.trifork.ckp.namequiz.util.ConnectivityCheck;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;

public final class FirebaseServiceApi implements ServiceApi {

    private static final String TAG = FirebaseServiceApi.class.getSimpleName();

    private final Firebase departmentsQuery;
    private final Firebase personsQuery;
    private final ConnectivityCheck connectivityCheck;

    public FirebaseServiceApi(Firebase departmentsQuery, Firebase personsQuery, ConnectivityCheck connectivityCheck) {
        this.departmentsQuery = departmentsQuery;
        this.personsQuery = personsQuery;
        this.connectivityCheck = connectivityCheck;
    }

    @Override
    public void getAllDepartments(final ServiceCallback<List<Department>> callback) {
        if (!this.connectivityCheck.isOnline()) {
            callback.onError(new ConnectException("No internet activity detected."));
        } else {
            Query queryRef = departmentsQuery.orderByChild("id");
            queryRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    List<Department> departments = new FirebaseDataFactory(snapshot).asDepartmentList();
                    if (departments.size() > 0) {
                        callback.onLoaded(departments);
                    } else {
                        callback.onError(new RuntimeException("No departments found."));
                    }
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {
                    Log.e(TAG, "The getAllDepartments read failed: " + firebaseError.getMessage());
                    callback.onError(firebaseError.toException());
                }
            });
        }
    }

    @Override
    public void getPersonsBelongingToDepartment(final ServiceCallback<List<Person>> callback, final Department department) {
        if (!connectivityCheck.isOnline()) {
            callback.onError(new ConnectException("No internet activity detected."));
        } else {
            Query personsQueryRef = personsQuery.orderByChild("department")
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
                        callback.onError(new RuntimeException("No persons found for department"));
                    }
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {
                    Log.e(TAG, "The getPersonsBelongingToDepartment read failed: " + firebaseError.getMessage());
                    callback.onError(firebaseError.toException());
                }
            });
        }
    }
}
