package com.trifork.ckp.namequiz.repository;

import android.util.Log;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.trifork.ckp.namequiz.model.Department;
import com.trifork.ckp.namequiz.model.Gender;
import com.trifork.ckp.namequiz.model.Person;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FakePersonsFactory {

    private static final String TAG = FakePersonsFactory.class.getSimpleName();

    private static final String PERSONS_JSON = "persons.json";

    public FakePersonsFactory() {
    }

    public Department produceDepartment() {
        return new Department(1, "Copenhagen");
    }

    public List<Person> producePersons() {
        List<Person> persons = new ArrayList<>();
        JsonElement json = new JsonParser().parse(this.openFile(PERSONS_JSON));

        for (Map.Entry<String,JsonElement> person : json.getAsJsonObject().entrySet()) {
            String gender = person.getValue().getAsJsonObject().get("gender").getAsString();
            persons.add(
                    new Person(
                            person.getValue().getAsJsonObject().get("firstName").getAsString(),
                            person.getValue().getAsJsonObject().get("fullName").getAsString(),
                            produceDepartment(),
                            person.getValue().getAsJsonObject().get("image").getAsString(),
                            gender.equals("male") ? Gender.MALE : Gender.FEMALE
                    )
            );
        }

        return persons;
    }

    private String openFile(String fileName) {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = null;
        try {
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(fileName);
            reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            Log.e(TAG, "IOException reading file " + fileName, e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    Log.e(TAG, "IOException reading file " + fileName, e);
                }
            }
        }
        return sb.toString();
    }
}
