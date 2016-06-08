package com.trifork.ckp.namequiz;


import android.content.Context;

import com.trifork.ckp.namequiz.data.Repository;
import com.trifork.ckp.namequiz.data.InMemoryRepository;
import com.trifork.ckp.namequiz.data.firebase.FirebaseQueryFactory;
import com.trifork.ckp.namequiz.data.firebase.FirebaseServiceApi;
import com.trifork.ckp.namequiz.util.ConnectivityCheck;
import com.trifork.ckp.namequiz.util.PersonImage;
import com.trifork.ckp.namequiz.util.PicassoPersonImage;

public final class Injection {

    private static final String FIREBASE_BASE_URL = "https://popping-fire-4168.firebaseio.com";
    private static final String DEPARTMENTS_PATH = "/data/departments";
    private static final String PERSONS_PATH = "/data/persons";

    private final Repository repository;

    public Injection(Context context) {
        this.repository = new InMemoryRepository(
                new FirebaseServiceApi(
                        new FirebaseQueryFactory(FIREBASE_BASE_URL, DEPARTMENTS_PATH).queryReference(),
                        new FirebaseQueryFactory(FIREBASE_BASE_URL, PERSONS_PATH).queryReference(),
                        new ConnectivityCheck(context)
                )
        );
    }

    public Repository provideRepository() {
        return this.repository;
    }

    public PersonImage providePersonImage(Context ctx) {
        return new PicassoPersonImage(ctx);
    }
}
