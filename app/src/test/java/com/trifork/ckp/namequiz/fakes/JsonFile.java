package com.trifork.ckp.namequiz.fakes;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public final class JsonFile implements File {
    private static final String TAG = JsonFile.class.getSimpleName();

    private final String fileName;

    public JsonFile(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public JsonElement open() throws IOException {
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
            throw new IOException(String.format("The file %s doesn't exist", fileName), e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    throw new IOException(String.format("Can't close the BufferedReader for file %s", fileName), e);
                }
            }
        }
        return new JsonParser().parse(sb.toString());
    }
}
