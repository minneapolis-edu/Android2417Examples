package com.clara.hydrationroom.db;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;


@Entity(indices = {@Index( value = {"name"}, unique = true)})
public class Person {

    @PrimaryKey(autoGenerate = true)
    int id;

    @NonNull
    String name;

    public Person() {}

    @Ignore
    public Person(String name) {
        this.name = name;
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
}
