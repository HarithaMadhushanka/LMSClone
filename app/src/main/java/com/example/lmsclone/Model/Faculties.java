package com.example.lmsclone.Model;

public class Faculties {
    private String name, id;

    public Faculties(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Faculties(String name, String id) {
        this.name = name;
        this.id = id;
    }
}
