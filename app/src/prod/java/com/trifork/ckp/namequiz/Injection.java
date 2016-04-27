package com.trifork.ckp.namequiz;


import com.trifork.ckp.namequiz.data.DepartmentsRepository;
import com.trifork.ckp.namequiz.data.InMemoryDepartmentsRepository;
import com.trifork.ckp.namequiz.data.FirebaseServiceApi;

public class Injection {

    public static DepartmentsRepository provideDepartmentsRepositoy() {
        return new InMemoryDepartmentsRepository(
                new FirebaseServiceApi("https://popping-fire-4168.firebaseio.com")
        );
    }
}
