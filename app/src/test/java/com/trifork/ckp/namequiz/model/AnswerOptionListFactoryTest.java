package com.trifork.ckp.namequiz.model;

import com.trifork.ckp.namequiz.fakes.FakePersonsFactory;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AnswerOptionListFactoryTest {

    private List<Person> persons;

    @Mock
    private FakeNames fakeNames;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        persons = new FakePersonsFactory().producePersons("persons.json");
    }

    @Test
    public void testAnswerOptionListContainsFourOptions() throws Exception {
        AbstractAnswerOptionListFactory factory = new AnswerOptionListFactory(persons.get(0), persons, fakeNames);
        assertEquals(4, factory.makeAnswerOptionList().size());
    }

    @Test
    public void testAnswerOptionListContainsNoDuplicates() throws Exception {
        List<AnswerOption> optionList = new AnswerOptionListFactory(persons.get(0), persons, fakeNames).makeAnswerOptionList();

        boolean duplicates = false;
        for (int j = 0; j < optionList.size(); j++)
            for (int k = j+1; k < optionList.size(); k++)
                if (k != j && optionList.get(k).displayOption().equals(optionList.get(j).displayOption()))
                    duplicates = true;

        assertEquals(String.format("Option list contains: %s", optionList), false, duplicates);
    }

    @Test
    public void testShortPersonsListGeneratesListWithFourNames() throws Exception {
        ArrayList<Person> shortList = new ArrayList<>(1);
        shortList.add(persons.get(0));
        AbstractAnswerOptionListFactory factory = new AnswerOptionListFactory(persons.get(0), shortList, new FakeNames(persons.get(0).gender()));
        assertEquals(4, factory.makeAnswerOptionList().size());
    }

    @Test
    public void testNumberOfFakeNamesMatchesNumberOfMissingPersonsOfSameGender() throws Exception {
        // Arrange:
        FakeNames names = new FakeNames(persons.get(0).gender());
        List<String> fakeNamesList = names.list(100);
        ArrayList<Person> onePersonDepartment = new ArrayList<>(1);
        onePersonDepartment.add(persons.get(0));

        // Act
        AbstractAnswerOptionListFactory factory = new AnswerOptionListFactory(persons.get(0), onePersonDepartment, names);
        List<AnswerOption> answerOptions = factory.makeAnswerOptionList();

        int countFakeNames = 0;
        for (String fakeName : fakeNamesList) {
            for (AnswerOption answerOption : answerOptions) {
                if (answerOption.displayOption().equals(fakeName)) {
                    countFakeNames++;
                }
            }
        }

        // Assert
        assertEquals(3, countFakeNames);
    }
}