package com.trifork.ckp.namequiz;

import android.content.Context;

import com.trifork.ckp.namequiz.data.FakeServiceApi;
import com.trifork.ckp.namequiz.data.InMemoryRepository;
import com.trifork.ckp.namequiz.data.Repository;
import com.trifork.ckp.namequiz.util.FakePersonImage;
import com.trifork.ckp.namequiz.util.PersonImage;

public final class Injection {

    public Repository provideRepository() {
        return new InMemoryRepository(
                new FakeServiceApi()
        );
    }

    public PersonImage providePersonImage(Context ctx) {
        return new FakePersonImage();
    }
}
