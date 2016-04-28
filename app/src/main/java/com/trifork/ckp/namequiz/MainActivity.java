package com.trifork.ckp.namequiz;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.firebase.client.Firebase;
import com.trifork.ckp.namequiz.flow.NameQuizDispatcher;
import com.trifork.ckp.namequiz.flow.NameQuizKeyParceler;
import com.trifork.ckp.namequiz.start.StartScreen;

import flow.Flow;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_main);

        //Firebase rootRef = new Firebase("https://popping-fire-4168.firebaseio.com/data");

        /*
        // Get a reference to our posts
        Firebase ref = new Firebase("https://popping-fire-4168.firebaseio.com/data/departments");
        // Attach an listener to read the data at our posts reference
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Object o = snapshot.getValue();
                Log.d("Firebase", o.toString());
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });

        /*
        Firebase alanRef = rootRef.child("departments").child("aarhus");
        Department department = new Department(2, "Aarhus");
        alanRef.setValue(department);


        Department department = new Department(1, "Copenhagen");

        Bitmap bmp =  BitmapFactory.decodeResource(getResources(), R.drawable.twf);//your image
        ByteArrayOutputStream bYtE = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, bYtE);
        bmp.recycle();
        byte[] byteArray = bYtE.toByteArray();
        String imageFile = Base64.encodeToString(byteArray, Base64.DEFAULT);

        Firebase personRef = rootRef.child("persons").child("twf");
        Person person = new Person("Thomas", "Thomas Fischer", department, imageFile);
        personRef.setValue(person);*/

    }

    @Override protected void attachBaseContext(Context baseContext) {
        baseContext = Flow.configure(baseContext, this)
                .dispatcher(new NameQuizDispatcher(this))
                .defaultKey(new StartScreen())
                .keyParceler(new NameQuizKeyParceler())
                .install();
        super.attachBaseContext(baseContext);
    }

    @Override public void onBackPressed() {
        if (!Flow.get(this).goBack()) {
            super.onBackPressed();
        }
    }
}
