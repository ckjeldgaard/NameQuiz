package com.trifork.ckp.namequiz;


import com.trifork.ckp.namequiz.data.DepartmentsRepository;
import com.trifork.ckp.namequiz.data.InMemoryDepartmentsRepository;
import com.trifork.ckp.namequiz.data.FirebaseServiceApi;

public class Injection {

    private static final String FIREBASE_BASE_URL = "https://popping-fire-4168.firebaseio.com";

    public static DepartmentsRepository provideDepartmentsRepository() {
        return new InMemoryDepartmentsRepository(
                new FirebaseServiceApi(FIREBASE_BASE_URL)
        );
    }
}
