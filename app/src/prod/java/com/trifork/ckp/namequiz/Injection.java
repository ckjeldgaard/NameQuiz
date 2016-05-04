package com.trifork.ckp.namequiz;


import com.trifork.ckp.namequiz.data.Repository;
import com.trifork.ckp.namequiz.data.InMemoryRepository;
import com.trifork.ckp.namequiz.data.FirebaseServiceApi;

public class Injection {

    private static final String FIREBASE_BASE_URL = "https://popping-fire-4168.firebaseio.com";

    public static Repository provideRepository() {
        return new InMemoryRepository(
                new FirebaseServiceApi(FIREBASE_BASE_URL)
        );
    }
}
