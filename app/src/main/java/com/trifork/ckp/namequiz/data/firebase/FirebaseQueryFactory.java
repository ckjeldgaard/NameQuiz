package com.trifork.ckp.namequiz.data.firebase;

import com.firebase.client.Firebase;

public final class FirebaseQueryFactory {

    private final String firebaseUrl;
    private final String path;

    public FirebaseQueryFactory(String firebaseUrl, String path) {
        this.firebaseUrl = firebaseUrl;
        this.path = path;
    }

    public Firebase queryReference() {
        return new Firebase(
                String.format(
                        "%s%s",
                        this.firebaseUrl,
                        this.path
                )
        );
    }
}
