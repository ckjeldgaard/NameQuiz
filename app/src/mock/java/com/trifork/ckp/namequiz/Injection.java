package com.trifork.ckp.namequiz;

import android.content.Context;

import com.trifork.ckp.namequiz.data.FakeServiceApi;
import com.trifork.ckp.namequiz.data.InMemoryRepository;
import com.trifork.ckp.namequiz.data.Repository;
import com.trifork.ckp.namequiz.util.FakePersonImage;
import com.trifork.ckp.namequiz.util.PersonImage;

public final class Injection {

    private final Repository repository;

    public Injection(Context ctx) {
        this.repository = new InMemoryRepository(
                new FakeServiceApi()
        );
    }

    public Repository provideRepository() {
        return this.repository;
    }

    public PersonImage providePersonImage(Context ctx) {
        return new FakePersonImage();
    }
}
