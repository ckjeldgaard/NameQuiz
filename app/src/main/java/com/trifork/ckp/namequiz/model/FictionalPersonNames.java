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
import java.util.Collections;
import java.util.List;

public class FictionalPersonNames {
    private static final String MALE_FILE_NAME = "male_names.json";
    private static final String FEMALE_FILE_NAME = "female_names.json";

    private final Gender gender;

    public FictionalPersonNames(Gender gender) {
        this.gender = gender;
    }

    public List<String> list(int size) {
        List<String> options = new ArrayList<>();
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

        return names;
    }
}
