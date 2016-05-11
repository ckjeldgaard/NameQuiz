package com.trifork.ckp.namequiz.fakes;

import com.google.gson.JsonElement;
import com.trifork.ckp.namequiz.model.Department;

import java.io.IOException;

public final class FakeDepartmentsFactory {

    public FakeDepartmentsFactory() {
    }

    public Department produceDepartment(String fileName) {
        JsonElement json;
        try {
            json = new JsonFile(fileName).open();
        } catch (IOException e) {
            throw new IllegalArgumentException(String.format("Couldn't parse file %s as Json", fileName), e);
        }

        return new Department(
                json.getAsJsonObject().get("department").getAsJsonObject().get("id").getAsInt(),
                json.getAsJsonObject().get("department").getAsJsonObject().get("departmentName").getAsString()
        );
    }
}
