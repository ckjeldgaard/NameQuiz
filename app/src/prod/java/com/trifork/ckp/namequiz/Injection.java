package com.trifork.ckp.namequiz;


import android.content.Context;

import com.trifork.ckp.namequiz.data.Repository;
import com.trifork.ckp.namequiz.data.InMemoryRepository;
import com.trifork.ckp.namequiz.data.FirebaseServiceApi;
import com.trifork.ckp.namequiz.util.PersonImage;
import com.trifork.ckp.namequiz.util.PicassoPersonImage;

public final class Injection {

    private static final String FIREBASE_BASE_URL = "https://popping-fire-4168.firebaseio.com";

    public Repository provideRepository() {
        return new InMemoryRepository(
                new FirebaseServiceApi(FIREBASE_BASE_URL)
        );
    }

    public PersonImage providePersonImage(Context ctx) {
        return new PicassoPersonImage(ctx);
    }
}
