package com.trifork.ckp.namequiz.fakes;

import com.google.gson.JsonElement;
import com.trifork.ckp.namequiz.model.Gender;
import com.trifork.ckp.namequiz.model.Person;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class FakePersonsFactory {

    public FakePersonsFactory() {
    }

    public Person producePerson(String fileName) {
        return this.producePersons(fileName).get(0);
    }

    public List<Person> producePersons(String fileName) {
        List<Person> persons = new ArrayList<>();
        JsonElement json;
        try {
            json = new JsonFile(fileName).open();
        } catch (IOException e) {
            throw new IllegalArgumentException(String.format("Couldn't parse file %s as Json", fileName), e);
        }

        for (Map.Entry<String,JsonElement> person : json.getAsJsonObject().entrySet()) {
            String gender = person.getValue().getAsJsonObject().get("gender").getAsString();
            persons.add(
                    new Person(
                            person.getValue().getAsJsonObject().get("firstName").getAsString(),
                            person.getValue().getAsJsonObject().get("fullName").getAsString(),
                            new FakeDepartmentsFactory().produceDepartment("department.json"),
                            person.getValue().getAsJsonObject().get("image").getAsString(),
                            gender.equals("male") ? Gender.MALE : Gender.FEMALE
                    )
            );
        }

        return persons;
    }
}
