package com.trifork.ckp.namequiz.fakes;

import com.google.gson.JsonElement;

import java.io.IOException;

public interface File {
    JsonElement open() throws IOException;
}
