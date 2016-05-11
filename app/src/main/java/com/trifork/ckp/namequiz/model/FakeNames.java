package com.trifork.ckp.namequiz.model;

import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FakeNames {
    private static final String TAG = FakeNames.class.getSimpleName();
    private static final String MALE_FILE_NAME = "male_names.json";
    private static final String FEMALE_FILE_NAME = "female_names.json";

    private final Gender gender;

    public FakeNames(Gender gender) {
        this.gender = gender;
    }

    public List<String> list(int size) {
        List<String> options = new ArrayList<>();
        String fileName = (this.gender == Gender.FEMALE) ? FEMALE_FILE_NAME : MALE_FILE_NAME;
        String jsonContents = openFile(fileName);
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
