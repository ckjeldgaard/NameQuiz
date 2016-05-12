package com.trifork.ckp.namequiz.fakes;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.trifork.ckp.namequiz.model.AnswerOption;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class FakeAnswerOptionsFactory {

    public FakeAnswerOptionsFactory() {
    }

    public List<AnswerOption> produceAnswerOptions(String fileName) {
        List<AnswerOption> answerOptions = new ArrayList<>();
        JsonElement json;
        try {
            json = new JsonFile(fileName).open();
        } catch (IOException e) {
            throw new IllegalArgumentException(String.format("Couldn't parse file %s as Json", fileName), e);
        }

        JsonArray names = json.getAsJsonObject().getAsJsonArray("answer_options");
        for (JsonElement name : names) {
            answerOptions.add(new AnswerOption(name.getAsString()));
        }

        return answerOptions;
    }
}
