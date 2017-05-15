package com.quizappexam.app.ormlitetest;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "test")
public class Test {


    @DatabaseField(generatedId = true)
    int id;

    @DatabaseField
    String name;


    @ForeignCollectionField
    private ForeignCollection<TestCollection> mTestCollections;

    public Test() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getName();
    }

    public ForeignCollection<TestCollection> getTestCollections() {
        return mTestCollections;
    }


}
