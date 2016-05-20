package com.trifork.ckp.namequiz.model.stubs;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.trifork.ckp.namequiz.model.Department;
import com.trifork.ckp.namequiz.util.JsonFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class StubbedDepartmentsFactory {

    public StubbedDepartmentsFactory() {
    }

    public List<Department> produceDepartments(String fileName) {
        List<Department> departments = new ArrayList<>();
        JsonElement json;
        try {
            json = new JsonParser().parse(
                    new JsonFile(fileName).open()
            );
        } catch (IOException e) {
            throw new IllegalArgumentException(String.format("Couldn't parse file %s as Json", fileName), e);
        }

        JsonArray jsonDepartments = json.getAsJsonObject().getAsJsonArray("departments");
        for (JsonElement department : jsonDepartments) {
            departments.add(
                    new Department(
                            department.getAsJsonObject().get("id").getAsInt(),
                            department.getAsJsonObject().get("departmentName").getAsString()
                    )
            );

        }


        return departments;
    }

    public Department produceDepartment(String fileName) {
        JsonElement json;
        try {
            json = new JsonParser().parse(
                    new JsonFile(fileName).open()
            );
        } catch (IOException e) {
            throw new IllegalArgumentException(String.format("Couldn't parse file %s as Json", fileName), e);
        }

        return new Department(
                json.getAsJsonObject().get("department").getAsJsonObject().get("id").getAsInt(),
                json.getAsJsonObject().get("department").getAsJsonObject().get("departmentName").getAsString()
        );
    }
}
