package com.trifork.ckp.namequiz.model.stubs;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.trifork.ckp.namequiz.model.Gender;
import com.trifork.ckp.namequiz.model.Person;
import com.trifork.ckp.namequiz.util.JsonFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class StubbedPersonsFactory {

    public StubbedPersonsFactory() {
    }

    public Person producePerson(String fileName) {
        return this.producePersons(fileName).get(0);
    }

    public List<Person> producePersons(String fileName) {
        List<Person> persons = new ArrayList<>();
        JsonElement json;
        try {
            json = new JsonParser().parse(
                    new JsonFile(fileName).open()
            );
        } catch (IOException e) {
            throw new IllegalArgumentException(String.format("Couldn't parse file %s as Json", fileName), e);
        }

        for (Map.Entry<String,JsonElement> person : json.getAsJsonObject().entrySet()) {
            String gender = person.getValue().getAsJsonObject().get("gender").getAsString();
            persons.add(
                    new Person(
                            person.getValue().getAsJsonObject().get("firstName").getAsString(),
                            person.getValue().getAsJsonObject().get("fullName").getAsString(),
                            new StubbedDepartmentsFactory().produceDepartment("stubbed_department.json"),
                            person.getValue().getAsJsonObject().get("image").getAsString(),
                            gender.equals("male") ? Gender.MALE : Gender.FEMALE
                    )
            );
        }

        return persons;
    }
}
