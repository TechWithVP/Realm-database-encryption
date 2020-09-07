package com.example.realmsecure.Models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Student extends RealmObject {
    @PrimaryKey
    private int std_id;

    private String std_name;
    private int std_age;

    public int getStd_id() {
        return std_id;
    }

    public void setStd_id(int std_id) {
        this.std_id = std_id;
    }

    public String getStd_name() {
        return std_name;
    }

    public void setStd_name(String std_name) {
        this.std_name = std_name;
    }

    public int getStd_age() {
        return std_age;
    }

    public void setStd_age(int std_age) {
        this.std_age = std_age;
    }

    public Student() {
    }

    public Student(int std_id, String std_name, int std_age) {
        this.std_id = std_id;
        this.std_name = std_name;
        this.std_age = std_age;
    }
}
