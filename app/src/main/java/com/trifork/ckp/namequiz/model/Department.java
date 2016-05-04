package com.trifork.ckp.namequiz.model;

import android.os.Parcel;
import android.os.Parcelable;

public final class Department implements Parcelable {

    private final long id;
    private final String departmentName;

    public Department(long id, String departmentName) {
        this.id = id;
        this.departmentName = departmentName;
    }

    protected Department(Parcel in) {
        id = in.readLong();
        departmentName = in.readString();
    }

    public static final Creator<Department> CREATOR = new Creator<Department>() {
        @Override
        public Department createFromParcel(Parcel in) {
            return new Department(in);
        }

        @Override
        public Department[] newArray(int size) {
            return new Department[size];
        }
    };

    public long getId() {
        return id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    @Override
    public String toString() {
        return this.departmentName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(departmentName);
    }
}
