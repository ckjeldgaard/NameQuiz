package com.trifork.ckp.namequiz.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class JsonFile implements File {
    private static final String ENCODING = "UTF-8";
    private final String fileName;

    public JsonFile(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String open() throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = null;
        try {
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(fileName);
            reader = new BufferedReader(new InputStreamReader(inputStream, ENCODING));

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
        return sb.toString();
    }
}
