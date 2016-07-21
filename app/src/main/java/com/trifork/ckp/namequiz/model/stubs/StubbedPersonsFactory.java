package com.trifork.ckp.namequiz.model.stubs;

import com.trifork.ckp.namequiz.model.Gender;
import com.trifork.ckp.namequiz.model.Person;
import java.util.ArrayList;
import java.util.List;

public final class StubbedPersonsFactory {

    public StubbedPersonsFactory() {
    }

    public Person producePerson() {
        return PersonBuilder.aPerson().build();
    }

    public List<Person> producePersons() {
        List<Person> persons = new ArrayList<>();

        persons.add(PersonBuilder.aPerson().withFirstName("Virginia").withFullName("Virginia Turner").withGender(Gender.FEMALE).build());
        persons.add(PersonBuilder.aPerson().withFirstName("Sara").withFullName("Sara Patterson").withGender(Gender.FEMALE).build());
        persons.add(PersonBuilder.aPerson().withFirstName("Ruby").withFullName("Ruby Sanchez").withGender(Gender.FEMALE).build());
        persons.add(PersonBuilder.aPerson().withFirstName("Patricia").withFullName("Patricia Peterson").withGender(Gender.FEMALE).build());
        persons.add(PersonBuilder.aPerson().withFirstName("Sharon").withFullName("Sharon Campbell").withGender(Gender.FEMALE).build());
        persons.add(PersonBuilder.aPerson().withFirstName("Jason").withFullName("Jason Garcia").withGender(Gender.MALE).build());
        persons.add(PersonBuilder.aPerson().withFirstName("Douglas").withFullName("Douglas Clark").withGender(Gender.MALE).build());
        persons.add(PersonBuilder.aPerson().withFirstName("Ruth").withFullName("Ruth Hill").withGender(Gender.FEMALE).build());
        persons.add(PersonBuilder.aPerson().withFirstName("Carol").withFullName("Carol Scott").withGender(Gender.FEMALE).build());
        persons.add(PersonBuilder.aPerson().withFirstName("Joan").withFullName("Joan Rodriguez").withGender(Gender.FEMALE).build());
        persons.add(PersonBuilder.aPerson().withFirstName("Michael").withFullName("Michael Roberts").withGender(Gender.MALE).build());
        persons.add(PersonBuilder.aPerson().withFirstName("Adam").withFullName("Adam Reed").withGender(Gender.MALE).build());
        persons.add(PersonBuilder.aPerson().withFirstName("Eric").withFullName("Eric Young").withGender(Gender.MALE).build());
        persons.add(PersonBuilder.aPerson().withFirstName("Gloria").withFullName("Gloria Johnson").withGender(Gender.FEMALE).build());
        persons.add(PersonBuilder.aPerson().withFirstName("Theresa").withFullName("Theresa Kelly").withGender(Gender.FEMALE).build());
        persons.add(PersonBuilder.aPerson().withFirstName("Tina").withFullName("Tina Cooper").withGender(Gender.FEMALE).build());
        persons.add(PersonBuilder.aPerson().withFirstName("Roy").withFullName("Roy Perez").withGender(Gender.MALE).build());
        persons.add(PersonBuilder.aPerson().withFirstName("Ralph").withFullName("Ralph Hughes").withGender(Gender.MALE).build());
        persons.add(PersonBuilder.aPerson().withFirstName("Aaron").withFullName("Aaron Ross").withGender(Gender.MALE).build());
        persons.add(PersonBuilder.aPerson().withFirstName("Donald").withFullName("Donald Green").withGender(Gender.MALE).build());
        persons.add(PersonBuilder.aPerson().withFirstName("Cynthia").withFullName("Cynthia Brown").withGender(Gender.FEMALE).build());
        persons.add(PersonBuilder.aPerson().withFirstName("Gary").withFullName("Gary Baker").withGender(Gender.MALE).build());
        persons.add(PersonBuilder.aPerson().withFirstName("Patrick").withFullName("Patrick Walker").withGender(Gender.MALE).build());
        persons.add(PersonBuilder.aPerson().withFirstName("Rose").withFullName("Rose Lewis").withGender(Gender.FEMALE).build());
        persons.add(PersonBuilder.aPerson().withFirstName("Kevin").withFullName("Kevin Morris").withGender(Gender.MALE).build());

        return persons;
    }
}
