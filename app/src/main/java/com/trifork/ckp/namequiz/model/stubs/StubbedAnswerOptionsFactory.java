package com.trifork.ckp.namequiz.model.stubs;

import com.trifork.ckp.namequiz.model.AnswerOption;
import java.util.ArrayList;
import java.util.List;

public final class StubbedAnswerOptionsFactory {

    public StubbedAnswerOptionsFactory() {
    }

    public List<AnswerOption> produceAnswerOptions() {
        return new ArrayList<AnswerOption>() {{
            add(new AnswerOption("James"));
            add(new AnswerOption("John"));
            add(new AnswerOption("Robert"));
        }};
    }
}
