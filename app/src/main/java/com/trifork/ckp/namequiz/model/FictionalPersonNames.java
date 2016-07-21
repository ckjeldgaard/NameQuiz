package com.trifork.ckp.namequiz.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.trifork.ckp.namequiz.util.JsonFile;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FictionalPersonNames {
    private final String[] maleNames = {"James", "John", "Robert", "Michael", "William", "David", "Richard", "Charles", "Joseph", "Thomas", "Christopher", "Daniel", "Paul", "Mark", "Donald", "George", "Kenneth", "Steven", "Edward", "Brian", "Ronald", "Kevin", "Jason", "Jeff"};
    private final String[] femaleNames = {"Mary", "Patricia", "Linda", "Barbara", "Elizabeth", "Jennifer", "Maria", "Susan", "Margaret", "Dorothy", "Lisa", "Nancy", "Karen", "Betty", "Helen", "Sandra", "Donna", "Carol", "Ruth", "Sharon", "Michelle", "Laura", "Sarah", "Kimberly", "Deborah"};

    private final Gender gender;

    public FictionalPersonNames(Gender gender) {
        this.gender = gender;
    }

    public List<String> list(int size) {
        List<String> options = (this.gender == Gender.FEMALE) ? new ArrayList<String>(Arrays.asList(femaleNames)) : new ArrayList<String>(Arrays.asList(maleNames));
        Collections.shuffle(options);
        List<String> names = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            if (i < options.size()) {
                names.add(options.get(i));
            }
        }
        return names;


        /*List<String> options = new ArrayList<>();
        String fileName = (this.gender == Gender.FEMALE) ? FEMALE_FILE_NAME : MALE_FILE_NAME;
        String jsonContents;
        try {
            jsonContents = new JsonFile(fileName).open();
        } catch (IOException e) {
            throw new IllegalArgumentException(String.format("Couldn't parse file %s as Json", fileName), e);
        }
        JsonArray jsonNames = new JsonParser().parse(jsonContents).getAsJsonObject().getAsJsonArray("names");
        for (JsonElement name : jsonNames) {
            options.add(name.getAsString());
        }
        Collections.shuffle(options);

        List<String> names = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            if (i < options.size()) {
                names.add(options.get(i));
            }
        }

        return names;*/
    }
}
